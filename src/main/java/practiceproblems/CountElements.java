package practiceproblems;


/**
 * https://leetcode.com/problems/counting-elements/
 * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
 * If there are duplicates in arr, count them separately.
 * Input: arr = [1,3,2,3,5,0]
 * Output: 3
 * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
 */
public class CountElements {

    public static int countElements(int[] arr) {
        int[] freq = new int[1002];
        for(int num : arr) {
            freq[num]++;
        }

        int res = 0;
        for(int num : arr) {
            if (freq[num + 1] > 0) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countElements(new int[]{1, 3, 2, 3, 5, 0}));
    }
}