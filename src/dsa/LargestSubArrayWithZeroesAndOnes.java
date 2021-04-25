package dsa;

import java.util.HashMap;

/**
 * https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
 */
class LargestSubArrayWithZeroesAndOnes {

    int maxLen(int[] arr, int n) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;
        int endingIndex = -1;

        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == 0) ? -1 : 1;
        }

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                maxLength = i + 1;
                endingIndex = i;
            }

            if (map.containsKey(sum)) {
                if (maxLength < i - map.get(sum)) {
                    maxLength = i - map.get(sum);
                    endingIndex = i;
                }
            } else {
                map.put(sum, i);
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == -1) ? 0 : 1;
        }

        int start = endingIndex - maxLength + 1;
        System.out.println(start + " to " + endingIndex);

        return maxLength;
    }

    public static void main(String[] args) {
        LargestSubArrayWithZeroesAndOnes sub = new LargestSubArrayWithZeroesAndOnes();
        //        int[] arr = { 1, 0, 1, 1, 1, 0, 0 };
        int[] arr = { 0, 0, 1, 1, 0 };
        int n = arr.length;

        System.out.println(sub.maxLen(arr, n));
    }
}
