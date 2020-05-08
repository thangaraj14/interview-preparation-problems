package geeksforgeeks;

/**
 * https://leetcode.com/problems/non-decreasing-array/description/
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
<<<<<<< HEAD
        // 3,4,2,3
=======
        //        //3,4,2,3
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
        int[] nums = { 7, 8, 2, 3 };
        NonDecreasingArray nda = new NonDecreasingArray();
        System.out.println(nda.checkPossibility(nums));
    }
}
