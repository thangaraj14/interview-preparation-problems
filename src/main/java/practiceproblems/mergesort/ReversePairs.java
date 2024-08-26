package practiceproblems.mergesort;

public class ReversePairs {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);

    }

    int mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int invCount = 0;
        int mid = ((right + left) / 2);

        invCount += mergeSort(arr, left, mid);
        invCount += mergeSort(arr, mid + 1, right);
        // Count reverse pairs across the two sorted halves
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if ((long) arr[i] > 2 * (long) arr[j]) {
                invCount += (mid - i + 1); // All elements from i to mid are greater than 2*nums[j]
                j++;
            } else {
                i++;
            }
        }
        merge(arr, left, mid, right);
        return invCount;
    }

    void merge(int[] arr, int left, int mid, int right) {
        int invCount = 0;
        int rightArrStart = mid + 1;
        int[] temp = new int[arr.length];

        int i = left;
        int j = mid + 1;
        int k = left;
        while ((i <= mid) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];
    }

}
