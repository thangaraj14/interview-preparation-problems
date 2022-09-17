package trees;

/**
 * tricky BST
 */
public class LargestBST {


    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;

        NodeValue result = largestBSTSubtreeHelper(root);

        return result.maxSize;

    }

    /**
     * Idea for a valid BST is if a node root has
     *
     *              root
     *             /    \
     *           /       \
     *   Max left    Min right
     *
     *   if root > 'Max left' then it is greater than all the nodes in the left subtree
     *   if root < 'Min right' then it is smaller than the all the nodes in the right subtree
     *
     *   in case of non bst then pass +INF as left max and -INF as right min which will break the result and then keep the size of the previous result
     *
     *   if root == null pass leftmax as -INF and rightMin as +INF
     *
     * @param root
     * @return
     */
    public NodeValue largestBSTSubtreeHelper(TreeNode root) {
        if (root == null) return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);


        NodeValue left = largestBSTSubtreeHelper(root.left);
        NodeValue right = largestBSTSubtreeHelper(root.right);

        int size = Math.max(left.maxSize, right.maxSize);

        if (left.maxNode < root.val && root.val < right.minNode) {

            return new NodeValue(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode), left.maxSize + right.maxSize + 1);
        }

        //non bst is found, then pass +INF as left max and -INF as right min which will break the result and then keep the size of the previous result
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, size);


    }


    static class NodeValue {
        int maxSize;
        int maxNode;
        int minNode;

        public NodeValue(int minNode, int maxNode, int maxSize) {
            this.minNode = minNode;
            this.maxNode = maxNode;
            this.maxSize = maxSize;
        }
    }
}
