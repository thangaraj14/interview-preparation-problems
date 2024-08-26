package binarysearch;

public class KthMissingPositive {

    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length; //Initialize right to arr.length instead of arr.length - 1.
                                // This allows the search to consider the position just after the array's end.
                                //[1,2,3,4], k = 2, the answer is 6, not 5.

        while (left < right) {
            int mid = left + (right - left) / 2;

            int diff = arr[mid] - (mid + 1);
            if (diff < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left + k;
    }
}
