package sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 3, 6, 8, 2, 1 };

        for (int i = 0; i < arr.length; i++) {
            int tempIndex = 0;
            for (int j = 1; j < arr.length - i; j++) {
                if (tempIndex < arr.length && arr[tempIndex] > arr[j]) {
                    int temp = arr[tempIndex];
                    arr[tempIndex] = arr[j];
                    arr[j] = temp;
                }
                tempIndex++;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
