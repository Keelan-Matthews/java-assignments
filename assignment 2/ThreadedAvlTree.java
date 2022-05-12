@SuppressWarnings({"unchecked", "rawtypes"})
public class ThreadedAvlTree<T extends Comparable<T>> {
    public Node<T> root;
    public ThreadedAvlTree() {
        this.root = null;
    }
    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }
    static Node getLeftMost(Node node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }
    void print(Node<T> node) {
        if (node == null)
            return;

        Node<T> cur = getLeftMost(node);

        while (cur != null) {
            System.out.print(" " + cur.data + " ");


            if (cur.rightThread == true)
                cur = cur.right;
            else
                cur = getLeftMost(cur.right);
        }
    }

    void convertAVLtoThreaded(Node<T> node) {
        if (node == null) return;
        root = deepCopy(node);
        makeThreaded(root);
    }

    Node<T> insert(Node<T> node, T data) {
        if (node != null) node = wipeThreads(node);
        node = insertRec(node, data);
        makeThreaded(node);
        return node;
    }

    Node<T> removeNode(Node<T> root, T data) {
        if (root != null) root = wipeThreads(root);
        root = removeRec(root, data);
        makeThreaded(root);
        return root;
    }

    Node<T> makeThreaded(Node<T> root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        if (root.left != null) {
            Node<T> pred = makeThreaded(root.left);
            pred.right = root;
            pred.rightThread = true;
        }
        if (root.right == null) return root;
        return makeThreaded(root.right);
    }

    Node<T> deepCopy(Node<T> root) {
        if (root == null) return null;

        Node<T> newNode = new Node<>(root.data);
        newNode.left= deepCopy(root.left);
        newNode.right= deepCopy(root.right);
        return newNode;
    }

    Node<T> wipeThreads(Node<T> root) {
        if (root == null) return null;

        wipeThreads(root.left);
        if (root.rightThread) {
            root.right = null;
            root.rightThread = false;
        }
        wipeThreads(root.right);

        return root;
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

        node.height = setHeights(node);
        rightChild.height = setHeights(rightChild);

        return rightChild;
    }

    Node<T> right(Node<T> node) {
        Node<T> leftChild = node.left;
        Node<T> centerChild = leftChild.right;

        leftChild.right = node;
        node.left = centerChild;

        node.height = setHeights(node);
        leftChild.height = setHeights(leftChild);

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
        node.height = maxHeight + 1;
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

    Node<T> insertRec(Node<T> node, T data) {
        if (node == null) return new Node<>(data);

        if (data.compareTo(node.data) < 0)
            node.left = insertRec(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insertRec(node.right, data);
        else
            return node;

        node.height = setHeights(node);
        return balance(node);
    }

    Node<T> removeRec(Node<T> root, T data) {
        if (root == null) return null;

        if (data.compareTo(root.data) < 0)
            root.left = removeRec(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = removeRec(root.right, data);
        else {
            //One or no children
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            //Two children
            root.data = getMax(root.left);
            root.left = removeRec(root.left,root.data);
        }

        root.height = setHeights(root);
        return balance(root);
    }
}
