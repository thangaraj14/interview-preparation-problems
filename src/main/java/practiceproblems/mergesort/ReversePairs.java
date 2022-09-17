package practiceproblems.mergesort;

public class ReversePairs {

    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSort(nums, temp, 0, nums.length - 1);

    }

    int mergeSort(int[] arr, int[] temp, int left, int right) {
        int mid;
        int invCount = 0;
        if (left < right) {

            mid = ((right + left) / 2);

            invCount = mergeSort(arr, temp, left, mid);
            invCount += mergeSort(arr, temp, mid + 1, right);

            invCount += merge(arr, temp, left, mid, right);
        }
        return invCount;
    }

    int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int invCount = 0;
        int rightArrStart = mid + 1;

        /**
         * the idea is move the rightArrStart for all the valid 2 * arr[j]
         * fox ex leftArr = [12,19,28] and rightArr = [2,10,12]
         *
         *  when  leftArrPointer = 12
         *        rightArrPointer = 10
         *     the while loop breaks and the count is added as (rightArrPointer - mid+1) => 1
         *
         *  we then increment leftArrPointer
         *
         *  when  leftArrPointer = 19
         *        rightArrPointer = 10
         *       the while loop is skipped and the count is added as (rightArrPointer - mid+1) => 1  because the previous 2 values is
         *       lesser than the 19(2*2 < 19)
         *
         *   we then increment leftArrPointer
         *
         *   when  leftArrPointer = 28
         *         rightArrPointer = 10
         *
         *         the while loop breaks because no more value in rightArr
         *         and the count is added as (rightArrPointer - mid+1) => 3
         *
         *
         */
        for (int leftArrStart = left; leftArrStart <= mid; leftArrStart++) {

            while (rightArrStart <= right && arr[leftArrStart] > 2 * (long) arr[rightArrStart]) {
                rightArrStart++;
            }
            invCount += (rightArrStart - (mid + 1)); // this gives the length of right array's valid window
        }

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

        return invCount;
    }

}
