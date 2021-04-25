package dsa;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class UnsortedSubarray {

    // use case to check : [1,3,2,2,2]
    public static int findUnsortedSubarrayNew(int[] nums) {
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

    public static void main(String[] args) {
        //(1, 3, 2, 4, 5)));
        //4, 15, 4, 4, 15, 18, 20

        // 2,6,4,8,10,9,15
        int[] arr = { 2, 6, 4, 8, 10, 9, 15 };
        System.out.println(findUnsortedSubarrayNew(arr));
    }
}
