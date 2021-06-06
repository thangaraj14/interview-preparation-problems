package dsa;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * https://leetcode.com/problems/climbing-stairs/
 */
public class FibonacciStaircaseWaysToCoverDist {

    public static void main(String[] args) {
        FibonacciStaircaseWaysToCoverDist fs = new FibonacciStaircaseWaysToCoverDist();
        System.out.println(fs.fibonacciSeries(5));
        System.out.println(climbStairs(5));
        System.out.println(minCostClimbingStairsRec(new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 }));
    }

    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 2], cost[i - 1]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }

    public int fibonacciSeries(int n) {
        int n1 = 0;
        int n2 = 1;

        if (n == n1 || n == n2) {
            return n;
        }

        int sum;
        for (int i = 1; i <= n; i++) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return n2;
    }

    /**
     * when we are in 2 ->
     *
     * @param n
     *
     * @return
     */
    public static int climbStairs(int n) {

        Integer[] arr = new Integer[n + 1];
        return climbStairsUtil(arr, n);

    }

    private static int climbStairsUtil(Integer[] arr, int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        if (arr[n] != null) {
            return arr[n];
        }

        arr[n] = climbStairsUtil(arr, n - 1) + climbStairsUtil(arr, n - 2);
        return arr[n];
    }

    static int[] dp;

    public static int minCostClimbingStairsRec(int[] cost) {
        int n = cost.length;
        dp = new int[n];
        return Math.min(minCost(cost, n - 1), minCost(cost, n - 2));
    }

    private static int minCost(int[] cost, int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return cost[n];
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = cost[n] + Math.min(minCost(cost, n - 1), minCost(cost, n - 2));
        return dp[n];
    }
}