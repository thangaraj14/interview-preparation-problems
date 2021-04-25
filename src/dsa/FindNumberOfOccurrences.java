package dsa;

import java.util.Arrays;

/*https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/*/
public class FindNumberOfOccurrences {

    public static void main(String[] args) {
        int arr[] = { 5, 7, 7, 8, 8, 10 };
        FindNumberOfOccurrences numberOfOccurrences = new FindNumberOfOccurrences();
        System.out.println(Arrays.toString(numberOfOccurrences.searchRange(arr, 8)));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] { -1, -1 };
        }
        int[] result = new int[2];
        result[0] = binarySearch(nums, target, false);
        if (result[0] == -1) {
            result[1] = -1;
            return result;
        }
        result[1] = binarySearch(nums, target, true);
        return result;
    }

    public int binarySearch(int[] arr, int target, boolean findMaxIndex) {
        int start = 0;
        int end = arr.length - 1;
        int key = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                key = mid;
                if (findMaxIndex) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return key;
    }
}
