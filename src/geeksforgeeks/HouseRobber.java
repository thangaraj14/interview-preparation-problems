package geeksforgeeks;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    public int rob(int[] nums) {
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
        System.out.println(houseRobber.rob(arr));
    }
}
