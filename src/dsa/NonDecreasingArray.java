package dsa;

/**
 * https://leetcode.com/problems/non-decreasing-array/description/
 * https://www.youtube.com/watch?v=Dxv_kCAYOk4&ab_channel=NideeshTerapalli
 */
class NonDecreasingArray {

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
        int[] nums = { 3, 4, 2, 3 };
        NonDecreasingArray nda = new NonDecreasingArray();
        System.out.println(nda.checkPossibility(nums));
    }
}
