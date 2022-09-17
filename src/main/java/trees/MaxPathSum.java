package trees;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/603423/Python-Recursion-stack-thinking-process-diagram
 *
 * tricky Path sum
 */

public class MaxPathSum {

    public int maxPathSum(TreeNode root) {

        if (root == null) return 0;
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        maxPathSumUtil(root, result);
        return result[0];
    }

    public int maxPathSumUtil(TreeNode root, int[] result) {

        if (root == null) return 0;

        int leftRootSum = Math.max(0, maxPathSumUtil(root.left, result));
        int rightRootSum = Math.max(0, maxPathSumUtil(root.right, result));

        int rootPathSum = rightRootSum + leftRootSum + root.val;

        result[0] = Math.max(rootPathSum, result[0]);

        return root.val + Math.max(leftRootSum, rightRootSum);
    }
}
