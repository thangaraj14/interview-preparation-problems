package practiceproblems;

import java.util.HashMap;

/**
 * Given an integer array arr, count element x such that x + 1 is also in arr.
 * <p>
 * If there're duplicates in arr, count them separetely.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3]
 * Output: 2
 * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
 * Example 2:
 * <p>
 * Input: arr = [1,1,3,3,5,5,7,7]
 * Output: 0
 * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
 * Example 3:
 * <p>
 * Input: arr = [1,3,2,3,5,0]
 * Output: 3
 * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
 * Example 4:
 * <p>
 * Input: arr = [1,1,2,2]
 * Output: 2
 * Explanation: Two 1s are counted cause 2 is in arr.
 */
public class CountElements {

    public static int countElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int in : arr) {
            freqMap.put(in, freqMap.getOrDefault(in, 0) + 1);
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (freqMap.containsKey(arr[i] - 1)) {
                count = count + freqMap.get(arr[i] - 1);
                freqMap.put(arr[i] - 1, 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countElements(new int[]{1, 3, 2, 3, 5, 0}));
    }
}