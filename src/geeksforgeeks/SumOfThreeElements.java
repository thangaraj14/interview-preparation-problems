package geeksforgeeks;

import java.util.Arrays;

/**
 *
 */

public class SumOfThreeElements {

    public static void main(String[] args) {
        int arr[] = { 1, 4, 45, 6, 10, 8 };
        int sum = 22;
        SumOfThreeElements ste = new SumOfThreeElements();
        ste.findTriplets(arr, sum);
    }

    private boolean findTriplets(int[] arr, int sum) {

        int arrSize = arr.length;
        Arrays.sort(arr);

        for (int i = 0; i < arrSize; i++) {

            int left = i + 1;
            int right = arrSize - 1;

            while (left < right) {
                int result = arr[i] + arr[left] + arr[right];
                if (result == sum) {
                    System.out.println("triplet found" + i + "+" + left + "+" + right);
                    break;
                } else if (result < sum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return false;
    }
}
