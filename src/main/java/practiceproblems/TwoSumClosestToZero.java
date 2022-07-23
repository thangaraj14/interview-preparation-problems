package practiceproblems;

import java.util.Arrays;

public class TwoSumClosestToZero {

    public static void main(String[] args) {
        Integer arr[] = {-6, -5, -3, 0, 2, 4, 9, 5};
        findPair(arr);

    }

    public static void findPair(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }

        Arrays.sort(arr);

        int low = 0;
        int high = arr.length - 1;

        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        while (low < high) {
            if (Math.abs(arr[high] + arr[low]) < min) {
                min = Math.abs(arr[high] + arr[low]);
                i = low;
                j = high;
            }

            if (min == 0) {
                break;
            }

            if (arr[high] + arr[low] < 0) {
                low++;
            } else {
                high--;
            }
        }

        System.out.print("Pair found (" + arr[i] + ", " + arr[j] + ")");
    }
}
