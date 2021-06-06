package dsa;

/**
 * Date 08/01/2014
 *
 * @author Tushar Roy
 * <p>
 * Time complexity - O(n2) Space complexity - O(n2)
 * <p>
 * Youtube link - https://youtu.be/_nCsPn7_OgI
 * <p>
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 * <p>
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int level = 2; level <= n; level++) {
            for (int i = 0; i < n - level + 1; i++) {
                int j = i + level - 1;

                if (level == 2 && arr[i] == arr[j]) {
                    dp[i][j] = 2;
                } else if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String args[]) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        int r2 = lps.longestPalindromeSubseq("agbdba");
        System.out.print(r2);
    }

}