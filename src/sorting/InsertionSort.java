package sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6 };
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int tempIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (value < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[tempIndex];
                    arr[tempIndex] = temp;
                    tempIndex--;
                } else {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
