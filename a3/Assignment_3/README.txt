All explanations for what each function does is located in the functions themselves

P.S. The main for this assignment is a chungus and will push you to the brink of tears when nothing works
P.S.S The main was made by Armand Krynauw, here's his ko-fi if you wanna donate something :) https://ko-fi.com/armandkrynauw

// ------------------------------------------------------------------------------------------------------------------------
[Taken from Armand's Instructions file]

Add the following functions to your classes (just remember to remove when submitting):

// ======================================================================================
// BPTree
// ======================================================================================

public BPTreeNode<TKey, TValue> getRoot() {
		return root;
}

// ======================================================================================
// BPTreeNode
// ======================================================================================

public BPTreeNode<TKey, TValue> getLeftSibling() {
		return leftSibling;
}

public BPTreeNode<TKey, TValue> getRightSibling() {
	return rightSibling;
}

@Override
public String toString() {
	if (this == null) {
		return "null";
	} else {
		String str = "(";

		for (int i = 0; i < this.getKeyCount(); i++) {
			str += this.getKey(i) + ",";
		}

		if (str.length() == 1) {
			str += ")";
		} else {
			str = str.substring(0, str.length() - 1) + ")";
		}

		return str;
	}
}


// ======================================================================================
// BPTreeInnerNode
// ======================================================================================

@SuppressWarnings("unchecked")
public BPTreeNode<TKey, TValue> getLeftReference() {
	return (BPTreeNode<TKey, TValue>) references[0];
}
