package practiceproblems;

public class MinimumSubArrayLength {

    /**
     * tricky int sliding window
     */
    public static int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while (left < nums.length) {
            sum += nums[left];
            while (sum >= target) {
                result = Math.min(result, left - right + 1);
                sum -= nums[right];
                right++;
            }

            left++;
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
    }
}
