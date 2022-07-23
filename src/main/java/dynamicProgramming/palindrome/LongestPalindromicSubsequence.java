package dynamicProgramming.palindrome;

import java.util.Arrays;

/**
 * Date 08/01/2014
 * 
 * @author Tushar Roy
 *
 *         Time complexity - O(n2) Space complexity - O(n2)
 *
 *         Youtube link - https://youtu.be/_nCsPn7_OgI
 *
 *         References
 *         http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

	public int calculate1(char[] str) {
		int[][] T = new int[str.length][str.length];
		for (int i = 0; i < str.length; i++) {
			T[i][i] = 1;
		}
		for (int l = 2; l < str.length; l++) {
			for (int i = 0; i+l < str.length ; i++) {
				int j = i + l;
				if (l == 2 && str[i] == str[j]) {
					T[i][j] = 2;
				} else if (str[i] == str[j]) {
					T[i][j] = T[i + 1][j - 1] + 2;
				} else {
					T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);
				}
			}
		}
		return T[0][str.length - 1];
	}

	public int longestPalindromeSubseq(String s) {
		char[] chars = s.toCharArray();
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < n; ++j) {
				if (chars[i] == chars[j]) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		for (int[] ints : dp) {
			System.out.println(Arrays.toString(ints));
		}
		return dp[0][n - 1];

	}

	public int longestPalindromeSubseqRecusive(String s) {
		return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
	}

	private int helper(String s, int i, int j, Integer[][] memo) {
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		if (i > j)      return 0;
		if (i == j)     return 1;

		if (s.charAt(i) == s.charAt(j)) {
			memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
		} else {
			memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
		}
		return memo[i][j];
	}

	public static void main(String args[]) {
		LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
		String str = "agbdba";
		int r2 = lps.longestPalindromeSubseq(str);
		System.out.print(r2);
	}

}