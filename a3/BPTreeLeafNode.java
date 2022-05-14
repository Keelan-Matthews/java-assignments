/**
 * A B+ tree leaf node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeLeafNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] values;
	
	public BPTreeLeafNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1.
		// You can change this if needed.
		this.keys = new Object[m];
		this.values = new Object[m];
	}

	@SuppressWarnings("unchecked")
	public TValue getValue(int index) {
		return (TValue)this.values[index];
	}

	public void setValue(int index, TValue value) {
		this.values[index] = value;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value)
	{
		if (getKeyCount() < m) {
			if (getKeyCount() == 0) {
				this.setPair(0, key, value);
				keyTally++;
				return this;
			}

			for (int i = 0; i < getKeyCount(); i++) {
				if (i+1 == getKeyCount()) {
					this.setPair(i+1, key, value);
					keyTally++;
					break;
				}

				if (getKey(i).compareTo(key) > 0) {
					for (int j = getKeyCount(); j > i; j--)
						this.setPair(j, getKey(j-1), getValue(j-1));

					this.setPair(i, key, value);
					keyTally++;
					break;
				}
			}
		}

		if (getKeyCount() == m) return split(this);

		return this;
	}

	private void setPair(int i, TKey key, TValue value) {
		this.setKey(i,key);
		this.setValue(i, value);
	}

	private BPTreeInnerNode<TKey, TValue> split(BPTreeLeafNode<TKey, TValue> node) {
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

		BPTreeLeafNode<TKey, TValue> left = new BPTreeLeafNode<>(node.m);
		for (int i = 0; i < median; i++) {
			left.setPair(i, node.getKey(i), node.getValue(i));
			left.keyTally++;
		}

		BPTreeLeafNode<TKey, TValue> right = new BPTreeLeafNode<>(node.m);
		for (int i = median; i < getKeyCount(); i++) {
			right.setPair(right.getKeyCount(), node.getKey(i), node.getValue(i));
			right.keyTally++;
		}

		left.rightSibling = right;
		right.leftSibling = left;
		right.parentNode = left.parentNode = parent;

		left.leftSibling = node.leftSibling;
		if (left.leftSibling != null) left.leftSibling.rightSibling = left;
		right.rightSibling = node.rightSibling;
		if (left.leftSibling != null) right.rightSibling.leftSibling = right;

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
}
