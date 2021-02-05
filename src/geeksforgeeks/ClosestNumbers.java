package geeksforgeeks;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/closest-numbers-list-unsorted-integers/
 */
public class ClosestNumbers {

    public static void main(String[] args) {
        //10, 50, 12, 100
        // {5, 4, 3, 2}
        int[] arr = new int[] { 5, 4, 3, 2 };
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        Arrays.sort(arr);

        // Compare differences of adjacent
        // pairs to find the minimum difference.
        int minDiff = arr[1] - arr[0];
        for (int i = 2; i < n; i++)
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);

        // Traverse array again and print all pairs
        // with difference as minDiff.
        for (int i = 1; i < n; i++) {
            if ((arr[i] - arr[i - 1]) == minDiff) {
                System.out.print("(" + arr[i - 1] + ", " + arr[i] + "),");
            }
        }
    }

}
