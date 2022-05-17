/**
 * A B+ tree internal node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeInnerNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] references; 
	
	public BPTreeInnerNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1/m+1 instead of m.
		// You can change this if needed. 
		this.keys = new Object[m];
		this.references = new Object[m + 1];
	}
	
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> getChild(int index) {
		return (BPTreeNode<TKey, TValue>)this.references[index];
	}

	public void setChild(int index, BPTreeNode<TKey, TValue> child) {
		this.references[index] = child;
		if (child != null)
			child.setParent(this);
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) {
		BPTreeNode<TKey, TValue> leaf = this.findLeaf(this, key);
		if (leaf.getKeyCount() < m) {
			keyTally++;
			return leaf.insert(key, value);
		}
		else {
			return split(this);
		}
	}
	public int addKey(TKey key) {
		for (int i = 0; i < getKeyCount(); i++) {
			if (getKey(i).compareTo(key) > 0) {
				for (int j = getKeyCount(); j > i; j--) {
					setKey(j, getKey(j - 1));
					setChild(j, getChild(j - 1));
				}

				setKey(i, key);
				keyTally++;
				return i;
			}

			if (i+1 == getKeyCount()) {
				setKey(i+1, key);
				keyTally++;
				return i;
			}
		}
		return 0;
	}

	public BPTreeNode<TKey, TValue> findLeaf(BPTreeNode<TKey, TValue> node, TKey key) {
		int i = 0;
		while (i < this.keyTally && key.compareTo((TKey) this.keys[i]) > 0)
			i++;

		if (node.isLeaf()) return node;

		BPTreeInnerNode<TKey, TValue> inner = (BPTreeInnerNode<TKey, TValue>)node;
		return ((BPTreeInnerNode<TKey, TValue>)inner.getChild(i)).findLeaf(inner.getChild(i), key);
	}

	private BPTreeInnerNode<TKey, TValue> split(BPTreeInnerNode<TKey, TValue> node) {
		BPTreeInnerNode<TKey, TValue> parent = new BPTreeInnerNode<>(node.m);
		int median = node.getKeyCount()/2;
		int pos = 0;

		if (node.parentNode == null) {
			parent.setKey(0, node.getKey(median));
			parent.keyTally++;
		}
		else {
			BPTreeInnerNode<TKey, TValue> currParent = (BPTreeInnerNode<TKey, TValue>)node.parentNode;
			pos = currParent.addKey(node.getKey(median));
			parent = currParent;
		}

		BPTreeInnerNode<TKey, TValue> left = new BPTreeInnerNode<>(node.m);
		for (int i = 0; i < median; i++) {
			left.setPair(i, node.getKey(i), node.getChild(i));
			left.keyTally++;
		}

		BPTreeInnerNode<TKey, TValue> right = new BPTreeInnerNode<>(node.m);
		for (int i = median; i < getKeyCount(); i++) {
			right.setPair(right.getKeyCount(), node.getKey(i), node.getChild(i));
			right.keyTally++;
		}

		if (node.parentNode == null) {
			parent.setChild(0, left);
			parent.setChild(1, right);
		}
		else {
			parent.setChild(pos, left);
			parent.setChild(pos+1, right);
		}
		return parent;
	}

	private void setPair(int i, TKey key, BPTreeNode<TKey, TValue> child) {
		this.setKey(i,key);
		this.setChild(i, child);
	}
}