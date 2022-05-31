/**
 * A B+ tree leaf node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeLeafNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] values;
	
	public BPTreeLeafNode(int order) {
		this.m = order;
		
		// my strategy is first accounting for the overflow by making sure the key and references will only contain the keys relative to the degree passed in
		this.keys = new Object[m - 1];
		this.values = new Object[m - 1];
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

	/* --------------------------------------------- ALL MY FUNCTIONS --------------------------------------------- */

	public Object[] getValueArr() // returns the value array of a leafnode
	{
		return this.values;
	}
}
