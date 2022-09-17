package binarysearch;

/**
 * https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            // if the mid element is the peak
            if ((mid == 0 || nums[mid - 1] < nums[mid]) && (mid == nums.length - 1 || nums[mid + 1] < nums[mid])) {
                return mid;
            }
            // if the mid element is in middle of increasing sub-arr we move towards the peak of increasing sub-arr
            // mid==0 is for edge case [1,2]
            else if (mid == 0 || nums[mid - 1] < nums[mid] && nums[mid + 1] > nums[mid]) {
                left = mid + 1;
            } else { // else we move back
                right = mid - 1;
            }
        }
        return -1;
    }
}
