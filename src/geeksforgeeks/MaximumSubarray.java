package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximum-product-subarray/
 */
public class MaximumSubarray {

    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     *
     * @param A
     *
     * @return
     */
    // 1, -2, -3, 0, 8, 7, -2
    public static int maxProductSubArray(int[] A) {

        if (A.length == 0) {
            return 0;
        }

        int maxHerePre = A[0];
        int minHerePre = A[0];
        int maxSoFar = A[0];

        for (int i = 1; i < A.length; i++) {
            int maxHere = Math.max(Math.max(maxHerePre * A[i], minHerePre * A[i]), A[i]);
            int minHere = Math.min(Math.min(maxHerePre * A[i], minHerePre * A[i]), A[i]);
            maxSoFar = Math.max(maxHere, maxSoFar);
            maxHerePre = maxHere;
            minHerePre = minHere;
        }
        return maxSoFar;
    }

    /**
     * @param nums https://leetcode.com/problems/maximum-subarray/
     *
     * @return
     */
    public static int maxSumSubArray(int[] nums) {

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /**
     * https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
     */
    public static int maximumSum(int[] arr) {
        int n = arr.length;
        int[] maxWithOneDeletion = new int[n];
        int[] maxWithNoDeletion = new int[n];
        int max = Integer.MIN_VALUE;
        maxWithNoDeletion[0] = arr[0];
        max = Math.max(maxWithNoDeletion[0], max);
        for (int i = 1; i < n; i++) {
            maxWithOneDeletion[i] = Math.max(maxWithNoDeletion[i - 1], maxWithOneDeletion[i - 1] + arr[i]);
            maxWithNoDeletion[i] = Math.max(maxWithNoDeletion[i - 1] + arr[i], arr[i]);
            max = Math.max(maxWithOneDeletion[i], max);
            max = Math.max(maxWithNoDeletion[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = { 2, -4, 7, -6, 1, 9, -3 };
  /*      System.out.println("Maximum Sub array sum is " + maxSumSubArray(arr));
        System.out.println("Maximum Sub array product is " + maxProductSubArray(arr));*/
        System.out.println(maxSumSubArray(arr));
    }
}
