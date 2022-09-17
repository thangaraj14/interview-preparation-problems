package trees;

/**
 * https://leetcode.com/problems/house-robber-iii/
 */
public class HouseRobberTree {

    /**
     * Approach1: Recursive (TLE)
     * T.C : O(2^n)
     * S.C : O(1) (ignoring stack memory used for recursion)
     */
    public int robNaive(TreeNode root) {
        if (root == null) return 0;

        int ans = 0;

        // max value from left grandchildren
        if (root.left != null) {
            ans += robNaive(root.left.left) + robNaive(root.left.right);
        }

        // max value from right grandchildren
        if (root.right != null) {
            ans += robNaive(root.right.left) + robNaive(root.right.right);
        }

        return Math.max(ans + root.val, robNaive(root.left) + robNaive(root.right));  //(Case1,Case2)
    }

    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * Redefine rob(root) as a new function which will return an array of two elements,
     * the first element of which denotes the maximum amount of money that can be robbed if root is not robbed,
     * while the second element signifies the maximum amount of money robbed if it is robbed.
     */
    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        /**
         * Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc.
         * For the 1st element of rob(root), we only need to sum up the larger elements of rob(root.left) and rob(root.right), respectively,
         * since root is not robbed and we are free to rob its left and right subtrees
         */
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);


        /**
         * For the 2nd element of rob(root), however, we only need to add up the 1st elements of rob(root.left) and rob(root.right), respectively,
         * plus the value robbed from root itself, since in this case it's guaranteed that we cannot rob the nodes of root.left and root.right.
         */
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
