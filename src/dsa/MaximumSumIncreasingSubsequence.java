package dsa;

/**
 * https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
 * start from the beginning and iterate through the loop
 * <p>
 * check for Increasing subsequence in the first conditional and sum in the second condition
 */
class MaximumSumIncreasingSubsequence {

    static int maxSumIS(int[] arr, int n) {
        int i;
        int j;
        int max = 0;
        int[] result = new int[n];

        for (i = 0; i < n; i++) {
            result[i] = arr[i];
        }

        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
                System.out.println(arr[i] + ">" + arr[j] + "&&" + result[i] + "<" + (result[j] + arr[i]));
                if (arr[i] > arr[j] && result[i] < result[j] + arr[i]) {
                    result[i] = result[j] + arr[i];
                    max = Math.max(result[i], max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 501, 2, 3, 100, 4, 5 };
        int n = arr.length;
        System.out.println("Sum of maximum sum " + "increasing subsequence is " + maxSumIS(arr, n));
    }
}