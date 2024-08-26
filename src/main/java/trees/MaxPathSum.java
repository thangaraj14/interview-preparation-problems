package trees;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/603423/Python-Recursion-stack-thinking-process-diagram
 *
 * tricky Path sum, observe the Note "Note: the path does not need to pass through the root."
 *
 *        -10
 *      /     \
 *    -2       3
 *   /  \     / \
 *  4    5   -6  2
 *
 *  result is 7 (from the path 4 -> -2 -> 5 in the left subtree)
 */

public class MaxPathSum {

    public int maxPathSum(TreeNode root) {

        if (root == null) return 0;
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        maxPathSumUtil(root, result);
        return result[0];
    }

    /**
     *       -10
     *     /    \
     *    9      20
     *   / \    /  \
     * -4   3  15   7

     *  For node 9:
     * leftRootSum = max(0, -4) = 0  // We use max(0, value) to ignore negative paths
     * rightRootSum = max(0, 3) = 3
     * rootPathSum = 0 + 3 + 9 = 12
     * Update result[0] to 12
     * Return 9 + max(0, 3) = 12 to parent

     * For node 20:

     * leftRootSum = max(0, 15) = 15
     * rightRootSum = max(0, 7) = 7
     * rootPathSum = 15 + 7 + 20 = 42
     * Update result[0] to 42
     * Return 20 + max(15, 7) = 35 to parent

     * For the root (-10):
     * leftRootSum = max(0, 12) = 12
     * rightRootSum = max(0, 35) = 35
     * rootPathSum = 12 + 35 + (-10) = 37
     * result[0] remains 42 (from step 3)
     * Return -10 + max(12, 35) = 25 to parent (which doesn't exist)
     */

    public int maxPathSumUtil(TreeNode root, int[] result) {

        if (root == null) return 0;
        //We use max(0, value) when calculating leftRootSum and rightRootSum.
        // This ensures we don't include negative paths that would decrease our sum.
        int leftRootSum = Math.max(0, maxPathSumUtil(root.left, result));
        int rightRootSum = Math.max(0, maxPathSumUtil(root.right, result));
        //By adding these three components, we get the sum of the path that starts from the left subtree,
        // goes through the root, and ends in the right subtree (or vice versa).
        //Negative values can be part of the maximum path if they're outweighed by positive values.
        // In our example, the -10 at the root is part of the path 9 -> -10 -> 20 -> 15, which sums to 34.
        int rootPathSum = rightRootSum + leftRootSum + root.val;

        result[0] = Math.max(rootPathSum, result[0]);

        //This represents the maximum sum of a path from the current node to one of its subtrees.
        return root.val + Math.max(leftRootSum, rightRootSum);
    }
}
