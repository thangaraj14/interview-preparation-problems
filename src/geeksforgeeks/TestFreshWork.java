package geeksforgeeks;

import java.util.Random;

/**
 *
 */

//unsorted array : 8,1,6,2,4,5 k=3
// output : 4

// bruteforce : sort : o n log n
// priority queue : n log k
// n --> --> n log k -> n log n -> n2
// Quick select : o (n) worst case : o (n2)
// worst case : o (n)
public class TestFreshWork {

    public static void main(String[] args) {
        int[] arr = { 8, 1, 6, 2, 4, 5 };
        TestFreshWork testFreshWork = new TestFreshWork();
        System.out.println(testFreshWork.sort(arr, 3, 0, arr.length - 1));

    }

    private int sort(int[] arr, int k, int start, int end) {

        if (start < end) {

            int pivot = new Random().nextInt(((end - start) / 2) + start);
            // pivot = 4
            int value = partition(arr, start, end, pivot);
            if (k == value) {
                return arr[k];
            } else if (k < value) {
                sort(arr, k, start, value - 1);
            } else {
                sort(arr, k, value, end);
            }
        }
        return -1;
    }

    //{ 8, 1, 6, 2, 4, 5 };
    // 1,2,4,6,8,5
    // 1,2,4,5,6,8
    private int partition(int[] arr, int start, int end, int pivot) {

        int iPivot = arr[pivot];
        int i = start;
        int j = end - 1;
        swap(end, pivot, arr);

  /*      while (arr[i] <) {

        }

        while (arr[j] <) {
        }*/

        // 8,1,6,2,5,4
        // 1,8,6,2,5,4
        // 1,6,8,2,5,4
        // 1,2,8,6,5,4

        // 1,2,4,6,5,8

        return 0;

    }

    private void swap(int start, int pivot, int[] arr) {
        int temp = arr[start];
        arr[start] = arr[pivot];
        arr[pivot] = temp;
    }
}
