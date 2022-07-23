package dynamicProgramming.fibonacci;

/**
 * Time Complexity: O(m * n * x)
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum
 */
class DiceThrow {
    // f-> faces
    // d->dices
    // target->sum
    static Integer[][] cache = new Integer[31][1001];

    public static int numRollsToTarget(int d, int f, int target) {
        if (d <= 0 || target < 0) return target == 0 ? 1 : 0;
        if (cache[d][target] != null) return cache[d][target];
        int ways = 0;
        for (int i = 1; i <= f; i++) {
            ways += numRollsToTarget(d - 1, f, target - i);
            ways %= 1000000007;
        }

        return cache[d][target] = ways;
    }

    public int numRollsToTargetBottomUp(int d, int f, int target) {
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++)
            for (int j = 1; j <= target; j++)
                for (int k = 1; k <= f; k++)
                    dp[i][j] = (dp[i][j] + (k <= j ? dp[i - 1][j - k] : 0)) % 1000000007;
        return dp[d][target];
    }

    public static void main(String[] args) {
        System.out.println(numRollsToTarget(6, 3, 6));
    }
}