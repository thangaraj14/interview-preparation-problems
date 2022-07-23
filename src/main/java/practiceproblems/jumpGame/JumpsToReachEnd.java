package practiceproblems.jumpGame;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class JumpsToReachEnd {

    /**
     * Given an array of non-negative integers nums,
     * you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * <p>
     * Determine if you are able to reach the last index
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * <p>
     * Input: nums = [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what.
     * Its maximum jump length is 0, which makes it impossible to reach the last index.
     */
    public static boolean canReachEnd(Integer[] nums) {

        int curMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (curMax < i) return false; //means we are not able to reach position i
            curMax = Math.max(curMax, i + nums[i]);
        }
        return true;
    }


    /**
     * Given an array of non-negative integers arr,
     * you are initially positioned at start index of the array.
     * When you are at index i, you can jump to i + arr[i] or i - arr[i],
     * check if you can reach to any index with value 0.
     * Notice that you can not jump outside of the array at any time.
     * <p>
     * Input: arr = [4,2,3,0,3,1,2], start = 5
     * Output: true
     * Explanation:
     * All possible ways to reach at index 3 with value 0 are:
     * index 5 -> index 4 -> index 1 -> index 3
     * index 5 -> index 6 -> index 4 -> index 1 -> index 3
     * <p>
     * Input: arr = [4,2,3,0,3,1,2], start = 0
     * Output: true
     * Explanation:
     * One possible way to reach at index 3 with value 0 is:
     * index 0 -> index 4 -> index 1 -> index 3
     */
    public boolean canReach(int[] arr, int start) {

        if (arr[start] == 0) return true;

        Deque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{start, arr[start]});
        boolean[] visited = new boolean[arr.length];
        visited[start] = true;
        while (!queue.isEmpty()) {

            int[] temp = queue.poll();

            int jumpIndexFwd = temp[0] + temp[1];
            int jumpIndexBack = temp[0] - temp[1];

            if (jumpIndexFwd >= 0 && jumpIndexFwd < arr.length && !visited[jumpIndexFwd]) {
                if (arr[jumpIndexFwd] == 0) return true;
                visited[jumpIndexFwd] = true;
                queue.offer(new int[]{jumpIndexFwd, arr[jumpIndexFwd]});
            }

            if (jumpIndexBack >= 0 && jumpIndexBack < arr.length && !visited[jumpIndexBack]) {
                if (arr[jumpIndexBack] == 0) return true;
                visited[jumpIndexBack] = true;
                queue.offer(new int[]{jumpIndexBack, arr[jumpIndexBack]});
            }

        }

        return false;
    }


    /**
     * This is a BFS solution
     *
     * @param nums
     * @return
     */
    public int minJump(int[] nums) {
        int result = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) { // loop till last jump hasn't taken us till the end

            //maxReachable is the maximum reachable index from index i.
            // Consider this example 2, 3, 10, 1 in this case, maxReachable will be 12 at index 2 which says we can reach till 12th index but not the number of jumps.
            // So, if you update it with number of jumps you get 12 where as answer is 2.

            curFarthest = Math.max(curFarthest, i + nums[i]);  //Updating the range of next level.
            // Similar to queue.push(node) step of BFS but here we are only greedily storing the max reachable index on next level.

            if (i == curEnd) { //When it becomes true, current level iteration has been completed.
                result++;
                curEnd = curFarthest; //Move on to the next level.

                if (curEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(3, 3, 1, 0, 2, 0, 1);
        System.out.println(canReachEnd(list.toArray(new Integer[list.size()])));
    }
}