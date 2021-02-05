package geeksforgeeks;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = { 1, 2, 2 };
        removeDuplicates.removeDuplicates(nums);
    }
}