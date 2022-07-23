package dynamicProgramming.lcs;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/shortest-common-supersequence/
 * <p>
 *The idea is very simple. The result string should contain all characters of s1 and s2 discarding the common ones.
 * -> S1+S2-LCS
 * because characters appearing in LCS are coming twice in the result. So count them only once.
 *
 * O(MN) *O(String len) if we store string in DP
 * else O(MN)
 *
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 */
public class ShortestCommonSupersequence {

    public String shortestCommonSuperSequence(String str1, String str2) {

        String lcs = longestCommonSubSeq(str1, str2);
        int i = 0;
        int j = 0;

        StringBuilder sb = new StringBuilder();
        for (char c : lcs.toCharArray()) {
            while (i < str1.length() && str1.charAt(i) != c) sb.append(str1.charAt(i++));
            while (j < str2.length() && str2.charAt(j) != c) sb.append(str2.charAt(j++));
            sb.append(c);
            i++;
            j++;
        }
        sb.append(str1.substring(i));
        sb.append(str2.substring(j));

        return sb.toString();
    }

    public static void main(String[] args) {
        new ShortestCommonSupersequence().shortestCommonSuperSequence("abac","cab");
    }

    public String longestCommonSubSeq(String str1, String str2) {
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
        for (String[] s : dp) {
            Arrays.fill(s, "");
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }
}
