package sorting;

import java.util.Arrays;

class MergeSort {

    static void mergeSort(int[] arr, int[] temp, int start, int end) {
        if (start < end) {
            int mid = ((end - start) / 2) + start;

            mergeSort(arr, temp, start, mid);
            mergeSort(arr, temp, mid + 1, end);

            merge(arr, temp, start, mid, end);
        }
    }

    /**
     * 9, 3, 7, 5, 6, 4, 8, 2
     * start =0 ; mid =1 ; end=3
     * <p>
     * compare arr[0] < arr[2] (3<5)
     */
    static void merge(int[] arr, int[] temp, int start, int mid, int end) {

        int iStart = start;
        int jMid = mid + 1;
        int index = start;

        while ((iStart <= mid) && (jMid <= end)) {
            if (arr[iStart] < arr[jMid]) {
                temp[index++] = arr[iStart++];
            } else {
                temp[index++] = arr[jMid++];
            }
        }

        while (iStart <= mid)
            temp[index++] = arr[iStart++];
        while (jMid <= end)
            temp[index++] = arr[jMid++];

        for (iStart = start; iStart <= end; iStart++) {
            arr[iStart] = temp[iStart];
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[] { 9, 3, 7, 5, 6, 4, 8, 2 };
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
