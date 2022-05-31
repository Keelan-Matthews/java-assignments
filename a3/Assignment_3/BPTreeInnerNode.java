/**
 * A B+ tree internal node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeInnerNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] references; 
	
	public BPTreeInnerNode(int order) 
	{
		this.m = order;

		// my strategy is first accounting for the overflow by making sure the key and references will only contain the keys relative to the degree passed in
		this.keys = new Object[m - 1];
		this.references = new Object[m];
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

	/* --------------------------------------------- ALL MY FUNCTIONS --------------------------------------------- */

	public Object[] getRefArr() // returns the reference array of a innernode
	{
		return this.references;
	}
}