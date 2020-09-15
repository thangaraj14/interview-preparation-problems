package dynamicProgramming.fibonacci;

import java.util.Arrays;

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

	public int climbStairs(int N) {
		int[] cache= new int[N+1];
		Arrays.fill(cache,-1);
		return fibUtil(N,0,cache);
	}

	public int fibUtil(int N, int start, int [] cache){
		if(start>N) return 0;

		if(N==start) return 1;

		if(cache[start]!=-1) return cache[start];

		cache[start]= fibUtil(N,start+1, cache)+fibUtil(N,start+2, cache);

		return cache[start];
	}


	public int minCostClimbingStairs(int[] cost) {
		int res = 0;
		//dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2])
		int[] dp = new int[cost.length];

		dp[0] = Math.min(cost[0], cost[1]);
		dp[1] = Math.min(cost[1] + dp[0], cost[1]);
		for(int i = 2; i < cost.length; i++){
			dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
		}
		int n = cost.length;
		System.out.println(Arrays.toString(dp));
		return Math.min(dp[n-1], dp[n-2]);
	}

}


