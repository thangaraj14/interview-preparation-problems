package dsa;

import java.util.HashMap;

/**
 * https://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/
 */
class LongestSpanWithSameSumArray {

    static int longestCommonSum(int[] arr1, int[] arr2, int n) {

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = arr1[i] - arr2[i];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum == 0) {
                maxLength = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }

    public static void main(String args[]) {
        int arr1[] = { 0, 1, 0, 0, 0, 0 };
        int arr2[] = { 1, 0, 1, 0, 0, 1 };
        //{-1,1,-1,0,0,-1}
        int n = arr1.length;
        System.out.println(longestCommonSum(arr1, arr2, n));
    }
}