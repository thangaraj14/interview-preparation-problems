package dynamicProgramming.lcs;

class MaximumSumIncreasingSubsequence {
	
	static int maxSumIS(int arr[], int n) {
		int i, j, max = 0;
		int msis[] = new int[n];

		for (i = 0; i < n; i++)
			msis[i] = arr[i];

		for (i = 1; i < n; i++) {
			for (j = 0; j < i; j++) {
				System.out.println(arr[i] + ">" + arr[j] + "&&" + msis[i] + "<" + (msis[j] + arr[i]));
				if (arr[i] > arr[j] && msis[i] < msis[j] + arr[i])
					msis[i] = msis[j] + arr[i];
			}
		}
		// Pick maximum of all msis values
		for (i = 0; i < n; i++)
			if (max < msis[i])
				max = msis[i];

		return max;
	}

	public static void main(String args[]) {
		int arr[] = new int[] { 1, 1001, 2, 3, 100, 4, 5 };
		int n = arr.length;
		System.out.println("Sum of maximum sum " + "increasing subsequence is " + maxSumIS(arr, n));
	}
}