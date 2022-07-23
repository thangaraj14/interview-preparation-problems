package practiceproblems;

/**
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
 */
public class MinOperationToMakeArrayIncreasing {

    public int minOperations(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;
        int total = 0;

        /**
         If at any point, nums[i] <= nums[i - 1],
         then we need to increment nums[i] to make the array strictly increasing.
         The number of increments needed is given by - nums[i - 1] + nums[i] + 1.
         Basically, it is the number of increments needed to take nums[i] to atleast nums[i - 1] + 1.
         **/
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) continue;
            total += Math.abs(nums[i] - nums[i - 1]) + 1;
            nums[i] += Math.abs(nums[i] - nums[i - 1]) + 1;
        }

        return total;
    }
}
