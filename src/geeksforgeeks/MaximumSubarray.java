package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximum-product-subarray/
 */
public class MaximumSubarray {

    public static int maxProductSubArray(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        int maxHerePre = A[0];
        int minHerePre = A[0];
        int maxsofar = A[0];

        for (int i = 1; i < A.length; i++) {
            int maxHere = Math.max(Math.max(maxHerePre * A[i], minHerePre * A[i]), A[i]);
            int minHere = Math.min(Math.min(maxHerePre * A[i], minHerePre * A[i]), A[i]);
            maxsofar = Math.max(maxHere, maxsofar);
            maxHerePre = maxHere;
            minHerePre = minHere;
        }
        return maxsofar;
    }

    public static int maxSumSubArray(int[] nums) {

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }


    public static void main(String[] args) {
        int arr[] = {1, -2, -3, 0, 8, 7, -2};
        System.out.println("Maximum Sub array product is " + maxSumSubArray(arr));
    }

}
