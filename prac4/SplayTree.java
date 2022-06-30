public class SplayTree<T extends Comparable<T>> {

    public TreeNode<T> root;

    public SplayTree() {}

    public Boolean contains(T key) {
        return contains(root, key);
    }

    public void insert(T key) { root = insert(root, key); }

    TreeNode<T> predecessor = null;

    public T findPredecessor(T key) {
        if (!contains(key)) return null;
        findPredecessor(root, key);
        if (predecessor != null) return predecessor.key;
        else return null;
    }

    public void access(T key) {

        if (!contains(key))
            insert(key);
        else
            root = splayFunction(root, key);
    }

    public Boolean contains(TreeNode<T> root, T key) {
        if (root == null) return false;
        if (root.key.compareTo(key) == 0) return true;

        if (root.key.compareTo(key) < 0)
            return contains(root.right, key);

        if (root.key.compareTo(key) > 0)
            return contains(root.left, key);

        return false;
    }

    public TreeNode<T> insert(TreeNode<T> root, T key) {
        if (root == null) {
            root = new TreeNode<>(key);
            return root;
        }

        if (key.compareTo(root.key) < 0)
            root.left = insert(root.left, key);
        else if (key.compareTo(root.key) > 0)
            root.right = insert(root.right, key);

        return root;
    }

    public void findPredecessor(TreeNode<T> root, T key) {
        if (root == null) return;

        if (root.key.compareTo(key) == 0) {
            if (root.left != null) {
                TreeNode<T> pointer = root.left;

                while (pointer.right != null)
                    pointer = pointer.right;

                predecessor = pointer;
            }

            return;
        }

        if (root.key.compareTo(key) > 0)
            findPredecessor(root.left, key);
        else {
            predecessor = root;
            findPredecessor(root.right, key);
        }
    }

    public TreeNode<T> splayFunction(TreeNode<T> root, T key) {
        if (root == null || root.key.compareTo(key) == 0) {
            return root;
        }

        if (root.key.compareTo(key) > 0) {
            if (root.left == null) return root;

            if (root.left.key.compareTo(key) > 0) {
                root.left.left = splayFunction(root.left.left, key);
                root = right(root);
            }
            else if (root.left.key.compareTo(key) < 0) {
                root.left.right = splayFunction(root.left.right, key);

                if (root.left.right != null)
                    root.left = left(root.left);
            }

            if (root.left == null)
                return root;
            else
                return right(root);
        }
        else {
            if (root.right == null) return root;

            if (root.right.key.compareTo(key) > 0) {
                root.right.left = splayFunction(root.right.left, key);

                if (root.right.left != null)
                    root.right = right(root.right);
            }
            else if (root.right.key.compareTo(key) < 0) {
                root.right.right = splayFunction(root.right.right, key);
                root = left(root);
            }

            if (root.right.left == null)
                return root;
            else
                return left(root);
        }
    }

    public TreeNode<T> right(TreeNode<T> node) {
        TreeNode<T> node2 = node.left;
        node.left = node2.right;
        node2.right = node;
        return node2;
    }

    public TreeNode<T> left(TreeNode<T> node) {
        TreeNode<T> node2 = node.right;
        node.right = node2.left;
        node2.left = node;
        return node2;
    }
}