package practiceproblems;

/**
 * https://leetcode.com/problems/sort-colors/
 */
class DutchNationalFlag {
    public void sortColors(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                //whatever comes from the low index has already been processed,
                // so it's safe to increment both low and mid
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high);
                //The reason we don't increment mid in this case is that after the swap,
                // we're not sure what value is now at arr[mid].
                // It could be 0, 1, or 2. We need to examine this new value in the next iteration.
                high--;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}