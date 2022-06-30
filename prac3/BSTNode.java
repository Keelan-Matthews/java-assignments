public class BSTNode<T extends Comparable<T>> {
    private T value;
    public BSTNode<T> right;
    public BSTNode<T> left;

    public boolean recInsert(T val){
        if(val.compareTo(value) == 0)
            return false;

        if(val.compareTo(value) < 0)
        {
            if(left == null)
            {
                left = new BSTNode<>(val);
                return true;
            } else {
                return left.recInsert(val);
            }
        } else {
            if(right == null)
            {
                right = new BSTNode<>(val);
                return true;
            } else {
                return right.recInsert(val);
            }
        }
    }

    //Implement the following

    public BSTNode(T val){
        value = val;
    }

    public T getVal(){
        return value;
    }

    public String toString(){
        if (left != null && right != null)
            return "L["+left.getVal()+"]V["+value+"]R["+right.getVal()+"]";
        else if (left == null && right != null)
            return "L[]V["+value+"]R["+right.getVal()+"]";
        else if (left != null && right == null)
            return "L["+left.getVal()+"]V["+value+"]R[]";
        else
            return "L[]V["+value+"]R[]";
    }

    //ADD HELPER FUNCTIONS HERE
}
