package dynamicProgramming.oiknapsack;

class O1KnapsackSpaceOptimized {

	public static void main(String[] args) {
		int wt[] = { 2, 3, 4, 5 };
		int val[] = { 1, 2, 4, 6 };
		int W = 7;
		int n = 4;
		//System.out.println(knapSack(val, wt, n, W));
	}

	public int findProfit(int val[], int wt[], int W) {
		int profits[][] = new int[val.length + 1][W + 1];
		for (int i = 0; i <= val.length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					profits[i][j] = 0;
					continue;
				}
//				So, for each item at index ‘i’ (0 <= i < items.length) and capacity ‘c’ (0 <= c <= capacity), we have two options:
//					Include the item at index ‘i’ if its weight is not more than the capacity.
//					In this case, we include its profit plus whatever profit we get from the remaining capacity
//					and from remaining items => profit[i] + dp[i-1][c-weight[i]]
				if (j - wt[i - 1] >= 0) {
					profits[i][j] = Math.max(profits[i - 1][j], profits[i - 1][j - wt[i - 1]] + val[i - 1]);
				}
//					Exclude the item at index ‘i’.
//					In this case, we will take whatever profit we get from the sub-array excluding this item => dp[i-1][c]
				else {
					profits[i][j] = profits[i - 1][j];
				}
			}
		}
		return profits[val.length][W];
	}

//	1) When we access dp[j], it has not been overridden yet for the current iteration, so it should be fine.
//	2) dp[j-weight[i]] might be overridden if “weight[i] > 0”. Therefore we can’t use this value for the current iteration.

//	To solve the second case, we can change our inner loop to process in the reverse direction: c:capacity-->0.
//	This will ensure that whenever we change a value in dp[], we will not need it again in the current iteration.

	public int findProfitSpaceOptimised(int val[], int wt[], int W){
		int profits[] = new int[W + 1];
		// if we have only one weight we take if it's not more than the capacity
		for (int c=0; c<=W;c++){
			if(wt[0]<c) profits[c]=profits[0];
		}

		for (int i = 1; i <= val.length; i++){
			for (int j = W; j >= 0; j--){
				int profit1 = 0, profit2=0;
				if(wt[i-1]<=j){
					profit1= val[i-1]+profits[j-wt[i-1]];
				}
				profit2=profits[j];
				profits[j]= Math.max(profit1,profit2);
			}
		}
			return profits[W];
	}

//	Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
//	Output: 9
//	Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9

	public int backPackII(int m, int[] A, int[] V) {

		int[][] dp= new int[A.length+1][m+1];

		for(int i=1;i<=A.length;i++) {
			for (int j = 1; j <= m; j++) {
				if (A[i - 1] > m) {
					dp[i][j] = dp[i - 1][j];
				} else {
					int prevVal = j >= m ? dp[i - 1][j - m] : 0;
					dp[i][j] = Math.max(dp[i - 1][j],
							V[i - 1] + prevVal);
				}
			}
		}
			return dp[A.length][m];

	}

	public int backPackUnBounded(int m, int[] A) {
		if(m==0 || A==null) return 0;

		int[] dp= new int[m+1];
		//dp[0]=0;

		// in 0-1 knapsack we check max with previous row item, here since we can take multiple of
		// same item, we can take from same row itself, so only 1-D array
		// we take the item A[i]+ we take another item in same row after reducing weight A[i] => dp[j-A[i]]
		for(int i=0; i<A.length ;i++){
			for(int j=m; j>=A[i]; j--){

				dp[j]= Math.max(dp[j],A[i]+dp[j-A[i]]);

			}
		}

		return dp[m];
	}

}