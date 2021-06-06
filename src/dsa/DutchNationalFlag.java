package dsa;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-colors/
 */
class DutchNationalFlag {

    public void sortColors(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int i = 0;
        int end = arr.length - 1;
        int start = 0;
        while (i <= end) {

            switch (arr[i]) {
                case 0:
                    swap(arr, start, i);
                    start++;
                    i++;
                    break;
                case 1:
                    i++;
                    break;
                case 2:
                    swap(arr, i, end);
                    end--;
                    break;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag();
        int[] arr = new int[] { 2, 0, 2, 1, 1, 2 };
        dutchNationalFlag.sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }
}