package dsa;

/**
 * @author Tushar Roy
 * <p>
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * https://leetcode.com/problems/house-robber/
 */
public class MaxSumForNonAdjacentElements {

    public int maxSum(int[] arr) {
        int excl = 0;
        int incl = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int oldResult = incl;
            incl = Math.max(excl + arr[i], incl);
            excl = oldResult;
        }
        return incl;
    }

    public static void main(String[] args) {
        MaxSumForNonAdjacentElements msn = new MaxSumForNonAdjacentElements();
        int[] arr = { 4, 1, 1, 4, 2, 1 };
        System.out.println(msn.maxSum(arr));

    }
}