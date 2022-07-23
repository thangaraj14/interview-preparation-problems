package practiceproblems;


/**
 * https://leetcode.com/problems/counting-elements/
 */
public class CountElements {

    public static int countElements(int[] arr) {
        int[] count = new int[1002];
        for(int num : arr) {
            count[num]++;
        }

        int res = 0;
        for(int num : arr) {
            if (count[num + 1] > 0) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countElements(new int[]{1, 3, 2, 3, 5, 0}));
    }
}