package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/
 */
public class SumMutatedArrayCloseTarget {
    Integer closest = Integer.MAX_VALUE;
    Integer minElem = 0;

    public int findBestValue(int[] arr, int target) {
        int left = 0;
        int right = Arrays.stream(arr).max().orElse(0) + 1; // +1 is to extend the window slightly to overcome edge cases

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isConditionSatisfies(mid, arr, target)) {
                right = mid;
            } else {
                left = mid + 1;

            }
        }

        return minElem;
    }

    public boolean isConditionSatisfies(int mid, int[] arr, int target) {
        int total = 0;
        for (int j : arr) {
            total += Math.min(j, mid);

        }
        if (Math.abs(target - total) < closest) {
            closest = Math.abs(target - total);
            minElem = mid;
        } else if (Math.abs(target - total) == closest) {
            minElem = Math.min(minElem, mid);
        }

        return total >= target;
    }
}
