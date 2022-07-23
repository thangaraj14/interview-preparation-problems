package dynamicProgramming.lcs;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings-set-2/
 */
public class TwoStringInterleavingToFormThird {

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String args[]) {
        String str1 = "aab";
        String str2 = "axy";
        String str3 = "aaxaby";
        TwoStringInterleavingToFormThird sti = new TwoStringInterleavingToFormThird();
        System.out.println(sti.isInterleave(str1, str2, str3));
    }

	public boolean isInterleave(String s1, int i, String s2, int j, String s3, int k, Integer[][] memo) {
		if (i == s1.length()) {
			return s2.substring(j).equals(s3.substring(k));
		}
		if (j == s2.length()) {
			return s1.substring(i).equals(s3.substring(k));
		}
		if (memo[i][j] !=null) {
			return memo[i][j] == 1;
		}
		boolean ans = s3.charAt(k) == s1.charAt(i) && isInterleave(s1, i + 1, s2, j, s3, k + 1, memo)
				|| s3.charAt(k) == s2.charAt(j) && isInterleave(s1, i, s2, j + 1, s3, k + 1, memo);

		memo[i][j] = ans ? 1 : 0;
		return ans;
	}
	public boolean isInterleaveRecursive(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		Integer[][] memo= new Integer[s1.length()][s2.length()];

		return isInterleave(s1, 0, s2, 0, s3, 0, memo);
	}
}
