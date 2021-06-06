package dsa;

/**
 * Time Complexity: O(m * n * x)
 */
class DiceThrow {

    public static long findWays(int d, int f, int target) {
        int MOD = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                if (j > i * f) {
                    continue;         //If j is larger than largest possible sum of i dices, there is no possible ways.
                } else {                      //watch out below condition, or NPE
                    for (int k = 1; k <= f && k <= j; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
        }
        for (int i = 0; i < d + 1; i++) {
            for (int j = 0; j < target + 1; j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        return dp[d][target];
    }

    public static void main(String[] args) {
        System.out.println(findWays(3, 6, 8));
    }
}