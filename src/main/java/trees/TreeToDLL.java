package trees;

/**
 * tricky traversal
 *
 */
public class TreeToDLL {

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        while (root.right != null)
            root = root.right;
        root.right = right;
    }

    private TreeNode prev = null;

    public void flattenOptimised(TreeNode root) {
        if (root == null)
            return;
        flattenOptimised(root.right);
        flattenOptimised(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public void flattenIterative(TreeNode root) {

        // Handle the null scenario
        if (root == null) {
            return;
        }

        TreeNode node = root;

        while (node != null) {

            // If the node has a left child
            if (node.left != null) {

                // Find the predecessor node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // move on to the right side of the tree
            node = node.right;
        }
    }
}
