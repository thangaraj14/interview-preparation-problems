package dp;

/**
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 * <p>
 * https://leetcode.com/problems/jump-game-ii/
 * https://leetcode.com/problems/jump-game/
 */
public class JumpProblems {

    public int minJump(int[] arr, int[] result) {

        int[] jump = new int[arr.length];
        jump[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            jump[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] + j >= i) {
                    if (jump[i] > jump[j] + 1) {
                        result[i] = j;
                        jump[i] = jump[j] + 1;
                    }
                }
            }
        }
        return jump[jump.length - 1];
    }

    public int jump(int[] arr) {
        int jumps = 0;
        int curEnd = 0;
        int maxLocation = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            maxLocation = Math.max(maxLocation, i + arr[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = maxLocation;
            }
        }
        return jumps;
    }

    public boolean canJump(int[] nums) {
        int maxLocation = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxLocation < i) {
                // if previous maxLocation smaller than i, meaning we cannot reach location i, thus return false.
                return false;
            }
            maxLocation = Math.max(i + nums[i], maxLocation);
        }
        return true;
    }

    public static void main(String[] args) {
        JumpProblems mj = new JumpProblems();
        //        int arr[] = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
        //        int r[] = new int[arr.length];
        //        int result = mj.minJump(arr, r);
        int[] arr = new int[] { 2, 3, 1, 1, 4 };

        int[] nums = { 3, 2, 1, 0, 4 };
        boolean result = mj.canJump(nums);
        System.out.println(result);
        System.out.println(mj.jump(arr));
    }
}