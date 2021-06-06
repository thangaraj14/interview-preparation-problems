package sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] ints = selectionSort(new int[] { 4, 3, 6, 8, 2, 1 });
        System.out.println(Arrays.toString(ints));
    }

    static int[] selectionSort(int[] arr) {
        if (arr.length == 0) {
            return null;
        }

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }

        return arr;
    }
}
