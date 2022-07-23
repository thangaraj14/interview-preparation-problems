package practiceproblems;

//https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

/**
 * tricky array traversal
 *
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 * <p>
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 */
public class MaximumUnsortedSubarray {

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }


        int min = Integer.MAX_VALUE;
        int begin = -1;
        //iterate from end of array
        //find the last element which is bigger than the last seen min from
        //its right side and mark it as begin
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                begin = i;
            }
        }
        if (begin == -1) return 0;
        int max = Integer.MIN_VALUE;
        int end = -2;
        //iterate from beginning of array
        //find the last element which is smaller than the last seen max from
        //its left side and mark it as end
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                end = i;
            }
        }
        return end - begin + 1;
    }

    public static void main(final String[] args) {
        //1, 1, 10, 10, 15, 10, 15, 10,10, 15, 10, 15
        //1, 3, 2, 4, 5
        //4, 15, 4, 4, 15, 18, 20
        //2, 6, 1, 8, 10, 9, 15
        findUnsortedSubarray(new int[]{4, 15, 4, 4, 15, 18, 20});

    }
}
