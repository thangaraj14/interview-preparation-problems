package practiceproblems;

/**
 * https://leetcode.com/problems/sort-colors/
 * tricky
 */
class DutchNationalFlag {
    public void sortColors(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int pivot = 1;
        int i = 0;
        int j = arr.length - 1;
        int zeroPos = 0;

        while (i <= j) {
            if (arr[i] > pivot) {
                swap(arr, i, j);
                /**
                 * I guess to answer why we increment i when nums[i] == 0 and not do the same when nums[i] == 2,
                 * the easy way for me to remember is, after we swap the 2 we still need to check the current idx
                 * because it maybe 1 or 0 at the current index.
                 * But when nums[i] == 0, we pretty sure there are no 2 to the left of current idx since we walked from the left to right and
                 * already swapped all the 2.
                 */
                // no need to increment i here
                // ex case [1,2,0]
                j--;
            } else if (arr[i] == pivot) {
                i++;
            } else {
                swap(arr, zeroPos, i);
                zeroPos++;
                i++;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}