package practiceproblems;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array
 */
public class SortedSquares {
    // Input: [-7,-3,2,3,11]
    // Output: [4,9,9,49,121]
    public int[] sortedSquares(int[] nums) {

        int[] result = new int[nums.length];

        int i = 0;
        int j = nums.length - 1;
        int k = nums.length - 1;
        while (i <= j) {

            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                result[k--] = nums[j] * nums[j];
                j--;
            } else if (nums[i] * nums[i] > nums[j] * nums[j]) {
                result[k--] = nums[i] * nums[i];
                i++;
            } else {
                result[k--] = nums[i] * nums[i];
                if (k >= 0) result[k--] = nums[i] * nums[i];
                i++;
                j--;
            }
        }

        return result;
    }
}