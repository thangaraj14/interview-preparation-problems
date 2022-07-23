package dynamicProgramming.lcs;

/**
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 * <p>
 * In this question we are given two strings , and asked to get the sum of ASCII values, to make string same,
 * RE-WORDING question : get the sum of all characters which do not form longest Common Subsequence.
 * <p>
 * +------+-----+-------+------+------+
 * |      | ""  | s (1) | e(2) | e(3) |
 * +------+-----+-------+------+------+
 * | ""   | 0   | 101   | 198  | 314  |
 * +------+-----+-------+------+------+
 * | t(1) | 115 | 216   | 313  | 429  |
 * +------+-----+-------+------+------+
 * | e(2) | 216 | 115   | 212  | 328  |
 * +------+-----+-------+------+------+
 * | e(3) | 313 |       |      |      |
 * +------+-----+-------+------+------+
 */
public class MinimumAsciiDelete {


    public int minimumDeleteSumTopDown(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // see first row and col, in order to equate chars to "", we need to delete chars
        // so we add all chars
        //if string_A or string_B is empty : then our ans is sum of all ASCII of non empty string
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        }
        // refer above comment
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                //Of the two strings, if both of their last characters match
                // then certainly the answer comes from skipping those characters.
                //i.e. Answer("zca","bza") = Answer("zc","bz")
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //if the last characters are different then its one of the three situations:
                    //drop s1's last character (ASCII(s1's last) + dp[i-1][j])
                    //drop s2's last character (ASCII(s2's last) + dp[i][j-1])
                    // min of above 2
                    //One is to delete s1.charAt(i-1) ,based on the condition that s1[0:i-1] and s2[0:j] is already the same.
                    //The other is to delete s2.charAt(j-1),based on the condition that s1[0:i] and s2[0:j-1] is already the same.
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }


    Integer[][] cache;

    public int minimumDeleteSum(String s1, String s2) {
        cache = new Integer[s1.length() + 1][s2.length() + 1];

        return recursionHelper(s1, s2, 0, 0);

    }

    public int recursionHelper(String s1, String s2, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) return 0;
        if (cache[i][j] != null) return cache[i][j];
        if (i >= s1.length()) return getAscii(s2.substring(j));
        if (j >= s2.length()) return getAscii(s1.substring(i));

        int result = 0;

        if (s1.charAt(i) == s2.charAt(j)) {
            result += recursionHelper(s1, s2, i + 1, j + 1);
        } else {
            result += Math.min(recursionHelper(s1, s2, i + 1, j) +  s1.charAt(i), recursionHelper(s1, s2, i, j + 1) +  s2.charAt(j));
        }

        return cache[i][j] = result;

    }

    public int getAscii(String str) {
        int res = 0;
        for (char s : str.toCharArray()) {
            res += s;
        }
        return res;
    }

}
