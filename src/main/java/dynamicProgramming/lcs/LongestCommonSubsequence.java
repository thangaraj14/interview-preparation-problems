package dynamicProgramming.lcs;

import java.util.Arrays;

public class LongestCommonSubsequence {

    // mistake i normally would assume is dp[0][0]=1, but actually it's not

    public static void main(String[] args) {
        String str1 = "ABCD";
        String str2 = "AEDB";

        System.out.println(longestCommonSubSeqPrint(str1, str2));

    }

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // previously matched characters
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    Integer[][] cache;

    public int longestCommonSubsequenceTopDown(String text1, String text2) {
        cache = new Integer[text1.length() + 1][text2.length() + 1];
        return recursionHelper(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    public int recursionHelper(String text1, String text2, int index1, int index2) {
        if (index1 < 0 || index2 < 0) return 0;
        if (cache[index1][index2] != null) return cache[index1][index2];

        if (text1.charAt(index1) == text2.charAt(index2)) {
            return cache[index1][index2] = 1 + recursionHelper(text1, text2, index1 - 1, index2 - 1);
        } else {
            return cache[index1][index2] =  Math.max(recursionHelper(text1, text2, index1 - 1, index2),recursionHelper(text1, text2, index1, index2 - 1));
        }
    }

    private static String longestCommonSubSeqPrint(String str1, String str2) {
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
        for (String[] strings : dp) {
            Arrays.fill(strings, "");
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
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


