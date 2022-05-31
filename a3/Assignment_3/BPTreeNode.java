/**
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> 
{	
	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level = 0; // DO NOT MODFIY THIS VARIABLE

	protected BPTreeNode() 
	{
		this.keyTally = 0;
		this.parentNode = null;
		this.leftSibling = null;
		this.rightSibling = null;
	}

	public int getKeyCount() 
	{
		return this.keyTally;
	}
	
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) 
	{
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) 
	{
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() 
	{
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) 
	{
		this.parentNode = parent;
	}	
	
	public abstract boolean isLeaf();
	
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node)
	{
		// Print all nodes in a subtree rooted with this node

		level++;
		if (node != null) 
		{
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
			// print all the subtrees rooted with this node.

			if (!node.isLeaf())
			{	
				BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>) node;

				for (int j = 0; j < (node.m); j++)
				{
					this.print((BPTreeNode<TKey, TValue>)inner.references[j]);
				}
			}
		}
		
		level--;
	}

	protected void printKeys()
	{
		// Print all the keys in this node

		System.out.print("[");
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}
	
	public TValue search(TKey key) 
	{
		return search(this, key);
	}

	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) 
	{
		if (key == null || value == null)
			return null;

		BPTreeNode<TKey, TValue> root = this;
		BPTreeNode<TKey, TValue> searchNode = searchInsert(root, key);

		if (searchNode == null)
			return null;

		if (!searchNode.isFull())
		{
			BPTreeLeafNode<TKey, TValue> searchLeaf = (BPTreeLeafNode<TKey, TValue>) searchNode;
			shiftLeaf(searchLeaf, key, value);

			return root;
		}

		if (searchNode.isFull())
		{
			root = splitNode(searchNode, key, value, null);
			return root;
		}	
		
		return null;
	}

	public BPTreeNode<TKey, TValue> delete(TKey key) 
	{
		if (key == null)
			return null;

		BPTreeNode<TKey, TValue> root = this;
		BPTreeNode<TKey, TValue> searchNode = searchDelete(root, key);

		if (searchNode == null)
			return null;

		shiftDeleteLeaf(searchNode, key); // delete first, check for underflow second
		
		int half = (int) Math.floor(searchNode.getKeyArr().length/2);

		if (!searchNode.hasParent() && searchNode.isLeaf())
		{
			return root;
		}
			
		else if (searchNode.getKeyCount() >= half)
		{
			return root;
		}
			
		else
		{
			root = mergeNode(searchNode, null);
			return root;
		}
	}

	@SuppressWarnings("unchecked")
	public TValue[] values() 
	{
		BPTreeNode<TKey, TValue> traverse = this;

		Object[] temp = null;
		int counter = 0;

		if (traverse.isLeaf() && !traverse.hasParent())
		{
			BPTreeLeafNode<TKey, TValue> yeet = (BPTreeLeafNode<TKey, TValue>) traverse;

			for (int i = 0; i < yeet.getKeyCount(); i++)
				counter++;

			temp = new Object[counter];

			for (int i = 0; i < yeet.getKeyCount(); i++)
				temp[i] = (TValue) yeet.getValueArr()[i];
	
			return (TValue[]) temp;
		}

		else
		{
			while (!traverse.isLeaf())
			{
				BPTreeInnerNode<TKey, TValue> yeet = (BPTreeInnerNode<TKey, TValue>) traverse;
				traverse = (BPTreeNode<TKey, TValue>) yeet.getRefArr()[0];
			}

			BPTreeLeafNode<TKey, TValue> startingPoint = (BPTreeLeafNode<TKey, TValue>) traverse;
			BPTreeLeafNode<TKey, TValue> traverseRight = (BPTreeLeafNode<TKey, TValue>) traverse;

			while (traverseRight != null)
			{
				for (int i = 0; i < traverseRight.getKeyCount(); i++)
					counter++;

				traverseRight = (BPTreeLeafNode<TKey, TValue>) traverseRight.rightSibling;
			}
			
			temp = new Object[counter];
			counter = 0;
			traverseRight = startingPoint;

			while (traverseRight != null)
			{
				for (int i = 0; i < traverseRight.getKeyCount(); i++)
					temp[counter++] = traverseRight.getValueArr()[i];

				traverseRight = (BPTreeLeafNode<TKey, TValue>) traverseRight.rightSibling;
			}
		}	

		return (TValue[]) temp;
	}




	/* --------------------------------------------- ALL MY FUNCTIONS --------------------------------------------- */

	/* ~~~~~~~~~~~~~~~~ My babies ~~~~~~~~~~~~~~~~ */

	public int getNumReferences()
	{
		/* returns the number of references a node has // this is very keyTally dependant, so make sure you handle key tallies properly in the code to avoid array out of bound errors */
		return this.keyTally + 1; 
	}

	public boolean hasParent() 
	{
		/* checks if the node has parent */

		if (this.parentNode == null)
			return false;

		else return true;
	}

	public boolean isFull() 
	{
		/* checks if the node is full */

		if (this.keyTally == this.m - 1)
			return true;

		else return false;
	}

	public boolean isEmpty() 
	{
		/* checks is the node is empty */ 

		if (this.keyTally == 0)
			return true;

		else return false;
	}

	public void setRightSibling(BPTreeNode<TKey, TValue> node) 
	{
		/* assigns a node to the node's rightSibling variable */ 

		this.rightSibling = node;
	}

	public void setLeftSibling(BPTreeNode<TKey, TValue> node) 
	{
		/* assigns a node to the node's leftSibling variable */ 
		
		this.leftSibling = node;
	}

	public void incrementKey() 
	{
		/* increments the key tally of a node */ 

		this.keyTally++;
	}

	public void decrementKey() 
	{
		/* decrements the key tally of a node */ 
		
		this.keyTally--;
	}

	public void setKey(int val) 
	{
		/* sets the key tally of a node to the value passed in */ 

		this.keyTally = val;
	}

	public Object[] getKeyArr() 
	{
		/* returns the key array of a node (for both inner and leaf nodes) */ 
		
		return this.keys;
	}




	/* ~~~~~~~~~~~~~~~~ Insert the boop of the snoot ~~~~~~~~~~~~~~~~ */
	/* 
	*	these are all the functions created specifically for insert, with some exceptions for deletion
	*/

	public void shiftLeaf(BPTreeLeafNode<TKey, TValue> node, TKey key, TValue value) 
	{
		/* takes a key and value, and inserts them into the correct postion in a leaf node */

		int position = 0;

		if (node.isEmpty()) // if there's no keys in the node we want to insert the 'key' & 'value' into
			position = 0;

		else 
		{
			if (node.getKeyCount() > 0)
			{
				position = searchPos(node, key); // else find the correct the position to insert into

				for (int k = node.getKeyCount(); k > position; k--) // shift everything else in the node to make space for the new key and value
				{
					node.getKeyArr()[k] = node.getKeyArr()[k-1];
					node.getValueArr()[k] = node.getValueArr()[k-1];
				}
			}
		}

		node.setKey(position, key);
		node.setValue(position, value);
		node.incrementKey();
	}

	public void shiftInner(BPTreeInnerNode<TKey, TValue> node, TKey them, BPTreeNode<TKey, TValue> childNode)
	{
		/* takes a key and a link to a child, and inserts them into the correct postion in an inner node */

		TKey key = null;

		if (them == null)
			key = childNode.getKey(0); // if no key was passed in, then this function was called when we had to redistribute nodes for deletion
		else key = them;

		int position = searchPos(node, key);

		for (int k = node.getKeyCount(); k > position; k--)
		{
			node.getKeyArr()[k] = node.getKeyArr()[k - 1];
			node.getRefArr()[k + 1] = node.getRefArr()[k];
		}

		node.setKey(position, key);
		node.setChild(position + 1, childNode);
		node.incrementKey();
		
		if (!childNode.isLeaf()) // this checks if this function was called for merging when deleting, if so, we shift the nodes to ensure there's no spaces in the node
		{
			BPTreeInnerNode<TKey, TValue> innerChildNode = (BPTreeInnerNode<TKey, TValue>) childNode;

			for (int i = 0; i < innerChildNode.getKeyCount(); i++)
				innerChildNode.getKeyArr()[i] = innerChildNode.getKeyArr()[i + 1];

			for (int x = 0; x < innerChildNode.getNumReferences(); x++)
				innerChildNode.getRefArr()[x] = innerChildNode.getRefArr()[x + 1];

			innerChildNode.decrementKey();
		}
	}

	public BPTreeNode<TKey, TValue> splitNode(BPTreeNode<TKey, TValue> node, TKey key, TValue value, BPTreeNode<TKey, TValue> child)
	{
		/* this function is the backbone of insertion, despite it's name, this function redistributes AND splits */

		if (node.isFull()) // since this is a recursive function, you need to make sure that the next node that gets passed through here is not full
		{
			if (node.isLeaf())
			{
				BPTreeLeafNode<TKey, TValue> leafNode = (BPTreeLeafNode<TKey, TValue>) node;
				BPTreeLeafNode<TKey, TValue> newLeaf = new BPTreeLeafNode<TKey, TValue>(leafNode.m);

				redistributeLeafNode(leafNode, newLeaf, key, value);

				if (leafNode.hasParent())
				{
					if (!leafNode.parentNode.isFull())
					{
						shiftInner(((BPTreeInnerNode<TKey, TValue>)leafNode.parentNode), null, newLeaf);
						setLeafSiblings(leafNode);
						setLeafSiblings(newLeaf);
					}
						
					else if (leafNode.parentNode.isFull())
					{
						return splitNode(leafNode.parentNode, newLeaf.getKey(0), null, newLeaf);
					}
				}

				else if (!leafNode.hasParent()) // if leaf is root node
				{
					BPTreeInnerNode<TKey, TValue> newRoot = new BPTreeInnerNode<TKey, TValue>(leafNode.m);
					shiftInner(newRoot, null, newLeaf);
					newRoot.setChild(1, newLeaf);
					newRoot.setChild(0, leafNode);

					setLeafSiblings(leafNode);
					setLeafSiblings(newLeaf);
					setInnerSiblings(newRoot);

					return newRoot;
				}
			}

			else if (!node.isLeaf())
			{
				BPTreeInnerNode<TKey, TValue> innerNode = (BPTreeInnerNode<TKey, TValue>) node;
				BPTreeInnerNode<TKey, TValue> newInner = new BPTreeInnerNode<TKey, TValue>(innerNode.m);

				redistributeInnerNode(innerNode, newInner, key, child);

				if (child.isLeaf())
				{
					BPTreeLeafNode<TKey, TValue> temp = (BPTreeLeafNode<TKey, TValue>) child;
					setLeafSiblings(temp);
				}
				else
				{
					BPTreeInnerNode<TKey, TValue> temp = (BPTreeInnerNode<TKey, TValue>) child;
					setInnerSiblings(temp);
				}

				if (innerNode.hasParent())
				{
					if (!innerNode.parentNode.isFull())
					{
						key = (TKey) newInner.getKeyArr()[0];
						shiftInner(((BPTreeInnerNode<TKey, TValue>)innerNode.parentNode), key, newInner);
						setInnerSiblings(innerNode);
						setInnerSiblings(newInner);
					}

					else if (innerNode.parentNode.isFull())
						return splitNode(innerNode.parentNode, newInner.getKey(0), null, newInner);
				}

				else if (!innerNode.hasParent())
				{
					BPTreeInnerNode<TKey, TValue> newRoot = new BPTreeInnerNode<TKey, TValue>(innerNode.m);
					shiftInner(newRoot, null, newInner);
					newRoot.setChild(0, innerNode);

					setInnerSiblings(newRoot);
					return newRoot;
				}
			}
		}

		BPTreeNode<TKey, TValue> traverse = node; // after all the required computations, we need to return the root back to the insert function
		while (traverse.hasParent())
			traverse = traverse.parentNode;

		return traverse;
	}




	/* ~~~~~~~~~~~~~~~~ Queer legends ~~~~~~~~~~~~~~~~ */
	/* 
	*	these functions are used for both deletion and insertion
	*	there's multiple if statements in these functions to help determine what the function was called to do
	*/

	public BPTreeLeafNode<TKey, TValue> createLeafList(BPTreeLeafNode<TKey, TValue> node1, BPTreeLeafNode<TKey, TValue> node2, TKey key, TValue val)
	{
		/* can be called by either a delete or insert function, accomodates for both */
		
		if (node2 == null) // this section is designed for insertion at leaf node level
		{
			BPTreeLeafNode<TKey, TValue> list = new BPTreeLeafNode<TKey, TValue>(node1.m + 1);

			for (int i = 0; i < node1.getKeyCount(); i++)
			{
				list.getKeyArr()[i] = node1.getKeyArr()[i];
				list.getValueArr()[i] = node1.getValueArr()[i];
				list.incrementKey();
			}

			shiftLeaf(list, key, val);
			return list;

		}

		else if (node2 != null) // will always be called on two nodes that are less than half full // this section is designed for deletion at leaf node level
		{
			BPTreeLeafNode<TKey, TValue> list = new BPTreeLeafNode<TKey, TValue>(node1.m);

			for (int i = 0; i < node1.getKeyCount(); i++) // take from node1
			{
				list.getKeyArr()[i] = node1.getKeyArr()[i];
				list.getValueArr()[i] = node1.getValueArr()[i];
				list.incrementKey();
			}

			int counter = 0;
			int index = list.getKeyCount();
			while (counter < node2.getKeyCount() && index < list.getKeyArr().length) // take from node2
			{
				list.getKeyArr()[index] = node2.getKeyArr()[counter];
				list.getValueArr()[index++] = node2.getValueArr()[counter++];
				list.incrementKey();
			}

			return list;
		}

		return null;
	}

	public BPTreeInnerNode<TKey, TValue> createInnerList(BPTreeInnerNode<TKey, TValue> node1, BPTreeInnerNode<TKey, TValue> node2, TKey key, BPTreeNode<TKey, TValue> child)
	{
		/* can be called by either a delete or insert function, accomodates for both */

		if (node2 == null) // designed for insertion for non-leaves
		{
			BPTreeInnerNode<TKey, TValue> list = new BPTreeInnerNode<TKey, TValue>(node1.m + 1);

			for (int i = 0; i < node1.getKeyCount(); i++)
			{
				list.getKeyArr()[i] = node1.getKeyArr()[i];
				list.incrementKey();
			}

			for (int k = 0; k < node1.getNumReferences(); k++)
				list.getRefArr()[k] = node1.getRefArr()[k];

			shiftInner(list, key, child);

			return list;
		}

		else if (node2 != null) // designed for deletion for non-leaves
		{
			if (key == null) // if there was no key passed in then we have to create a list for a delete function that merges nodes at leaf level
			{
				BPTreeInnerNode<TKey, TValue> list = new BPTreeInnerNode<TKey, TValue>(node1.m);

				for (int i = 0; i < node1.getKeyCount(); i++)
				{
					list.getKeyArr()[i] = node1.getKeyArr()[i];
					list.incrementKey();
				}

				for (int k = 0; k < node1.getNumReferences(); k++)
					list.getRefArr()[k] = node1.getRefArr()[k];

				int counter = 0;
				int index = list.getKeyCount();

				while (counter < node2.getKeyCount() && index < list.getKeyArr().length)
				{
					list.getKeyArr()[index++] = node2.getKeyArr()[counter++];
					list.incrementKey();
				}

				counter = 0;
				index = list.getNumReferences();
				while (counter < node2.getNumReferences() && index < list.getRefArr().length)
					list.getRefArr()[index++] = node2.getRefArr()[counter++];

				return list;
			}

			else if (key != null) // if there was a key passed in then we have to create a list for a delete function that merges nodes at non-leaf level
			{
				BPTreeInnerNode<TKey, TValue> list = new BPTreeInnerNode<TKey, TValue>(node1.m);

				for (int i = 0; i < node1.getKeyCount(); i++)
				{
					list.getKeyArr()[i] = node1.getKeyArr()[i];
					list.incrementKey();
				}

				for (int k = 0; k < node1.getNumReferences(); k++)
					list.getRefArr()[k] = node1.getRefArr()[k];

				int counter = 0;
				int index = list.getKeyCount();
				while (counter < node2.getKeyCount() && index < list.getKeyArr().length)
				{
					list.getKeyArr()[index++] = node2.getKeyArr()[counter++];
				}

				counter = 0;
				index = list.getNumReferences();
				while (counter < node2.getNumReferences() && index < list.getRefArr().length)
				{
					list.getRefArr()[index++] = node2.getRefArr()[counter++];
	
					if (node2.getKeyCount() != 0)
						list.incrementKey();	
				}

				if (node2.getKeyCount() != 0)
					list.decrementKey();

				shiftingForInnerList(list, key);
					
				return list;
			}
		}

		return null; // safety net
	}

	public void shiftingForInnerList(BPTreeInnerNode<TKey, TValue> node, TKey key)
	{
		/* takes a key and inserts it into the correct position in the passed in innernode */

		int position = searchPos(node, key);

		for (int k = node.getKeyCount(); k > position; k--)
			node.getKeyArr()[k] = node.getKeyArr()[k - 1];

		node.setKey(position, key);
		node.incrementKey();
	}

	public void redistributeLeafNode(BPTreeLeafNode<TKey, TValue> node1, BPTreeLeafNode<TKey, TValue> node2, TKey key, TValue val)
	{
		/* takes two nodes and redistributes the values equally amongst the two */

		if (key != null && val != null) // prepare redistribute for insert
		{			
			BPTreeLeafNode<TKey, TValue> list = createLeafList(node1, null, key, val);

			double middleP = Math.floor(list.getKeyCount()/2);
			int middlePos = (int) middleP;
	
			int counter = 0;
			int index = middlePos;
	
			/* -------------------------------------------- DEALING WITH NODE 2------------------------------------------------------- */
			while (counter < node2.getKeyArr().length && index < list.getKeyCount()) // copies half of the values from the list to node2
			{
				node2.getKeyArr()[counter] = list.getKeyArr()[index];
				node2.getValueArr()[counter++] = list.getValueArr()[index++];
				node2.incrementKey();
			}
	
			/* -------------------------------------------- DEALING WITH NODE 1------------------------------------------------------- */
			for (int i = 0; i < middlePos; i++) // updating node1 just in case the list added something that belongs to node1
			{
				node1.getKeyArr()[i] = list.getKeyArr()[i];
				node1.getValueArr()[i] = list.getValueArr()[i];
			}
	
			for (int i = middlePos; i < node1.getKeyArr().length; i++) // removing keys that were given to node2 in node1
			{
				node1.getKeyArr()[i] = null;
				node1.getValueArr()[i] = null;
				node1.decrementKey();
			}

			setLeafSiblings(node1);
		}

		else if (key == null && val == null) // prepare redistribute for delete
		{
			BPTreeLeafNode<TKey, TValue> list = createLeafList(node1, node2, null, null);

			int middlePos = 0;

			if (node2.getKeyCount() == 0)
			{
				if (node1.getKeyCount() % 2 != 0)
					middlePos = (int) Math.floor(list.getKeyCount()/2) + 1;	

				else if (node1.getKeyCount() % 2 == 0)
					middlePos = (int) Math.floor(list.getKeyCount()/2);	
			}

			else middlePos = (int) Math.floor(list.getKeyCount()/2);

			for (int c = 0; c < node1.getKeyArr().length; c++) // wipe node1 clean
			{
				if (node1.getKeyArr()[c] != null)
				{
					node1.getKeyArr()[c] = null;
					node1.decrementKey();
				}
			}

			for (int c = 0; c < node2.getKeyArr().length; c++) // wipe node2 clean
			{
				if (node2.getKeyArr()[c] != null)
				{
					node2.getKeyArr()[c] = null;
					node2.decrementKey();
				}
			}

			/* -------------------------------------------- DEALING WITH NODE 1 ------------------------------------------------------- */
			for (int i = 0; i < middlePos; i++) // updating node1 just in case the list added something that belongs to node1
			{
				node1.getKeyArr()[i] = list.getKeyArr()[i];
				node1.getValueArr()[i] = list.getValueArr()[i];
				node1.incrementKey();
			}
			
	
			/* -------------------------------------------- DEALING WITH NODE 2 ------------------------------------------------------- */
			int counter = 0;
			int index = middlePos;

			while (counter < node2.getKeyArr().length && index < list.getKeyCount()) // copies half of the values from the list to node2
			{
				node2.getKeyArr()[counter] = list.getKeyArr()[index];
				node2.getValueArr()[counter++] = list.getValueArr()[index++];
				node2.incrementKey();
			}
		}
	}

	public void redistributeInnerNode(BPTreeInnerNode<TKey, TValue> node1, BPTreeInnerNode<TKey, TValue> node2, TKey key, BPTreeNode<TKey, TValue> child)
	{
		/* takes two nodes and redistributes the values equally amongst the two */

		BPTreeInnerNode<TKey, TValue> list = createInnerList(node1, null, key, child);
		double middleP = Math.floor(list.getKeyCount()/2);
		int middlePos = (int) middleP;

		int counter = 0; 
		int index = middlePos;

		/* -------------------------------------------- DEALING WITH NODE 2------------------------------------------------------- */
		while (index < list.getKeyCount()) // copying keys from second half of list to node2
		{
			node2.getKeyArr()[counter++] = list.getKeyArr()[index++];
			node2.incrementKey();
		}

		counter = 1;
		index = middlePos + 1;

		while (index < list.getNumReferences()) // copying references from second half of list to node2
		{
			node2.getRefArr()[counter] = list.getRefArr()[index];
			node2.setChild(counter, node2.getChild(counter));
			counter++;
			index++;
		}

		setInnerSiblings(node2);

		/* -------------------------------------------- DEALING WITH NODE 1------------------------------------------------------- */
		for (int i = 0; i < middlePos; i++) // copying keys from first half of list to node1
			node1.getKeyArr()[i] = list.getKeyArr()[i];

		for (int i = 0; i <= middlePos; i++) // copying references from first half of list to node1
		{
			node1.getRefArr()[i] = list.getRefArr()[i];
			node1.setChild(i, node1.getChild(i));
		}
		
		for (int i = middlePos; i < node1.getKeyArr().length; i++) // clearing all keys in node that were sent to node2
		{
			node1.getKeyArr()[i] = null;
			node1.decrementKey();
		}

		for (int p = middlePos + 1; p < node1.getRefArr().length; p++) // clearing all references in node that were sent to node2
			node1.getRefArr()[p] = null;

		setInnerSiblings(node1);
	}




	/* ~~~~~~~~~~~~~~~~ Searching for who asked ~~~~~~~~~~~~~~~~ */
	/* 
	*	these functions are used to find keys, nodes, etc.
	*/

	public TValue search(BPTreeNode<TKey, TValue> node, TKey key)
	{
		/* is passed the root of the tree and the key to search for */

		if (!node.hasParent() && node.isLeaf()) // root is the only node in the tree
		{
			BPTreeLeafNode<TKey, TValue> leafnode = (BPTreeLeafNode<TKey, TValue>) node;

			for (int i = 0; i < leafnode.getKeyCount(); i++)
			{
				if (((TKey) leafnode.getKeyArr()[i]).equals(key))
					return ((TValue) leafnode.getValueArr()[i]);
			}
		}

		else
		{
			for (int i = 0; i < node.getKeyCount(); i++)
			{
				if (((TKey) node.getKeyArr()[i]).equals(key) && node.isLeaf())
				{
					BPTreeLeafNode<TKey, TValue> leafnode = (BPTreeLeafNode<TKey, TValue>) node;
					return ((TValue) leafnode.getValueArr()[i]);
				}

				else if (((TKey) node.getKeyArr()[i]).equals(key) && !node.isLeaf())
				{
					BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
					return search(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[i + 1]), key);
				}

				else if (((TKey) node.getKeyArr()[i]).compareTo(key) > 0 && !node.isLeaf())
				{
					BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
					return search(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[i]), key);
				}
			}

			if (((TKey) node.getKeyArr()[node.getKeyCount() - 1]).compareTo(key) < 0 && !node.isLeaf())
			{
				BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
				return search(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[node.getNumReferences() - 1]), key);
			}
		}

		return null; // key not found
	}

	public BPTreeNode<TKey, TValue> searchDelete(BPTreeNode<TKey, TValue> node, TKey key)
	{
		/* is passed the root and the key to delete, after it finds the key it returns the node that key is in */

		if (!node.hasParent() && node.isLeaf()) // root is the only node in the tree
		{
			int check = searchDeletePos(node, key);
			if (check == -1)
				return null;

			return node;
		}

		else
		{
			for (int i = 0; i < node.getKeyCount(); i++)
			{
				if (((TKey) node.getKeyArr()[i]).equals(key) && node.isLeaf()) // reached leaf level
				{
					int check = searchDeletePos(node, key);
					if (check == -1)
						return null;

					return node;
				}		
				
				else if (((TKey) node.getKeyArr()[i]).equals(key) && !node.isLeaf())
				{
					BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
						return searchDelete(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[i + 1]), key);
				}

				else if (((TKey) node.getKeyArr()[i]).compareTo(key) > 0 && !node.isLeaf())
				{
					BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
						return searchDelete(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[i]), key);
				}
			}

			if (((TKey) node.getKeyArr()[node.getKeyCount() - 1]).compareTo(key) < 0 && !node.isLeaf())
			{
				BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
					return searchDelete(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[node.getNumReferences() - 1]), key);
			}
		}

		return null; // key not found
	}

	public BPTreeNode<TKey, TValue> searchInsert(BPTreeNode<TKey, TValue> node, TKey key)
	{
		/* is passed the root and the key to find a place for, after it finds a postion to insert the key in, it returns the node that the postion is in */

		if (!node.hasParent() && node.isLeaf())
			return node;

		else if (node.isLeaf())
			return node;

		else if (!node.isLeaf())
		{
			BPTreeInnerNode<TKey, TValue> innerNode = (BPTreeInnerNode<TKey, TValue>) node;
			
			for (int i = 0; i < innerNode.getKeyCount(); i++)
			{
				if (((TKey) innerNode.getKeyArr()[i]).compareTo(key) > 0)
					return searchInsert(((BPTreeNode<TKey, TValue>) innerNode.getRefArr()[i]), key);

				// else if (((TKey) node.getKeyArr()[i]).equals(key) && !node.isLeaf()) // THERE WONT BE DUPLICATES WE HAVE TO CHECK FOR
				// {
				// 	BPTreeInnerNode<TKey, TValue> innernode = (BPTreeInnerNode<TKey, TValue>) node;
				// 		return searchInsert(((BPTreeNode<TKey, TValue>) innernode.getRefArr()[i + 1]), key);
				// }
			}

			if (((TKey) innerNode.getKeyArr()[innerNode.getKeyCount() - 1]).compareTo(key) < 0)
				return searchInsert(((BPTreeNode<TKey, TValue>) innerNode.getRefArr()[innerNode.getNumReferences() - 1]), key);
		}

		return null;
	}

	public int searchDeletePos(BPTreeNode<TKey, TValue> node, TKey key)
	{
		/* finds the postion in a node in which the key resides and returns that position */

		int position = -1;

		for (int i = 0; i < node.getKeyCount(); i++)
		{
			if (((TKey) node.getKeyArr()[i]).equals(key))
			{
				position = i;
				break;
			}
		}
	
		return position;
	}

	public int searchPos(BPTreeNode<TKey, TValue> node, TKey key)
	{
		/* is passed the root and the key to delete, after it finds the key it returns the node that key is in */

		int position = 0;

		if (node.getKeyCount() == 0)
			return position;

		for (int i = 0; i < node.getKeyCount(); i++)
		{
			if (((TKey) node.getKeyArr()[i]).compareTo(key) > 0)
			{
				position = i;
				break;
			}
		}

		if (((TKey) node.getKeyArr()[node.getKeyCount() - 1]).compareTo(key) < 0)
		{
			position = node.getKeyCount();
		}

		return position;
	}




	/* ~~~~~~~~~~~~~~~~ Family family family family family family family family family family family ~~~~~~~~~~~~~~~~ */
	/* 
	*	these functions are used to assign siblings to every single node in the tree and maintain those sibling relationships during deletion
	*/

	public void maintainLinkedList(BPTreeLeafNode<TKey, TValue> node) // this is called when you have to delete a node not directly beneath the root
	{
		/* if a node is part of linked list and is not connected directly to the root, this function maintains the links between its siblings and gets used in deletion */ 

		if (node.leftSibling != null)
		node.leftSibling.rightSibling = node.rightSibling;
	
		if (node.rightSibling != null)
			node.rightSibling.leftSibling = node.leftSibling;

		node.leftSibling = null;
		node.rightSibling = null;
	}

	public void setLeafSiblings(BPTreeLeafNode<TKey, TValue> node) // used for insertion
	{
		/* sets an leaf node's left & right siblings relative to its parent, this function is often used in insertion */ 

		if (node.hasParent())
		{
			BPTreeInnerNode<TKey, TValue> parent = (BPTreeInnerNode<TKey, TValue>) node.parentNode;
			
			int position = 0;

			for (int i = 0; i < parent.getNumReferences(); i++)
			{
				if (parent.getRefArr()[i] == node)
				{
					position = i;
					break;
				}
			}

			if (node.rightSibling != null)
				node.rightSibling.leftSibling = null;

			if (node.leftSibling != null)
				node.leftSibling.rightSibling = null;

			node.setLeftSibling(null);
			node.setRightSibling(null);

			if (position < parent.getNumReferences() - 1)
			{
				BPTreeNode<TKey, TValue> sibling = (BPTreeNode<TKey, TValue>) parent.getRefArr()[position + 1];

				node.setRightSibling(sibling);
				sibling.setLeftSibling(node);
			}

			if (position > 0)
			{
				BPTreeNode<TKey, TValue> sibling = (BPTreeNode<TKey, TValue>) parent.getRefArr()[position - 1];

				node.setLeftSibling(sibling);
				sibling.setRightSibling(node);
			}
		}
	}

	public void setInnerSiblings(BPTreeInnerNode<TKey, TValue> node)
	{
		/* sets an inner node's left & right siblings relative to its parent, this function is often used in insertion */ 
		
		if (node.hasParent())
		{
			BPTreeInnerNode<TKey, TValue> parent = (BPTreeInnerNode<TKey, TValue>) node.parentNode;

			int position = 0;

			for (int i = 0; i < parent.getNumReferences(); i++)
			{
				if (parent.getRefArr()[i] == node)
				{
					position = i;
					break;
				}
			}

			if (node.rightSibling != null)
				node.rightSibling.leftSibling = null;

			if (node.leftSibling != null)
				node.leftSibling.rightSibling = null;

			node.setLeftSibling(null);
			node.setRightSibling(null);

			if (position < parent.getNumReferences() - 1)
			{
				BPTreeNode<TKey, TValue> sibling = (BPTreeNode<TKey, TValue>) parent.getRefArr()[position + 1];
				node.setRightSibling(sibling);
				sibling.setLeftSibling(node);
			}

			if (position > 0)
			{
				BPTreeNode<TKey, TValue> sibling = (BPTreeNode<TKey, TValue>) parent.getRefArr()[position - 1];
				node.setLeftSibling(sibling);
				sibling.setRightSibling(node);
			}
		}

		else if (!node.hasParent())
		{
			node.rightSibling = null;
			node.leftSibling = null;

			BPTreeNode<TKey, TValue> node1 = (BPTreeNode<TKey, TValue>) node.getRefArr()[0];
			BPTreeNode<TKey, TValue> node2 = (BPTreeNode<TKey, TValue>) node.getRefArr()[1];

			if (node1 != null && node2 != null)
			{
				node1.setRightSibling(node2);
				node2.setLeftSibling(node1);
			}
		}
	}




	/* ~~~~~~~~~~~~~~~~ I fucking hated implementing deletion for this god forsaken assignment ~~~~~~~~~~~~~~~~ */
	/* 
	*	these are all the functions created specifically for dele
	*/

	public BPTreeNode<TKey, TValue> mergeNode(BPTreeNode<TKey, TValue> node, BPTreeNode<TKey, TValue> child)
	{
		/* similar to the 'splitNode' function, this function serves as the backbone for the delete function */

		int half = (int) Math.floor(node.getKeyArr().length/2);

		if (node.getKeyCount() < half) // since this is a recursive function, you need to make sure that the next node that gets passed through this block is than half full
		{
			if (node.isLeaf())
			{
				BPTreeLeafNode<TKey, TValue> currLeafNode = (BPTreeLeafNode<TKey, TValue>) node;

				if (node.leftSibling != null && node.leftSibling.getKeyCount() > half)
				{
					BPTreeLeafNode<TKey, TValue> leftNodeSibling = (BPTreeLeafNode<TKey, TValue>) node.leftSibling;

					redistributeLeafNode(leftNodeSibling, currLeafNode, null, null);

					updateMother(currLeafNode);

					if (currLeafNode.parentNode != leftNodeSibling.parentNode)
					{	
						BPTreeNode<TKey, TValue> upToTheRootBoiz = (BPTreeNode<TKey, TValue>) currLeafNode;
						updateTheRootForTheBullshitDistribute(upToTheRootBoiz.parentNode, upToTheRootBoiz, (TKey) upToTheRootBoiz.getKeyArr()[0]);
					}
				}

				else if (node.rightSibling != null && node.rightSibling.getKeyCount() > half)
				{
					BPTreeLeafNode<TKey, TValue> rightNodeSibling = (BPTreeLeafNode<TKey, TValue>) node.rightSibling;

					redistributeLeafNode(currLeafNode, rightNodeSibling, null, null);
					updateMother(rightNodeSibling);

					if (rightNodeSibling.parentNode != currLeafNode.parentNode)
					{	
						BPTreeNode<TKey, TValue> upToTheRootBoiz = (BPTreeNode<TKey, TValue>) rightNodeSibling;
						updateTheRootForTheBullshitDistribute(upToTheRootBoiz.parentNode, upToTheRootBoiz, (TKey) upToTheRootBoiz.getKeyArr()[0]);
					}
				}
				
				else // if neither sibling is more than half full
				{
					if (node.leftSibling != null && currLeafNode.parentNode == currLeafNode.leftSibling.parentNode)
					{
						node = mergingschmerging(node.leftSibling, node, true);

						if (node.hasParent() && node.parentNode.keyTally <= half)
							return mergeNode(node.parentNode, node);
					}
						

					else if (node.rightSibling != null && currLeafNode.parentNode == currLeafNode.rightSibling.parentNode)
					{
						node = mergingschmerging(node, node.rightSibling, false);						

						if (node.hasParent() && node.parentNode.keyTally <= half)
							return mergeNode(node.parentNode, node);
					}
				}
			}

			else if (!node.isLeaf())
			{	
				if (node.leftSibling != null && node.leftSibling.getKeyCount() > half) // NOTHING IS DELETED!!!! .: LINKED LISTS WILL ALWAYS BE MAINTAINED
					AVLTreesMyEnemy((BPTreeInnerNode<TKey, TValue>) node.leftSibling, (BPTreeInnerNode<TKey, TValue>) node.parentNode, (BPTreeInnerNode<TKey, TValue>) node, true);

				else if (node.rightSibling != null && node.rightSibling.getKeyCount() > half)
					AVLTreesMyEnemy((BPTreeInnerNode<TKey, TValue>) node, (BPTreeInnerNode<TKey, TValue>) node.parentNode, (BPTreeInnerNode<TKey, TValue>) node.rightSibling, false);

				else // neither sibling is more than half full
				{
					if (node.leftSibling != null && node.parentNode == node.leftSibling.parentNode)
					{
						BPTreeInnerNode<TKey, TValue> temp = creatingListIWantToDie((BPTreeInnerNode<TKey, TValue>) node.leftSibling, (BPTreeInnerNode<TKey, TValue>) node.parentNode, (BPTreeInnerNode<TKey, TValue>) node, true);
					
						if (temp.parentNode != null)
							return mergeNode(temp.parentNode, temp);

						else node = temp;
					}
						

					else if (node.rightSibling != null && node.parentNode == node.rightSibling.parentNode)
					{
						BPTreeInnerNode<TKey, TValue> temp = creatingListIWantToDie((BPTreeInnerNode<TKey, TValue>) node, (BPTreeInnerNode<TKey, TValue>) node.parentNode, (BPTreeInnerNode<TKey, TValue>) node.rightSibling, false);
					
						if (temp.parentNode != null)
							return mergeNode(temp.parentNode, temp);

						else node = temp;
					}	
				}
			}
		}

		BPTreeNode<TKey, TValue> traverse = node;
		while (traverse.hasParent())
			traverse = traverse.parentNode;

		return traverse;
	}

	public void updateTheRootForTheBullshitDistribute(BPTreeNode<TKey, TValue> currNode, BPTreeNode<TKey, TValue> child, TKey beegBoi)
	{
		/* 
			there's sometimes the possibility that a node will redistribute keys with a sibling that does not have the same parent, in such a scenario, we have to propogate up to the root to update the separator key
			such that it still acts as a valid search guide
			updates the corresponding separator key in root
		*/

		if (!currNode.hasParent())
		{
			BPTreeInnerNode<TKey, TValue> rootNode = (BPTreeInnerNode<TKey, TValue>) currNode;
			int position = 0;

			for (int i = 0; i < rootNode.getNumReferences(); i++)
			{
				if (rootNode.getRefArr()[i] == child)
				{
					position = i;
					break;
				}
			}

			rootNode.setKey(position - 1, beegBoi);
		}

		else if (currNode.hasParent()) 
			updateTheRootForTheBullshitDistribute(currNode.parentNode, currNode, beegBoi);
	}

	public BPTreeNode<TKey, TValue> mergingschmerging(BPTreeNode<TKey, TValue> node1, BPTreeNode<TKey, TValue> node2, boolean isLeft) 
	{
		/* 
			this functions handles all the test cases for when you have to merge nodes after deletion
			isLeft lets us know if the sibling we're merging with is on the left of the currNode
			merges nodes on leaf level // has the chance to make the parent underflow
		*/

		if (node1.isLeaf() && node2.isLeaf() && node1.hasParent() && node2.hasParent()) // make sure we're not dealing with the root in any way // root has its own way of dealing with deletes
		{
			if (isLeft) // parent node is shifted AND SEPARATOR KEY IS UPDATED // currNode's index will never be 0
			{
				/*
					node1 = leftSibling
					node2 = currNode
				*/

				BPTreeLeafNode<TKey, TValue> leftNode = (BPTreeLeafNode<TKey, TValue>) node1;
				BPTreeLeafNode<TKey, TValue> currNode = (BPTreeLeafNode<TKey, TValue>) node2;

				BPTreeLeafNode<TKey, TValue> list = createLeafList(leftNode, currNode, null, null);

				weInTheEndGameNow(list, leftNode);

				// update separator key and make sure parent no longer points to currNode
				BPTreeInnerNode<TKey, TValue> parentYeet = (BPTreeInnerNode<TKey, TValue>) currNode.parentNode;
				int position = shiftDeleteInner(parentYeet, currNode);

				BPTreeLeafNode<TKey, TValue> shiftedNode = (BPTreeLeafNode<TKey, TValue>) parentYeet.getRefArr()[position - 1];
				
				if (position - 2 >= 0)
					parentYeet.getKeyArr()[position - 2] = shiftedNode.getKeyArr()[0]; // update the separator key to the key of the 1st index in the child

				if (!leftNode.parentNode.hasParent()) // node's parent are the root
				{
					BPTreeInnerNode<TKey, TValue> root = (BPTreeInnerNode<TKey, TValue>) node1.parentNode;

					if (root.getKeyCount() == 0)
					{
						root = null;
						leftNode.parentNode = null;
					}
				}
				return (BPTreeNode<TKey, TValue>) leftNode;
			}
	
			else if (!isLeft) // parent node is shifted AND SEPARATOR KEY IS !!NOT!! UPDATED // currNode's index will have the chance to be 0
			{
				/*
					node1 = currNode
					node2 = rightSibling
				*/

				BPTreeLeafNode<TKey, TValue> currNode = (BPTreeLeafNode<TKey, TValue>) node1;
				BPTreeLeafNode<TKey, TValue> rightNode = (BPTreeLeafNode<TKey, TValue>) node2;
				BPTreeLeafNode<TKey, TValue> list = createLeafList(currNode, rightNode, null, null);

				weInTheEndGameNow(list, currNode);

				// Do not update separator key and make sure parent no longer points to rightNode
				BPTreeInnerNode<TKey, TValue> parentYeet = (BPTreeInnerNode<TKey, TValue>) rightNode.parentNode;
				int position = shiftDeleteInner(parentYeet, rightNode);

				if (!currNode.parentNode.hasParent()) // node's parent are the root
				{
					BPTreeInnerNode<TKey, TValue> root = (BPTreeInnerNode<TKey, TValue>) node1.parentNode;

					if (root.getKeyCount() == 0)
					{
						root = null;
						currNode.parentNode = null;
					}
				}

				return (BPTreeNode<TKey, TValue>) currNode;
			}
		}

		return null;
	}

	public void weInTheEndGameNow(BPTreeLeafNode<TKey, TValue> list, BPTreeLeafNode<TKey, TValue> node) 
	{
		/* 
			this function is used to populate a node with a list when merging at leaf level
			list = list of combined node1 & node2
			node = node to transfer list to
		*/

		for (int i = 0; i < node.getKeyCount(); i++) // first wipe the currNode/ leftSibling clean
		{
			node.getKeyArr()[i] = null;
			node.getValueArr()[i] = null;
			node.decrementKey();
		}

		for (int i = 0; i < list.getKeyCount(); i++) // repopulate currNode/ leftSibling with new values and keys
		{
			node.getKeyArr()[i] = list.getKeyArr()[i];
			node.getValueArr()[i] = list.getValueArr()[i];
			node.incrementKey();
		}
	}

	public void shiftDeleteLeaf(BPTreeNode<TKey, TValue> node, TKey key)
	{
		/*
			this function is used to delete a key and its corresponding value in a node
			this function DOES NOT check for overflow, the OG delete function does that
		*/

		BPTreeLeafNode<TKey, TValue> leafNode = (BPTreeLeafNode<TKey, TValue>) node;

		if (leafNode.getKeyCount() == 1)
		{
			leafNode.getKeyArr()[0] = null;
			leafNode.decrementKey();
			return;
		}

		int position = searchDeletePos(leafNode, key);
		leafNode.getKeyArr()[position] = null;

		if (leafNode.isFull())
		{
			for (int i = position; i < leafNode.getKeyCount() - 1; i++)
			{
				leafNode.getKeyArr()[i] = leafNode.getKeyArr()[i + 1];
				leafNode.getValueArr()[i] = leafNode.getValueArr()[i + 1];
			}

			leafNode.getKeyArr()[leafNode.getKeyCount() - 1] = null;
			leafNode.getValueArr()[leafNode.getKeyCount() - 1] = null;
		}

		else
		{
			for (int i = position; i < leafNode.getKeyCount(); i++)
			{
				leafNode.getKeyArr()[i] = leafNode.getKeyArr()[i + 1];
				leafNode.getValueArr()[i] = leafNode.getValueArr()[i + 1];
			}	
		}

		leafNode.decrementKey();
	}

	public int shiftDeleteInner(BPTreeNode<TKey, TValue> node1, BPTreeNode<TKey, TValue> node2)
	{
		/* 
			this function is called after when we have to merge 2 inner nodes
			node1 = parent
			node2 = child to search for
		*/ 

		BPTreeInnerNode<TKey, TValue> parentYeet = (BPTreeInnerNode<TKey, TValue>) node1;
		BPTreeLeafNode<TKey, TValue> childNode = (BPTreeLeafNode<TKey, TValue>) node2; // childNode was rightNode
		int position = 0;

		for (int i = 0; i < parentYeet.getNumReferences(); i++) // find the parent's position in the index set
		{
			if (parentYeet.getRefArr()[i] == childNode)
			{
				position = i;
				break;
			}
		}

		parentYeet.getRefArr()[position] = null;
		childNode.setParent(null);
		parentYeet.getKeyArr()[position - 1] = null;

		maintainLinkedList(childNode); // delete currNode from list
		childNode = null;

		if (parentYeet.isFull())
		{
			for (int x = position - 1; x < parentYeet.getKeyCount() - 1; x++)
				parentYeet.getKeyArr()[x] = parentYeet.getKeyArr()[x + 1];
			parentYeet.getKeyArr()[parentYeet.getKeyCount() - 1] = null;

			for (int y = position; y < parentYeet.getNumReferences() - 1; y++)
				parentYeet.getRefArr()[y] = parentYeet.getRefArr()[y + 1];
			parentYeet.getRefArr()[parentYeet.getNumReferences() - 1] = null;
		}

		else if (!parentYeet.isFull())
		{
			for (int x = position - 1; x < parentYeet.getKeyCount(); x++) // move keys
				parentYeet.getKeyArr()[x] = parentYeet.getKeyArr()[x + 1];

			for (int y = position; y < parentYeet.getNumReferences(); y++) // move references
				parentYeet.getRefArr()[y] = parentYeet.getRefArr()[y + 1];
		}

		parentYeet.decrementKey();

		return position;
	}
	
	public void updateMother(BPTreeNode<TKey, TValue> child)
	{
		/* 
			this function will always be given a child not in index [0] // will always be called when there is no overflow in a node
			just updates the separator key in the immediate parent after a redistribution
		*/

		BPTreeInnerNode<TKey, TValue> parentYeet = (BPTreeInnerNode<TKey, TValue>) child.parentNode;
		BPTreeLeafNode<TKey, TValue> childNode = (BPTreeLeafNode<TKey, TValue>) child;
		int position = 0;

		for (int i = 0; i < parentYeet.getNumReferences(); i++) // find the child's position in the parent
		{
			if (parentYeet.getRefArr()[i] == childNode)
			{
				position = i;
				break;
			}
		}

		parentYeet.getKeyArr()[position - 1] = childNode.getKeyArr()[0]; // update the separator key
	}

	public void shiftInnerRefs(BPTreeInnerNode<TKey, TValue> node, TKey key, BPTreeNode<TKey, TValue> child, boolean isLeft)
	{
		/* this function is called when you have to redistribute keys between inner nodes */

		if (isLeft)
		{
			for (int k = 0; k < node.getRefArr().length - 1; k++)
				node.getRefArr()[k + 1] = node.getRefArr()[k];

			node.getRefArr()[0] = null;

			for (int i = 0; i < node.getKeyArr().length - 1; i++)
				node.getKeyArr()[i + 1] = node.getKeyArr()[i];

			node.setKey(0, key);
			node.setChild(0, child);

			node.incrementKey();
		}

		else if (!isLeft)
		{
			if (node.isFull())
			{
				for (int k = 0; k < node.getNumReferences() - 1; k++)
					node.getRefArr()[k] = node.getRefArr()[k + 1];
				node.getRefArr()[node.getNumReferences() - 1] = null;

				for (int i = 0; i < node.getKeyCount() - 1; i++)
					node.getKeyArr()[i] = node.getKeyArr()[i + 1];
				node.getKeyArr()[node.getKeyCount() - 1] = null;
			}
			
			else if (!node.isFull())
			{
				for (int k = 0; k < node.getNumReferences(); k++)
				node.getRefArr()[k] = node.getRefArr()[k + 1];

				for (int i = 0; i < node.getKeyCount(); i++)
					node.getKeyArr()[i] = node.getKeyArr()[i + 1];
			}

			node.decrementKey();
		}
	}

	public int findChildInParent(BPTreeNode<TKey, TValue> parent, BPTreeNode<TKey, TValue> child)
	{
		/* simply finds the position in the refArr this child resides in // will always be passed a child that exists in the parent */

		BPTreeInnerNode<TKey, TValue> parentNode = (BPTreeInnerNode<TKey, TValue>) parent;

		int position = 0;

		for (int k = 0; k < parent.getNumReferences(); k++)
		{
			if (parentNode.getRefArr()[k] == child)
			{
				position = k;
				break;
			}
		}

		return position;
	}

	public void AVLTreesMyEnemy(BPTreeInnerNode<TKey, TValue> node1, BPTreeInnerNode<TKey, TValue> parent, BPTreeInnerNode<TKey, TValue> node2, boolean isLeft)
	{
		/* 
			this function is 1 of 2 test cases to account for a merge that propogates to the parent when we delete at leaf level
			this function is called when an inner node has a sibling that is more than half full
			accounts for whether the parent was the root or NOT the root
		*/

		if (isLeft)
		{
			/*
				node1 = left
				node2 = curr
			*/

			int position = findChildInParent(parent, node2);
			TKey parentKey = (TKey) parent.getKeyArr()[position - 1];

			BPTreeNode<TKey, TValue> siblingChild = (BPTreeNode<TKey, TValue>) node1.getRefArr()[node1.getNumReferences() - 1];

			node1.getRefArr()[node1.getNumReferences() - 1] = null;

			TKey siblingKey = (TKey) node1.getKeyArr()[node1.getKeyCount() - 1];
			parent.getKeyArr()[position - 1] = siblingKey;
			node1.getKeyArr()[node1.getKeyCount() - 1] = null;

			node1.decrementKey();			

			shiftInnerRefs(node2, parentKey, siblingChild, true);
		}

		else if (!isLeft)
		{
			/*
				node1 = curr
				node2 = right
			*/

			int position = findChildInParent(parent, node2);
			TKey parentKey = (TKey) parent.getKeyArr()[position - 1];

			BPTreeNode<TKey, TValue> siblingChild = (BPTreeNode<TKey, TValue>) node2.getRefArr()[0];

			TKey siblingKey = (TKey) node2.getKeyArr()[0];

			parent.getKeyArr()[position - 1] = siblingKey;
			node2.getKeyArr()[0] = null;
			
			if (node1.getKeyCount() > 1)
			{
				node1.getKeyArr()[node1.getKeyCount() - 1] = parentKey;
				node1.setChild(node1.getNumReferences() - 1, siblingChild);
			}

			else if (node1.getKeyCount() == 0)
			{
				node1.getKeyArr()[0] = parentKey;
				node1.setChild(1, siblingChild);
				node1.incrementKey();
			}

			shiftInnerRefs(node2, null, null, false);
		}
	}

	public BPTreeInnerNode<TKey, TValue> creatingListIWantToDie(BPTreeInnerNode<TKey, TValue> node1, BPTreeInnerNode<TKey, TValue> parent, BPTreeInnerNode<TKey, TValue> node2, boolean isLeft)
	{
		/* 
			this function is 2 of 2 test cases to account for a merge that propogates to the parent when we delete at leaf level
			this function is called when an inner node has both siblings that are not more than half full
			accounts for whether the parent was the root or NOT the root
		*/

		if (parent.hasParent() || (!parent.hasParent() && parent.getKeyCount() > 1)) // (parent of currNode is NOT root) OR (is the root BUT has more than 1 key)
		{
			if (isLeft)
			{
				/*
					node1 = left
					node2 = curr
				*/

				int position = findChildInParent(parent, node2);
				TKey parentKey = (TKey) parent.getKeyArr()[position - 1];
				BPTreeInnerNode<TKey, TValue> list = createInnerList(node1, node2, parentKey, null);

				parent.getRefArr()[position] = null;

				for (int i = 0; i < node1.getKeyCount(); i++)
					node1.getKeyArr()[i] = null;

				for (int k = 0; k < node1.getNumReferences(); k++)
					node1.getRefArr()[k] = null;

				node1.setKey(0);

				for (int i = 0; i < list.getKeyCount(); i++)
				{
					node1.getKeyArr()[i] = list.getKeyArr()[i];
					node1.incrementKey();
				}
					
				for (int k = 0; k < list.getNumReferences(); k++)
				{
					node1.setChild(k, (BPTreeNode<TKey, TValue>) list.getRefArr()[k]);
				}

				if (!parent.isFull())
				{
					for (int i = position - 1; i < parent.getKeyCount(); i++)
						parent.getKeyArr()[i] = parent.getKeyArr()[i + 1];
					
					for (int k = position; k < parent.getNumReferences(); k++)
						parent.getRefArr()[k] = parent.getRefArr()[k + 1];
				}

				else if (parent.isFull())
				{
					for (int i = position - 1; i < parent.getKeyCount() - 1; i++)
						parent.getKeyArr()[i] = parent.getKeyArr()[i + 1];
					parent.getKeyArr()[parent.getKeyCount() - 1] = null;
					
					for (int k = position; k < parent.getNumReferences() - 1; k++)
						parent.getRefArr()[k] = parent.getRefArr()[k + 1];
					parent.getRefArr()[parent.getNumReferences() - 1] = null;
				}

				parent.decrementKey();

				// ----------------------------------------------------------
				setInnerSiblings(node1);
				// ----------------------------------------------------------

				return node1;
			}

			else if (!isLeft)
			{
				/*
					node1 = curr
					node2 = right
				*/

				int position = findChildInParent(parent, node2);
				TKey parentKey = (TKey) parent.getKeyArr()[position - 1];

				BPTreeInnerNode<TKey, TValue> list = createInnerList(node1, node2, parentKey, null);

				parent.getRefArr()[position] = null;

				for (int i = 0; i < node1.getKeyCount(); i++)
					node1.getKeyArr()[i] = null;

				for (int k = 0; k < node1.getNumReferences(); k++)
					node1.getRefArr()[k] = null;

				node1.setKey(0);

				for (int i = 0; i < list.getKeyCount(); i++)
				{
					node1.getKeyArr()[i] = list.getKeyArr()[i];
					node1.incrementKey();
				}
					
				for (int k = 0; k < list.getNumReferences(); k++)
				{
					node1.setChild(k, (BPTreeNode<TKey, TValue>) list.getRefArr()[k]);
				}

				if (!parent.isFull())
				{
					for (int i = position - 1; i < parent.getKeyCount(); i++)
						parent.getKeyArr()[i] = parent.getKeyArr()[i + 1];
					
					for (int k = position; k < parent.getNumReferences(); k++)
						parent.getRefArr()[k] = parent.getRefArr()[k + 1];
				}

				else if (parent.isFull())
				{
					for (int i = position - 1; i < parent.getKeyCount() - 1; i++)
						parent.getKeyArr()[i] = parent.getKeyArr()[i + 1];
					parent.getKeyArr()[parent.getKeyCount() - 1] = null;
					
					for (int k = position; k < parent.getNumReferences() - 1; k++)
						parent.getRefArr()[k] = parent.getRefArr()[k + 1];
					parent.getRefArr()[parent.getNumReferences() - 1] = null;
				}

				parent.decrementKey();

				// ----------------------------------------------------------
				setInnerSiblings(node1);
				// ----------------------------------------------------------

				return node1;
			}
		}

		else if (!parent.hasParent() && parent.getKeyCount() == 1)
		{			
			TKey parentKey = (TKey) parent.getKeyArr()[0];


			BPTreeInnerNode<TKey, TValue> newRoot = createInnerList(node1, node2, parentKey, null);


			parent.getRefArr()[1] = null;
			node1.leftSibling = null;
			node1.rightSibling = null;

			for (int i = 0; i < node1.getKeyCount(); i++)
				node1.getKeyArr()[i] = null;
			
			for (int k = 0; k < node1.getNumReferences(); k++)
				node1.getRefArr()[k] = null;

			node1.setKey(0);

			for (int i = 0; i < newRoot.getKeyCount(); i++)
			{
				node1.getKeyArr()[i] = newRoot.getKeyArr()[i];
				node1.incrementKey();
			}


			for (int k = 0; k < newRoot.getNumReferences(); k++)
			{
				node1.setChild(k, (BPTreeNode<TKey, TValue>) newRoot.getRefArr()[k]);
			}
				
			parent.setKey(0);
			node1.parentNode = null;

			return node1; // new root of the tree
		}

		return null;
	}
}