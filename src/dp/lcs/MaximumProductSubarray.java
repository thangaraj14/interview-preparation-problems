package dp.lcs;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {

    static int maxSubarrayProduct(int[] A) {
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

    public static void main(String[] args) {
        int[] arr = { 1, -2, -3, 0, 8, 7, -2 };
        System.out.println("Maximum Sub array product is " + maxSubarrayProduct(arr));
    }

}
