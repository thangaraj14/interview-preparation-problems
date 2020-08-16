package geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/closest-numbers-list-unsorted-integers/
 * Given a list of distinct unsorted integers,
 * find the pair of elements that have the smallest absolute difference between them? 
 * If there are multiple pairs, find them all.
 * Input : arr[] = {10, 50, 12, 100}
Output : (10, 12)
The closest elements are 10 and 12

Input : arr[] = {5, 4, 3, 2}
Output : (2, 3), (3, 4), (4, 5)
 */
public class ClosestNumbers {

    public static void main(String[] args) {
        //10, 50, 12, 100
        int[] arr = new int[] { 10, 50, 12, 100 };
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        // Sort array elements
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
