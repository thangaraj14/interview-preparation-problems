package practiceproblems;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int incl = nums[0]; // max money can get if rob current house
        int excl = 0; // max money can get if not rob current house
        for (int i = 1; i < nums.length; i++) {
            int temp = incl;
            incl = Math.max(incl, excl + nums[i]);
            excl = temp;
        }
        return incl;
    }

    public int robCircular(int[] nums) {

        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(helperFn(nums, 0, nums.length - 2), helperFn(nums, 1, nums.length - 1));

    }

    public int helperFn(int[] nums, int start, int end) {
        int pre = 0;
        int cur = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = temp;

        }
        return cur;
    }

    Integer[] cache;

    public int robBottomUp(int[] nums) {
        cache = new Integer[nums.length];
        return recursionHelper(nums, 0);
    }

    public int recursionHelper(int[] nums, int index) {
        if (index >= nums.length) return 0;
        if (cache[index] != null) return cache[index];
        int inclusive = recursionHelper(nums, index + 2) + nums[index];
        int exclusive = recursionHelper(nums, index + 1);

        return cache[index] = Math.max(inclusive, exclusive);
    }

    public int robExtraSpace(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 9, 3, 1};
        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.rob(arr));
    }
}
