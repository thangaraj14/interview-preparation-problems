package sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = { 8, 9, 2, 5, 10, 1, 3 };
        quickSort(0, arr.length - 1, arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int low, int high, int[] arr) {
        if (low < high) {
            int mid = partition(low, high, arr);
            quickSort(low, mid - 1, arr);
            quickSort(mid + 1, high, arr);
        }
    }

    private static void swap(int[] arr, int low, int iLow) {
        int temp = arr[low];
        arr[low] = arr[iLow];
        arr[iLow] = temp;
    }

    private static int partition(int start, int end, int[] arr) {

        Random rand = new Random();
        int pivotIndex = start + rand.nextInt(Math.abs(end - start));
        swap(arr, pivotIndex, end);

        int pivot = arr[end];
        int iLow = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, iLow);
                iLow++;
            }
        }
        swap(arr, iLow, end);
        return iLow;
    }

    public int findPivot(int[] nums, int start, int end) {
        Random rand = new Random();
        //Get a random pivot between beg and end
        int pivotIndex = start + rand.nextInt(Math.abs(end - start));

        //storing last index since New position of pivot element is at last
        int last = end;

        //Move the pivot element to right edge of the array
        swap(nums, pivotIndex, end);

        end--;

        while (start <= end) {
            if (nums[start] < nums[last]) {
                start++; //Accumulate smaller elements to the left
            } else {
                swap(nums, start, end);
                end--;
            }
        }
        //Move pivot element to its correct position
        swap(nums, start, last);
        return start;
    }

}
