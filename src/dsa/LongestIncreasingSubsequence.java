package dsa;

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
    // 4, 10, 4, 3, 8, 9 :
    public static void main(String[] args) {
        int[] nums = { 10,9,2,5,3,7,101,18 };
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println(lis.lengthOfLIS(nums));
    }

    public int findPositionToReplace(int[] a, int low, int high, int x) {
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (a[mid] == x) {
                return mid;
            } else if (a[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // 10,9,2,5,3,7,101,18
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int len = 0;
        int[] increasingSequence = new int[n];
        increasingSequence[len] = nums[0];
        len++;
        for (int i = 1; i < n; i++) {
            if (nums[i] > increasingSequence[len - 1]) {
                increasingSequence[len++] = nums[i];
            } else {
                int position = findPositionToReplace(increasingSequence, 0, len - 1, nums[i]);
                increasingSequence[position] = nums[i];
            }
        }
        return len;
    }
}
