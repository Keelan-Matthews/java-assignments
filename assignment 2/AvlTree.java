public class AvlTree<T extends Comparable<T>> {
    public Node<T> root;
    public AvlTree() {
        this.root = null;
    }
    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }
    void print(Node<T> node) {
        if (node == null)
            return;

        print(node.left);

        System.out.print(node.data + " ");

        print(node.right);
    }

    Node<T> insert(Node<T> node, T data) {
        if (node == null) return new Node<>(data);

        if (data.compareTo(node.data) < 0)
            node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        else
            return node;

        setHeights(node);
        return balance(node);
    }

    Node<T> removeNode(Node<T> root, T data) {
        if (root == null) return null;

        if (data.compareTo(root.data) < 0)
            root.left = removeNode(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = removeNode(root.right, data);
        else {
            //One or no children
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            //Two children
            root.data = getMax(root.left);
            root.left = removeNode(root.left,root.data);
        }

        setHeights(root);
        return balance(root);
    }

    int max(int x, int y) {
        return (x > y) ? x : y;
    }

    T getMax(Node<T> node) {
        return (node.right != null) ? getMax(node.right) : node.data;
    }

    Node<T> left(Node<T> node) {
        Node<T> rightChild = node.right;
        Node<T> centerChild = rightChild.left;

        rightChild.left = node;
        node.right = centerChild;

        setHeights(node);
        setHeights(rightChild);

        return rightChild;
    }

    Node<T> right(Node<T> node) {
        Node<T> leftChild = node.left;
        Node<T> centerChild = leftChild.right;

        leftChild.right = node;
        node.left = centerChild;

        setHeights(node);
        setHeights(leftChild);

        return leftChild;
    }

    int bF(Node<T> node) {
        return (node == null) ? 0 : (getBalanceHeight(node.left) - getBalanceHeight(node.right));
    }

    int getBalanceHeight(Node<T> node) {
        return (node == null) ? 0 : node.height + 1;
    }

    int setHeights(Node<T> node) {
        if (node == null || (node.left == null && node.right == null)) return 0;

        int maxHeight = max(setHeights(node.left), setHeights(node.right));
        node.height = maxHeight +1;
        return node.height;
    }

    Node<T> balance(Node<T> node) {
        int bF = bF(node);

        if (bF > 1) {
            if (bF(node.left) < 0)
                node.left = left(node.left);

            return right(node);
        }
        if (bF < -1) {
            if (bF(node.right) > 0)
                node.right = right(node.right);

            return left(node);
        }
        return node;
    }
}
