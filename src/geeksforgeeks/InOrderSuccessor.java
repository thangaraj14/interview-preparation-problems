package geeksforgeeks;

public class InOrderSuccessor {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */ TreeNode result = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        helperFn(root, p);
        return result;
    }

    public void helperFn(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }

        if (root.val > p.val) {
            result = root;
            helperFn(root.left, p);
        } else {
            helperFn(root.right, p);
        }

    }
}