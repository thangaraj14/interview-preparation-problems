package sorting;

import java.util.Random;

class QuickSelect {

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static int partition(int[] A, int low, int high, int pivotIndex) {
        int pivot = A[pivotIndex];
        swap(A, pivotIndex, high);
        int iLow = low;
        for (int i = low; i < high; i++) {
            if (A[i] <= pivot) {
                swap(A, i, iLow);
                iLow++;
            }
        }
        swap(A, iLow, high);
        return iLow;
    }

    public static int quickSelect(int[] A, int left, int right, int k) {
        if (left == right) {
            return A[left];
        }
        int pivotIndex = new Random().nextInt(right - left + 1) + left;
        pivotIndex = partition(A, left, right, pivotIndex);
        if (k == pivotIndex) {
            return A[k];
        } else if (k < pivotIndex) {
            return quickSelect(A, left, pivotIndex - 1, k);
        } else {
            return quickSelect(A, pivotIndex + 1, right, k);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 7, 4, 6, 3, 9, 1 };
        int k = 2;
        System.out.print("K'th smallest element is " + quickSelect(arr, 0, arr.length - 1, k));
    }
}