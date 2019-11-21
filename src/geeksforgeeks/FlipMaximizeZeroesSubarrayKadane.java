package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximize-number-0s-flipping-subarray/
 */
class FlipMaximizeZeroesSubarrayKadane {

    public static int findMaxZeroCount(int arr[], int n) {
        int zeroCount = 0;
        int maxSoFar = 0;
        int maxEndingHere = 0;

        for (int i = 0; i < n; i++) {

            if (arr[i] == 0)
                zeroCount++;

            int val = (arr[i] == 1) ? 1 : -1;

            maxEndingHere = Math.max(val, maxEndingHere + val);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        maxSoFar = Math.max(0, maxSoFar);

        return zeroCount + maxSoFar;
    }

    public static void main(String[] args) {
        int arr[] = {0, 1, 0, 0, 1, 1, 0};

        System.out.println(findMaxZeroCount(arr, arr.length));
    }
}