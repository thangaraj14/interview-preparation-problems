package sorting;

import java.util.Arrays;

public class MinHeap {

    public static void main(String[] args) {
        int[] arr = { 7, 4, 9, 2, 77, -54, 3, 1, 12, 16, 19, 20 };
        MinHeap heap = new MinHeap();
        heap.sort(arr);
    }

    void sort(int[] arr) {
        int n = (arr.length / 2) - 1;
        for (int i = n; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));

    }

    private void heapify(int[] arr, int i, int length) {
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        int smallest = i;
        if (left < length && arr[left] > arr[i]) {
            smallest = left;
        }

        if (right < length && arr[right] > arr[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            heapify(arr, smallest, length);
        }
    }

}
