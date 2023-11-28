package cess;

/**
 * Bitmask
 * <p>
 * https://cses.fi/problemset/task/1623
 * https://www.youtube.com/watch?v=raGn3saVfa8
 */
public class AppleDivision {

    public static long solve(int[] arr) {
        long result = Long.MAX_VALUE;
        int n = arr.length;
        for (int mask = 0; mask < (1 << n); mask++) {

            long sumA = 0;
            long sumB = 0;

            for (int pos = 0; pos < n; pos++) {
                if ((mask & (1 << pos)) > 0) {
                    sumA += arr[pos];
                } else {
                    sumB += arr[pos];
                }
            }
            result = Math.min(result, Math.abs(sumA - sumB));

        }
        return result;
    }
}
