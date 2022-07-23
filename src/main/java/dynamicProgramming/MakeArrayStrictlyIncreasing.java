package dynamicProgramming;

/**
 * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing
 */
public class MakeArrayStrictlyIncreasing {
    public boolean canBeIncreasing(int[] nums) {
        int numberOfDeletionsNeededToMakeStrictlyIncreasing = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i]) {

                int iMinusTwoValue = i - 2 < 0 ? Integer.MIN_VALUE : nums[i - 2];
                int iPlusOneValue = i + 1 == nums.length ? Integer.MAX_VALUE : nums[i + 1];

                //[1,2,10,5,7]
                //[2,3,1,2] -- false
                //[1,1,1] -- false
                //[100,21,100]
                //[1,2,3]
                //[105,924,32,968]
                if ((nums[i] > iMinusTwoValue && nums[i] < iPlusOneValue) ||
                        (nums[i - 1] > iMinusTwoValue && nums[i - 1] < iPlusOneValue)) {
                    ++numberOfDeletionsNeededToMakeStrictlyIncreasing;
                } else {
                    return false; // found an unfixable non-increase
                }

            }
        }
        return numberOfDeletionsNeededToMakeStrictlyIncreasing <= 1;
    }
}
