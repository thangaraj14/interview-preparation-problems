package binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 * Revise
 */
public class KClosestElements {
    /**
     * Now let's see how x - arr[mid] > arr[mid + k] - x works:
     * Say this array is sorted from small to large: [x1, arr[mid], x2, arr[mid + k], x3]
     *
     *     Case x1:
     *     x1 is smaller than both arr[mid] and arr[mid + k]. We should recurse into the left.
     *     x - arr[mid] is negative and arr[mid + k] - x is positive.
     *     The condition is false and we recurse to left.
     *
     *     Case x2:
     *     x2 is in between arr[mid] and arr[mid + k]. We should recurse to the side that is closer to x.
     *     x - arr[mid] and arr[mid + k] - x are both positive.
     *     The comparison works the same as using abs(); we recursive to the closer side.
     *     (When in the case of arr[mid] == arr[mid + k], the condition is false and we recurse to the left.)
     *
     *     Case x3:
     *     x3 is larger than both arr[mid] and arr[mid + k]. We should recurse into the right.
     *     x - arr[mid] is positive and arr[mid + k] - x is negative.
     *     The condition is true and we recurse to right.
     *     Now the previously failed corner case arr[mid] == arr[mid + k] < x3 doesn't cause a problem anymore
     *     because the positivity of the numbers we're comparing is still the same.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Initialize binary search bounds
        // we need to find the left most element which will be the start of the result set
        int left = 0;
        // the farthest it can go is array-k
        int right = arr.length - k; // else the mid+k will go out of bound

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            // If the element at arr[mid] is closer to x than arr[mid + k],
            // then that means arr[mid + k], as well as every element to the right of it can never be in the answer.
            // This means we should move our right pointer to avoid considering them
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public List<Integer> findClosestElementsIntuitive(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1, mid;
        List<Integer> res = new ArrayList<>();

        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int leftPointer = left - 1;
        int rightPointer = left;

        while (rightPointer - leftPointer <= k) {
            if (leftPointer < 0) {
                rightPointer += 1;
                continue;
            }
            if (rightPointer == arr.length || Math.abs(arr[leftPointer] - x) <= Math.abs(arr[rightPointer] - x)) {
                leftPointer -= 1;
            } else {
                rightPointer += 1;
            }
        }

        for (int i = leftPointer + 1; i < rightPointer; i++) {
            res.add(arr[i]);
        }

        return res;
    }

    public List<Integer> findClosestElementsTwoPointers(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
                lo++;
            } else {
                hi--;
            }
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = lo; i <= hi; i++) {
            result.add(arr[i]);
        }
        return result;
    }

}