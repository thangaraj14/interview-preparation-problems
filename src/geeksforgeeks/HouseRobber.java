package geeksforgeeks;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    public int robI(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int incl = nums[0];
        int excl = 0;
        for (int i = 1; i < nums.length; i++) {
            int temp = incl;
            incl = Math.max(incl, excl + nums[i]);
            excl = temp;
        }
        return incl;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 7, 9, 3, 1 };
        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.robI(arr));
    }

    public int robII(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(helperFn(nums, 0, nums.length - 2), helperFn(nums, 1, nums.length - 1));

    }

    public int helperFn(int[] nums, int start, int end) {
        int incl = 0;
        int excl = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(excl + nums[i], incl);
            excl = incl;
            incl = temp;

        }
        return incl;
    }
}
