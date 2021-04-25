package dp.lcs;

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
        int[] nums = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        lis.lengthOfLIS(nums);
      /*  if (nums.length == 0) {
            return;
        }

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
        System.out.println(maximumSoFar);*/
    }

    public static int findPositionToReplace(int[] a, int low, int high, int x) {
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

    public static int lengthOfLIS(int[] nums) {
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
