package dsa;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
 */
public class FindMinimumInRotatedArray {

    public static int findMin(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = ((end - start) / 2) + start;

            if (arr[start] <= arr[end]) {
                return arr[start];
            }

            if (arr[mid] < arr[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 7, 1, 2, 3, 4, 5, 6 };
        findMin(arr);
    }
}

