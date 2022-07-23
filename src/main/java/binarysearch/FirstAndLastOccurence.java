package binarysearch;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
 */
public class FirstAndLastOccurence {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) return result;

        int first = binarySearch(nums, target);

        if (first == nums.length || nums[first] != target) return result;

        result[0] = first;
        int last = binarySearch(nums, target + 1);

        result[1] = nums[last] == target ? last : last - 1;

        return result;

    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;

            }
        }

        return left;
    }
}