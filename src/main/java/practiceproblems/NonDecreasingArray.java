package practiceproblems;

/**
 * https://leetcode.com/problems/non-decreasing-array/description/
 *
 * tricky
 */
class NonDecreasingArray {
    /**
     * Whenever we encounter a violation at a particular index i, we need to check what modification we can make to make the array sorted
     * [3,4,5,3,6,8]
     * In the example above, we consider the numbers 4, 5, 3 for deciding on how to fix the violation or.
     * In this case, the correct modification is to change the number 3 to 5.
     * If we change 5 to 3, then we won't be fixing the violation because the resulting array would be 3, 4, 3, 3, 6, 8.
     * The basic decision-making process for fixing a violation is listed below.
     * Without considering the number at the index i - 2, we won't be able to choose between updating nums[i] or nums[i - 1]
     *
     * in the example above nums[i-2] <= nums[i] so there's no point in changing nums[i-1] to nums[i] (i.e 5 to 3)
     * so we change the nums[i] to nums[i-1] (i.e 3 to 5)
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {

        int count = 0;
        for (int i = 1; i < nums.length && count <= 1; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;
                if ((i - 2 < 0) || nums[i - 2] <= nums[i]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return count <= 1;
    }

    public static void main(String[] args) {
        // 1,4,2,3
        //        //3,4,2,3
        int[] nums = {7, 8, 2, 3};
        NonDecreasingArray nda = new NonDecreasingArray();
        System.out.println(nda.checkPossibility(nums));
    }
}
