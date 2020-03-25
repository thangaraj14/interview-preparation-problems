package geeksforgeeks;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/
 */
class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();
        int[] arr = { 0, 1, 0, 3, 12 };
        mz.moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}