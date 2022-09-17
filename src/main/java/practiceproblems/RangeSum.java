package practiceproblems;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 *
 * First calculate prefix sums array for the given array nums.
 * Now, your task is to find sum of elements from left to right.
 * If left == 0, means we need to directly return sum at the index pref[right].
 * Otherwise, just return the difference of sums at pref[right] - pref[left - 1].
 *
 * Example:
 * ar = [-2, 0, 3, -5, 2, -1]
 * range = [0, 2], [2, 5]
 *
 * Prefix Sums Array = [-2,-2,1,-4,-2,-3]
 *
 * Output:
 * For query 1, left == 0, return prefix sum at index right. Print pref[2] = 1
 * For query 2, left != 0, return pref[right] - pref[left - 1]. Print pref[5] - pref[1] = -1
 */
public class RangeSum {
    int[] dp;

    public void NumArray(int[] nums) {
        if (nums.length == 0) return;
        dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) return dp[j];
        return dp[j] - dp[i - 1];
    }
}