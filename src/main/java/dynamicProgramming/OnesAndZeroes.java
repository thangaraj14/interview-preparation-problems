package dynamicProgramming;

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {
    private int[][][] memo;

    public int findMaxForm(String[] strs, int m, int n) {
        memo = new int[m + 1][n + 1][strs.length];
        return findMaxFormFrom(strs, m, n, 0);
    }

    private int findMaxFormFrom(String[] strs, int m, int n, int si) {
        if (si == strs.length || (m == 0 && n == 0)) {
            return 0;
        }
        if (memo[m][n][si] > 0) {
            return memo[m][n][si];
        }
        int cntIncludeStr = 0;
        int zeros = countZeros(strs[si]);
        int ones = strs[si].length() - zeros;
        //for each string, we count the zeroes in it by countZeroesIn(String str) and see if there are enough 0s and 1s for it.
        // If so, we accumulate that string and proceed with the remaining strings, 0s and 1s by means of the following code:
        if (m >= zeros && n >= ones) {
            cntIncludeStr = 1 + findMaxFormFrom(strs, m - zeros, n - ones, si + 1);
        }
        //We also take the other route, which simply skips the string and does not use any 0s and 1s.
        int cntExcludeStr = findMaxFormFrom(strs, m, n, si + 1);
        //Whichever is bigger, is that the result.
        memo[m][n][si] = Math.max(cntIncludeStr, cntExcludeStr);
        return memo[m][n][si];
    }

    private int countZeros(String s) {
        int cntZero = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                cntZero++;
            }
        }
        return cntZero;
    }
}
