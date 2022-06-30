public class BST<T extends Comparable<T>> {
    protected BSTNode<T> root;

    public BST(){};

    public boolean insert(T val){
        if(root == null){
            root = new BSTNode<>(val);
            return true;
        } else {
            return root.recInsert(val);
        }
    }

    //Place code below

    public int numNodes(){
        return numNodes(root);
    }

    Object[] returnArray = new BSTNode[90];
    int toArrayCount = 0;
    public Object[] toArray(){
        toArray(root);

        Object[] newArray = new BSTNode[numNodes()];
        int i = 0;
        while (returnArray[i] != null) {
            newArray[i] = returnArray[i];
            i++;
        }
        return newArray;
    }

    public boolean contains(T val){
        return contains(val, root);
    }

    public boolean isEmpty(){
        return root == null;
    }

    public BSTNode<T> find(T val){
        return find(val, root);
    }

    public int height(){
        return height(root)-1;
    }

    public Object[] nodesOnHeight(int h){
        heightCount = 0;
        heightArray = new BSTNode[90];
        nodesOnHeight(h,root,0);

        if (heightArray[0] == null) return null;

        Object[] array = new BSTNode[heightCount];

        for (int i = 0; i < heightCount; i++) {
            array[i] = heightArray[i];
        }

        return array;
    }

    public String DFT(){
        DFT(root);
        dftString = dftString.substring(0,dftString.length()-1);
        return dftString;
    }

    public String BFT(){
        int h = height();
        int i;
        int count = 0;
        String bftString = "";
        for (i = 0; i <= h; i++){
            Object[] row = nodesOnHeight(i);

            for (int j = count; j < row.length; j++) {
                bftString += row[j].toString() + ";";
                count++;
            }
        }

        bftString = bftString.substring(0,bftString.length()-1);
        return bftString;
    }

    public T maxVal(){
        return maxVal(root);
    }

    public T minVal(){
        return minVal(root);
    }

    //ADD HELPER FUNCTIONS HERE
    public int numNodes(BSTNode<T> node) {
        if (node == null) return 0;

        return 1 + numNodes(node.left) + numNodes(node.right);
    }

    public boolean contains(T val, BSTNode<T> rootNode) {
        if (rootNode == null) return false;

        if (val.compareTo(rootNode.getVal()) == 0)
            return true;
        else if (val.compareTo(rootNode.getVal()) < 0)
            return contains(val,rootNode.left);
        else
            return contains(val,rootNode.right);
    }

    public BSTNode<T> find(T val, BSTNode<T> rootNode) {
        if (rootNode == null) return null;

        if (val.compareTo(rootNode.getVal()) == 0)
            return rootNode;
        else if (val.compareTo(rootNode.getVal()) < 0)
            return find(val,rootNode.left);
        else
            return find(val,rootNode.right);
    }

    public int height(BSTNode<T> node) {
        if (node == null) return -1;

        int left = height(node.left);
        int right = height(node.right);

        if (left > right)
            return left + 1;
        else
            return right + 1;
    }

    public T maxVal(BSTNode<T> node) {
        if (node == null) return null;

        if (node.right == null)
            return node.getVal();
        return maxVal(node.right);
    }

    public T minVal(BSTNode<T> node) {
        if (node == null) return null;

        if (node.left == null)
            return node.getVal();
        return minVal(node.left);
    }

    String dftString = "";
    public void DFT(BSTNode<T> p) {
        if (p != null) {
            DFT(p.left);
            dftString = dftString + p.toString() + ";";
            DFT(p.right);
        }
    }
    
    Object[] heightArray;
    int heightCount;
    public void nodesOnHeight(int h, BSTNode<T> root, int currH) {
        if(h < 0 || root == null) return;

        if(currH == h) {
            heightArray[heightCount++] = root;
            return;
        }

        nodesOnHeight(h, root.left, currH+1);
        nodesOnHeight(h, root.right,currH+1);
    }

    public void toArray(BSTNode<T> p) {
        if (p != null) {
            toArray(p.left);
//            arrayQueue.enqueue(p);
            returnArray[toArrayCount++] = p;
            toArray(p.right);
        }
    }
}
