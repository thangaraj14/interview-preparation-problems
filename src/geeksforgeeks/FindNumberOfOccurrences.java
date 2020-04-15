package geeksforgeeks;

import java.util.Arrays;

/*https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/*/
public class FindNumberOfOccurrences {

    public static void main(String[] args) {
        int arr[] = { 5, 7, 7, 8, 8, 10 };
        FindNumberOfOccurrences numberOfOccurrences = new FindNumberOfOccurrences();
        System.out.println(Arrays.toString(numberOfOccurrences.searchRange(arr, 8)));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[] { -1, -1 };
        if (nums.length == 0) {
            return result;
        }

        int start = 0;
        int end = nums.length - 1;
        int len = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if ((mid == 0 && nums[mid] == target) || (nums[mid] == target && nums[mid - 1] != target)) {
                result[0] = mid;
                break;
            } else if (nums[mid] == target && nums[mid - 1] == target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        start = 0;
        end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((mid == len && nums[mid] == target) || (nums[mid] == target && nums[mid + 1] != target)) {
                result[1] = mid;
                break;
            } else if (nums[mid] == target && nums[mid + 1] == target) {
                start = mid + 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }

}
