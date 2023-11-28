package practiceproblems;


/**
 * https://www.geeksforgeeks.org/maximize-number-0s-flipping-subarray/
 * <p>
 * Problem : flip 1's to 0's so that total no.of 0's in array is maximized
 */
class FlipMaximizeZeroesSubarrayKadane {

    public static int findMaxZeroCount(int[] arr, int n) {
        int zeroCount = 0;
        int maxSoFar = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < n; i++) {

            if (arr[i] == 0) {
                zeroCount++;
            }

            int val = (arr[i] == 1) ? 1 : -1;

            sum = sum + val;

            if (sum < 0) {
                sum = 0;
            }
            maxSoFar = Math.max(maxSoFar, sum);
        }
        maxSoFar = Math.max(0, maxSoFar);

        return zeroCount + maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 0, 1, 1, 0};

        System.out.println(findMaxZeroCount(arr, arr.length));
    }

}