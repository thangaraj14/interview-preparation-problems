package dynamicProgramming.lcs;

import java.util.Arrays;

public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        // if we sort the array, we do not need to delete elements smaller than nums[idx] (ie nums[idx] - 1) because they are already computed
        // and saved in memo, we only need to delete nums[idx] + 1 and we can do this simply by skipping them since the array is sorted
        Arrays.sort(nums);
        Integer[] dp = new Integer[nums.length];
        return recursionUtil(nums, 0, dp);
    }

    private int recursionUtil(int[] nums, int idx, Integer[] dp) {
        // if we reached the end of the array, we can not earn anymore, return 0
        if (idx == nums.length)
            return 0;

        if (dp[idx] != null) return dp[idx];
        // delete and earn this element
        int earned = nums[idx];
        int skip = idx + 1;

        // simply add all similar values of nums[idx] to earned at once
        while (skip < nums.length && nums[skip] == nums[idx]) {
            earned += nums[idx];
            skip++;
        }

        // skip all elements = nums[idx] + 1
        // this is instead of deleting the elements = nums[idx] + 1
        // which does not alter the array and make the solution work
        while (skip < nums.length && nums[skip] == nums[idx] + 1)
            skip++;

        // recurse
        earned += recursionUtil(nums, skip, dp);

        // skip deleting and earning this element
        int skipped = recursionUtil(nums, idx + 1, dp);

        // return the max of the 2 values
        dp[idx] = Math.max(earned, skipped);


        return dp[idx];
    }

    /**
     * for numbers from [1 - 10000], each has a total sum sums[i]; if you earn sums[i], you cannot earn sums[i-1] and sums[i+1]
     * kind of like house robbing. you cannot rob 2 connected houses.
     */

    public int deleteAndEarnBottomUp(int[] nums) {
        int n = 10001;
        int[] values = new int[n];
        for (int num : nums)
            values[num] += num;

        int take = 0, skip = 0;
        for (final int value : values) {

            final int temp = Math.max(skip + value, take);
            skip = take;
            take = temp;
        }
        return take;
    }
}
