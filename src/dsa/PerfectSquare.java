package dsa;

/**
 * https://leetcode.com/problems/perfect-squares/
 * <p>
 * https://www.youtube.com/watch?v=dOOzOsfj31I&ab_channel=KnowledgeCenter
 */
class PerfectSquare {

    public int numSquaresDp(int n) {
        int[] dp = new int[n + 1];
        for (int x = 1; x <= n; ++x) {
            int minVal = x;
            int temp = 1;
            int square = 1;
            while (square <= x) {
                minVal = Math.min(minVal, 1 + dp[x - square]);
                temp++;
                square = temp * temp;
            }
            dp[x] = minVal;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquare ps = new PerfectSquare();
        System.out.println(ps.numSquaresDp(13));
    }
}