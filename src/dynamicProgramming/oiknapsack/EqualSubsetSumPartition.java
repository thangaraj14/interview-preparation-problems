package dynamicProgramming.oiknapsack;

import java.util.Arrays;

/**
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5752754626625536
 *
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
class EqualSubsetSumPartition {


// 			  0	  1	  2   3   4  5
//			+---+---+---+---+---+---+
//	{1}		| T | T | F | F | F | F |
//			+---+---+---+---+---+---+
//	{1,2}	| T | T | T | T | F | F |
//			+---+---+---+---+---+---+
//	{1,2,3}	| T | T | T | T | T | T |
//			+---+---+---+---+---+---+
//{1,2,3,4} | T | T | T | T | T | T |
//			+---+---+---+---+---+---+
	public boolean canPartition(int[] num) {
		int n = num.length;

		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += num[i];

		// if sum is odd we cannot divide into 2 parts
		if (sum % 2 != 0)
			return false;

		sum /= 2;

		boolean[][] dp = new boolean[n][sum + 1];

		for (int i = 0; i < n; i++)
			dp[i][0] = true;

		// with only one number, we can form a subset only when the required sum is
		// equal to its value
		for (int s = 1; s <= sum; s++) {
			System.out.println(num[0] + "-" + s);
			dp[0][s] = (num[0] == s ? true : false);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= sum; j++) {
				// if we can get the sum 's' without the number at index 'i'
				if (dp[i - 1][j]) {
					dp[i][j] = dp[i - 1][j];
				} else if (j >= num[i]) { // else if we can find a subset to get the remaining sum
					dp[i][j] = dp[i - 1][j - num[i]];
				}
			}
		}

		System.out.println(Arrays.deepToString(dp));

		return dp[n - 1][sum];
	}

	public static void main(String[] args) {
		EqualSubsetSumPartition ps = new EqualSubsetSumPartition();
		int[] num = { 2, 3, 4, 5 };
		System.out.println(ps.canPartition(num));
	}
}
