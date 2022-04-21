//public class ThreadedAvlTree<T extends Comparable<T>> {
//    public Node<T> root;
//    public ThreadedAvlTree() {
//        this.root = null;
//    }
//    int getHeight(Node<T> N) {
//        if (N == null)
//            return 0;
//
//        return N.height;
//    }
//    static Node getLeftMost(Node node) {
//        while (node != null && node.left != null)
//            node = node.left;
//        return node;
//    }
//    void print(Node<T> node) {
//        if (node == null)
//            return;
//
//        Node<T> cur = getLeftMost(node);
//
//        while (cur != null) {
//            System.out.print(" " + cur.data + " ");
//
//
//            if (cur.rightThread == true)
//                cur = cur.right;
//            else
//                cur = getLeftMost(cur.right);
//        }
//    }
//
//    void convertAVLtoThreaded(Node<T> node) {
//        root = deepCopy(node);
//        makeThreaded(root);
//    }
//
//    Node<T> insert(Node<T> node, T data) {
//
//    }
//
//    Node<T> removeNode(Node<T> root, T data) {
//
//    }
//
//    Node<T> makeThreaded(Node<T> root) {
//        if (root == null) return null;
//        if (root.left == null && root.right == null) return root;
//
//        if (root.left != null) {
//            Node<T> pred = makeThreaded(root.left);
//            pred.right = root;
//            pred.rightThread = true;
//        }
//        if (root.right == null) return root;
//        return makeThreaded(root.right);
//    }
//
//    Node<T> deepCopy(Node<T> root)
//    {
//        if (root == null) return null;
//
//        Node<T> newNode = new Node<>(root.data);
//        newNode.left= deepCopy(root.left);
//        newNode.right= deepCopy(root.right);
//        return newNode;
//    }
//}
