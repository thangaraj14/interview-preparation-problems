package dynamicProgramming.fibonacci;

import java.util.Arrays;
import java.util.HashMap;

public class FibonacciStaircaseWaysToCoverDist {

    public int fibonacciSeriesRecursive(int n) {
        if (n == 1)
            return 2;
        if (n == 2)
            return 3;
        return fibonacciSeriesRecursive(n - 1) + fibonacciSeriesRecursive(n - 2);
    }

    public static void main(String args[]) {
        FibonacciStaircaseWaysToCoverDist fs = new FibonacciStaircaseWaysToCoverDist();
        System.out.println(fs.fibonacciSeries(4));
        System.out.println(fs.fibonacciSeriesRecursive(3));
    }

    public int fibonacciSeries(int n) {
        int n1 = 0;
        int n2 = 1;
        int sum;

        if (n == n1 || n == n2) {
            return n;
        }

        for (int i = 1; i <= n; i++) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return n2;
    }


    public int climbStairsBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int stair = 3; stair <= n; ++stair) {
            dp[stair] += dp[stair - 1] + dp[stair - 2];
        }

        return dp[n];
    }

    public int climbStairs(int N) {
        int[] cache = new int[N + 1];
        Arrays.fill(cache, -1);
        return fibUtil(N, 0, cache);
    }

    public int fibUtil(int N, int start, int[] cache) {
        if (start > N) return 0;

        if (N == start) return 1;

        if (cache[start] != -1) return cache[start];

        cache[start] = fibUtil(N, start + 1, cache) + fibUtil(N, start + 2, cache);

        return cache[start];
    }


    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) return Math.min(cost[0], cost[1]);
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    private HashMap<Integer, Integer> memo = new HashMap<>();

    public int minCostClimbingStairsRecursion(int[] cost) {
        return minimumCost(cost.length, cost);
    }

    private int minimumCost(int i, int[] cost) {
        // Base case, we are allowed to start at either step 0 or step 1
        if (i <= 1) {
            return 0;
        }

        // Check if we have already calculated minimumCost(i)
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        // If not, cache the result in our hash map and return it
        int downOne = cost[i - 1] + minimumCost(i - 1, cost);
        int downTwo = cost[i - 2] + minimumCost(i - 2, cost);
        memo.put(i, Math.min(downOne, downTwo));
        return memo.get(i);
    }

}


