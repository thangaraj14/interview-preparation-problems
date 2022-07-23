package dynamicProgramming.lcs;

import java.util.Arrays;

/**
 * Solve the LIS subproblem for each snippet of the array ending between 1, 2,
 * 3, ... and so on until nums.length - 1 (inclusive)
 * <p>
 * Ex:
 * <p>
 * [-2, 1, 2, 3]
 * <p>
 * [-2] from index 0 to index 0 [-2, 1] from index 0 to index 1 [-2, 1, 2] from
 * index 0 to index 2 [-2, 1, 2, 3] from index 0 to index 3
 * <p>
 * Our answer is the maximum LNDS found between all subproblems we solve along
 * the way.
 * <p>
 * Time complexity is O(n^2).
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        lisLength(nums);
    }

    private static void lisLength(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, 1);
        int maximumSoFar = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }
            maximumSoFar = Math.max(maximumSoFar, result[i]);
        }
        System.out.println(Arrays.toString(result));
        System.out.println(maximumSoFar);
    }

    public int lengthOfLISBottomUp(int[] nums) {
        //there were two chaning variable in recursive solution curr and prev
        //so we need a 2d matrix
        //length decision:-prev will go max upto nums.length so will take nums.length+1
        // curr will go max upto nums.length-1 so will take nums.length
        //for initialization of dp matrix initialize it with -1

        int[][] dp = new int[nums.length + 1][nums.length];
        for (int[] x : dp) {
            Arrays.fill(x, -1);
        }
        return solve(nums, -1, 0, dp);
    }

    public int solve(int[] nums, int prevIndex, int curr, int[][] dp) {
        if (curr == nums.length) {
            return 0;
        }

        if (dp[prevIndex + 1][curr] != -1) {
            return dp[prevIndex + 1][curr];
        }

        if (prevIndex < 0 || nums[curr] > nums[prevIndex]) {
            dp[prevIndex + 1][curr] = Math.max(1 + solve(nums, curr, curr + 1, dp),
                    solve(nums, prevIndex, curr + 1, dp));
        } else {
            dp[prevIndex + 1][curr] = solve(nums, prevIndex, curr + 1, dp);
        }
        return dp[prevIndex + 1][curr];
    }

    /**
     * https://www.youtube.com/watch?v=qW1O1a40-No&ab_channel=AryanMittal
     * <p>
     * Idea is to prepare a result array, the result array will be sorted obviously because, we are looking for
     * LIS.
     * case i) if current element arr[i] is greater that result[result.length-1] append to result
     * case ii) else we need to look for a suitable position in the result arr for that element arr[i]
     * which has arr[i-1]>=arr[i]=<arr[i+1] this could be useful in getting a bigger LIS length
     * remember replacing will not alter the already available length of result
     * <p>
     * arr[]= {2,5,3,7,11,8,10,13,6}
     * result[]={}
     * when i=2(arr[i]=3)
     * result=[2,5]
     * we need to see a position for 3 to accommodate, do a binary search in [2,5], we'll get index 1
     * so we modify the result = [2,3] *Note the length is not changed
     * <p>
     * when i=6 (arr[i]=8)
     * result=[2,3,7,11] we need to check for a position, do a binary search in [2,3,6,7], we'll get index 3
     * result=[2,3,7,8]
     * <p>
     * when i=8(arr[i]=6)
     * result=[2,3,7,8,10,13] or [2,5,7,8,10,13]   do a binary search , we'll get index 2
     * result=[2,3,6,8,10,13] at this point we are not bothered about the correctness of result
     * all we need is the length
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length, len = 0;
        int[] increasingSequence = new int[n];
        increasingSequence[len++] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > increasingSequence[len - 1])
                increasingSequence[len++] = nums[i];
            else {
                int position = findPositionToReplace(increasingSequence, 0, len - 1, nums[i]);
                increasingSequence[position] = nums[i];
            }
        }
        return len;
    }

    public static int findPositionToReplace(int[] a, int low, int high, int x) {
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (a[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
