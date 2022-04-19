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

    /*Printing AvlTree in inorder*/
    void print(Node<T> node) {
        if (node == null)
            return;

        print(node.left);

        System.out.print(node.data + " ");

        print(node.right);
    }

    /* Do not edit the code above */

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */

    Node<T> insert(Node<T> node, T data) {
        if (node == null) return new Node<>(data);

        if (data.compareTo(node.data) < 0)
            node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        else
            return node;

        update(node);
        int bF = bF(node);

        if (bF > 1 && data.compareTo(node.left.data) < 0) return right(node);
        if (bF < -1 && data.compareTo(node.right.data) > 0) return left(node);

        if (bF > 1 && data.compareTo(node.left.data) > 0 ) {
            node.left = left(node.left);
            return right(node);
        }
        if (bF < -1 && data.compareTo(node.right.data) < 0 ) {
            node.right = right(node.right);
            return left(node);
        }

        return node;
    }


    /**
     * Remove / Delete the node based on the given data
     * Return the node / root if the data do not exist
     */

    Node<T> removeNode(Node<T> root, T data) {
        if (root == null) return null;

        if (data.compareTo(root.data) < 0)
            root.left = removeNode(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = removeNode(root.right, data);
        else {
            if (root.left == null || root.right == null) {
                Node<T> var = null;
                if (var == root.left)
                    var = root.right;
                else
                    var = root.left;

                if (var == null) {
                    var = root;
                    root = null;
                }
                else
                    root = var;
            }
            else {
                Node<T> curr = root.right;
                while (curr.left != null)
                    curr = curr.left;

                root.data = curr.data;
                root.right = removeNode(root.right, curr.data);
            }
        }

        if (root == null) return null;

        update(root);
        int bF = bF(root);

        if (bF > 1 && bF(root.left) >= 0) return right(root);
        if (bF < -1 && bF(root.right) <= 0) return left(root);

        if (bF > 1 && bF(root.left) < 0) {
            root.left = left(root.left);
            return right(root);
        }

        if (bF < -1 && bF(root.right) > 0) {
            root.right = right(root.right);
            return left(root);
        }

        return root;
    }

    int max(int x, int y) {
        return (x > y) ? x : y;
    }

    Node<T> left(Node<T> node) {
        Node<T> rightNode = node.right;
        Node<T> leftNode = rightNode.left;

        rightNode.left = node;
        node.right = leftNode;

        update(node);
        update(rightNode);

        return rightNode;
    }

    Node<T> right(Node<T> node) {
        Node<T> leftNode = node.left;
        Node<T> rightNode = leftNode.right;

        leftNode.right = node;
        node.left = rightNode;

        update(node);
        update(leftNode);

        return leftNode;
    }

    int bF(Node<T> node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    int update(Node<T> node) {
        int leftHeight = 0;
        int rightHeight = 0;

        if (node == null) return 0;

        if (node.left == null && node.right == null) {
            node.height = 0;
            return node.height;
        }

        if (node.right != null) rightHeight = 1 + update(node.right);
        if (node.left != null) leftHeight = 1 + update(node.left);

        node.height = max(leftHeight, rightHeight);
        return node.height;
    }
}
