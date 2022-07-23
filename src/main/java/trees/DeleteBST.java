package trees;

/**
 * tricky
 */
public class DeleteBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null && root.right == null) return null;

            else if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                root.val = getRightMin(root.right);
                root.right = deleteNode(root.right, root.val);
                return root;
            }
        }
    }

    public int getRightMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}
