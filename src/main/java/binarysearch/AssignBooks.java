package binarysearch;

import java.util.Arrays;

// https://leetcode.com/problems/split-array-largest-sum/
//https://takeuforward.org/data-structure/allocate-minimum-number-of-pages/
public class AssignBooks {

    private static int getPieces(int[] nums, int mid) {
        int sum = 0; // Current sum of the subarray
        int pieces = 1; // At least one subarray is needed

        for (int num : nums) {
            if (sum + num > mid) { // If adding num to current sum exceeds mid
                sum = num; // Start a new subarray
                pieces++; // Increase the count of subarrays
            } else {
                sum += num; // Continue adding to the current subarray
            }
        }
        return pieces;
    }

    public int splitArray(int[] nums, int k) {
        // Initial boundary values for binary search
        int start = Arrays.stream(nums).max().orElse(0); // The minimum possible largest sum is the max element in the array
        int end = Arrays.stream(nums).sum(); // The maximum possible largest sum is the sum of all elements in the array

        // Binary search for the minimum possible largest sum
        while (start < end) {
            int mid = start + (end - start) / 2; // Middle value between start and end
            int pieces = getPieces(nums, mid);

            // If more subarrays are needed than allowed, increase the lower bound
            if (pieces > k) {
                start = mid + 1; // Increase the lower bound since mid is too small
            } else {
                end = mid; // Decrease the upper bound since mid could be the answer
            }
        }

        // When start == end, we've found the minimum possible largest sum
        return start; // or return end; since start == end
    }
}
