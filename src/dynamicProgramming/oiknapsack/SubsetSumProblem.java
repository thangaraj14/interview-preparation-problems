package dynamicProgramming.oiknapsack;

import java.util.ArrayList;

//Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.

/**
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 *
 * Input: {1, 2, 7, 1, 5}, S=10
 * Output: True
 * The given set has a subset whose sum is '10': {1, 2, 7}
 */

//				  0	  1	  2   3   4   5   6
//				+---+---+---+---+---+---+---+
//	{1}			| T | T | F | F | F | F | F |
//				+---+---+---+---+---+---+---+
//	{1,2}		| T | T | T | T | F | F | F |
//				+---+---+---+---+---+---+---+
//  {1,2,3}		| T | T | T | T | T | T | T |
//				+---+---+---+---+---+---+---+
//	{1,2,3,7}	| T | T | T | T | T | T | T |
//				+---+---+---+---+---+---+---+
public class SubsetSumProblem {

	static boolean[][] dp;

	static void printAllSubsets(int arr[], int n, int sum) {
		if (n == 0 || sum < 0)
			return;

		dp = new boolean[n][sum + 1];
		for (int i = 0; i < n; ++i) {
			dp[i][0] = true;
		}

		if (arr[0] <= sum)
			dp[0][arr[0]] = true;

		for (int i = 1; i < n; ++i) {
			for (int j = 0; j <= sum; j++) {
				if (arr[i] <= j) {
					dp[i][j] = (dp[i - 1][j] || dp[i - 1][j - arr[i]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		if (dp[n - 1][sum] == false) {
			System.out.println("There are no subsets with" + " sum " + sum);
			return;
		}

		ArrayList<Integer> p = new ArrayList<>();
	}

	public boolean canPartition(int[] nums) {
		if(nums==null || nums.length==0) return false;

		int sum=0;
		for(int i: nums){
			sum+=i;
		}
		// odd numbers cannot be partitioned
		if ((sum & 1) == 1) {
			return false;
		}
		sum/=2; // checking for half of the the sum
		boolean[][] dp= new boolean[nums.length+1][sum+1];

		for(int i=0; i<=nums.length;i++){
			dp[i][0]= true; // first index of all rows are true because 0 is present in 0,0
		}
		for(int i=1;i<=nums.length; i++){
			for(int j=1; j<=sum;j++){

				if(nums[i-1]>j){
					dp[i][j]= dp[i-1][j];
				}else{
					dp[i][j]= (dp[i - 1][j] || dp[i-1][j-nums[i-1]]);
				}
			}
		}



		return dp[nums.length][sum];
	}

	public static void main(String args[]) {
		int arr[] = { 2, 3, 5, 8, 10 };
		int n = arr.length;
		int sum = 10;
		printAllSubsets(arr, n, sum);
	}
}