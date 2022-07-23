package dynamicProgramming.oiknapsack;

import java.util.Arrays;

/**
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5752754626625536
 * <p>
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
class EqualSubsetSumPartition {

// 			  0	  1	  2   3   4  5
//			+---+---+---+---+---+---+
//	{1}		| T | T | F | F | F | F |
//			+---+---+---+---+---+---+
//	{1,2}	| T | T | T | T | F | F |
//			+---+---+---+---+---+---+
//	{1,2,3}	| T | T | T | T | T | T |
//			+---+---+---+---+---+---+
//{1,2,3,4} | T | T | T | T | T | T |
//			+---+---+---+---+---+---+

    public boolean canPartition(int[] nums) {
            int totalSum = 0;
            // find sum of all array elements
            for (int num : nums) {
                totalSum += num;
            }
            // if totalSum is odd, it cannot be partitioned into equal sum subset
            if (totalSum % 2 != 0) return false;
            int subSetSum = totalSum / 2;
            int n = nums.length;
            boolean dp[][] = new boolean[n + 1][subSetSum + 1];
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                int curr = nums[i - 1];
                for (int j = 0; j <= subSetSum; j++) {
                    if (j < curr)
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
                }
            }
            return dp[n][subSetSum];
        }

    Boolean[][] cache;

    public boolean canPartitionBottomUp(int[] nums) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        cache = new Boolean[nums.length + 1][target + 1];
        return sumPossible(nums, 0, target);
    }

    public boolean sumPossible(int[] nums, int i, int target) {
        if (target < 0) return false;
        if (target == 0) return true;
        if (i >= nums.length) return false;
        if (cache[i][target] != null) return cache[i][target];

        return cache[i][target] = sumPossible(nums, i + 1, target - nums[i]) || sumPossible(nums, i + 1, target);
    }

    public boolean canPartitionSpace(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = target; i > 0; i--) {

                // Go from behind to preserve data!
                // We only need data from the previous number
                // being looked at, that means the previous row.
                // If we go from behind, that information is preserved.

                if (i - num >= 0) dp[i] = dp[i] || dp[i - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        EqualSubsetSumPartition ps = new EqualSubsetSumPartition();
        int[] num = {2, 3, 4, 5};
        System.out.println(ps.canPartition(num));
    }
}
