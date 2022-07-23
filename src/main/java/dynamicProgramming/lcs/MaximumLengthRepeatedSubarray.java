package dynamicProgramming.lcs;

/**
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * Idea is same as Longest common substring, in case of subsequence only we'll do if(nums1[i-1]!=nums2[j-1])
 */
public class MaximumLengthRepeatedSubarray {

    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        int result = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;
    }
}
