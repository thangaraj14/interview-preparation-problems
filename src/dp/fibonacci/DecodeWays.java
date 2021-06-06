package dp.fibonacci;

import java.util.Arrays;

/**
 * backtobackSWE course video : https://www.youtube.com/watch?v=YcJTyrG3bZs
 * https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

    /**
     * I used a dp array of size n + 1 to save subproblem solutions. dp[0] means an empty string will have one way to decode,
     * dp[1] means the way to decode a string of size 1.
     * I then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.
     */
    public int numDecodings(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = str.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(str.substring(i - 1, i));
            int twoDigits = Integer.parseInt(str.substring(i - 2, i));
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] = dp[i - 1];
            }
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public int numDecodingsRecur(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return numDecodings(s, 0, dp);
    }

    private int numDecodings(String str, int index, int[] dp) {
        // if index reaches the length,then we are done and its valid.so return 1
        if (index == str.length()) {
            return 1;
        }
        // if the sub problem is already solved, return the value
        if (dp[index] > -1) {
            return dp[index];
        }

        int totalDecompositions = 0;
        for (int i = 1; i <= 2; i++) {
            if (index + i <= str.length()) {
                String value = str.substring(index, index + i);
                if (isValid(value)) {
                    totalDecompositions += numDecodings(str, index + i, dp);
                }
            }
        }
        dp[index] = totalDecompositions;
        return totalDecompositions;
    }

    private boolean isValid(String snippet) {
        return Integer.parseInt(snippet) >= 1 && Integer.parseInt(snippet) <= 26 && !snippet.startsWith("0");
    }

    public static void main(String[] args) {
        DecodeWays decode = new DecodeWays();
        //        System.out.println(decode.numDecodingsRecur("216"));
        System.out.println(decode.numDecodingsRecur("226"));
    }

}
