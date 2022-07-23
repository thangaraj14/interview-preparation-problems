package dynamicProgramming;

/**
 * https://leetcode.com/problems/target-sum/
 * <p>
 * Why 0/1 Knapsack? Our 'Capacity' is the target we want to reach 'S'.
 * Our 'Items' are the numbers in the input subset and the 'Weights' of the items are the values of the numbers itself.
 * This question follows 0/1 and not unbounded knapsack because we can use each number ONCE.
 * <p>
 * What is the variation? The twist on this problem from standard knapsack is that
 * we must add ALL items in the subset to our knapsack.
 * We can reframe the question into adding the positive or negative
 * value of the current number to our knapsack in order to reach the target capacity 'S'.
 * <p>
 * What is the variation? The twist on this problem from standard knapsack is that
 * we must add ALL items in the subset to our knapsack.
 * We can reframe the question into adding the positive or negative value of the current number to our knapsack
 * in order to reach the target capacity 'S'.
 * <p>
 * We need 2 base cases. One for when the current state is valid and one for when the current state is invalid.
 * <p>
 * Valid: Index is out of bounds AND current sum is equal to target 'S'
 * Invalid: Index is out of bounds
 * <p>
 * <p>
 * Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
 * Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
 * <p>
 * Then let's see how this can be converted to a subset sum problem:
 * <p>
 * sum(P) - sum(N) = target
 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
 * 2 * sum(P) = target + sum(nums)
 */
public class TargetSum {
    Integer[][] cache;

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) sum += i;
        cache = new Integer[1001][2 * sum + 1];

        // the reason we send target+sum and sum instead of target and 0 is
        // sum-nums[index] will produce negative values which will throw index out of bound exception at cache[index][sum]
        return recursionHelper(nums, target + sum, 0, sum);
    }

    public int recursionHelper(int[] nums, int target, int index, int sum) {

        if (index >= nums.length) {
            return target == sum ? 1 : 0;
        }

        if (cache[index][sum] != null) return cache[index][sum];

        int left = recursionHelper(nums, target, index + 1, sum + nums[index]);

        int right = recursionHelper(nums, target, index + 1, sum - nums[index]);

        return cache[index][sum] = left + right;
    }

    /** solution 2: DP (0/1 knapsack) - Time: O(n^2), Space: O(n^2) Thanks @jerry  */
    /**
     * sub-problem: dp[i][j] represents number of possible ways to reach sum j by using first ith items
     * base case: dp[0][sum], position sum represents sum 0
     * recurrence relation:
     * dp[i][j] += dp[i - 1][j + nums[i - 1]] if j + nums[i - 1] <= sum * 2
     * dp[i][j] += dp[i - 1][j - nums[i - 1]] if j - nums[i - 1] >= 0
     * <p>
     * explanation: if j + nums[i - 1] or j - nums[i - 1] is in correct range, we can use the number nums[i - 1]
     * to generate next state of dp array
     */
    public static int findTargetSumWaysBottomUp(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // corner case: when S is out of range [-sum, sum]
        if (S < -sum || S > sum) {
            return 0;
        }

        int[][] dp = new int[nums.length + 1][sum * 2 + 1];
        dp[0][sum] = 1;
        int leftBound = 0;
        int rightBound = sum * 2;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = leftBound; j < rightBound + 1; j++) {
                // try all possible sum of (previous sum j + current number nums[i - 1]) and all possible difference of
                // (previous sum j - current number nums[i - 1])
                if (j + nums[i - 1] <= rightBound) {
                    dp[i][j] += dp[i - 1][j + nums[i - 1]];
                }
                if (j - nums[i - 1] >= leftBound) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum + S];
    }

    public static void main(String[] args) {
        System.out.println(findTargetSumWaysBottomUp(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
