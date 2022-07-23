package dynamicProgramming;

/**
 * TODO
 * https://leetcode.com/problems/paint-house/
 */
public class PaintHouses {

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        // Assume all costs are positive
        int n = costs.length; // number of houses
        int[][] dp = new int[n][3];
        // Init
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        // DP
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }

}
