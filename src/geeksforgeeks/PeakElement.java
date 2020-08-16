package geeksforgeeks;

/**
 * https://leetcode.com/problems/find-peak-element/
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 */
public class PeakElement {

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int result = -1;

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1]))
                return mid;
            else if (mid > 0 && nums[mid - 1] > nums[mid]) end = mid - 1;
            else if (nums[mid + 1] > nums[mid]) start = mid + 1;

        }
        return result;
    }

    public static void main(String[] args) {
        PeakElement pe = new PeakElement();
        int[] arr = {1, 1, 1, 3, 2, 1, 2};
        System.out.println(arr[pe.findPeakElement(arr)]);
    }

}
