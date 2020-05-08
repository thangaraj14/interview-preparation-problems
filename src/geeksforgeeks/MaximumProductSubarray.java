package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximum-product-subarray/
 */
public class MaximumProductSubarray {

    // 1, -2, -3, 0, 8, 7, -2
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

<<<<<<< HEAD:src/geeksforgeeks/MaximumProductSubarray.java
=======
    public static int maxSumSubArray(int[] arr) {

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum < 0) {
                //get i if you want to get index of subarray
                sum = 0;
            }
            max = Math.max(sum, max);
        }
        return sum;
    }

>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163:src/geeksforgeeks/MaximumSubarray.java
    public static void main(String[] args) {
        int arr[] = { 1, -2, -3, 0, 8, 7, -2 };
        System.out.println("Maximum Sub array sum is " + maxSumSubArray(arr));
        System.out.println("Maximum Sub array product is " + maxProductSubArray(arr));
    }
}
