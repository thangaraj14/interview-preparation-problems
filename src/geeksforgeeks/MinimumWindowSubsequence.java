package geeksforgeeks;

import java.util.Arrays;

/*https://www.programcreek.com/2014/01/leetcode-minimum-window-subsequence-java/*/
// unresolved
public class MinimumWindowSubsequence {

    public static String minWindow(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        // fill first row with index values
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j + 1;
        }
        // if any element is not present then it will set that row as zero so there wont
        // be any valid values in last row
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0;
        int len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {
                if (j - dp[m][j] + 1 < len) {
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return len == n + 1 ? "" : S.substring(start, start + len);
    }

    public static void main(String args[]) {
        System.out.println(minWindow("abcdebdde", "bdd"));
    }
}
