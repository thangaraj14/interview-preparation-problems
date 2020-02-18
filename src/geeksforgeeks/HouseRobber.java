package geeksforgeeks;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author i312458
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
}
