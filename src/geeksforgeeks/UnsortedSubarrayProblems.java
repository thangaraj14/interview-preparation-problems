package geeksforgeeks;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/submissions/
 */
public class UnsortedSubarrayProblems {

    public static int findUnsortedSubarray(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        //left -> right and break when its next element is min
        while (left < right) {
            if (nums[left] > nums[left + 1]) {
                break;
            }
            left++;
        }
        if (left >= right) {
            return 0;
        }
        //right-> left and break when its previous element is max
        while (right > 0) {
            if (nums[right] < nums[right - 1]) {
                break;
            }
            right--;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int k = left; k <= right; k++) {
            min = Math.min(min, nums[k]);
            max = Math.max(max, nums[k]);
        }

        while (left >= 0 && min < nums[left])
            left--;
        while (right < nums.length && max > nums[right])
            right++;

        return right - left - 1;

    }

    public static void main(String[] args) {
        //(1, 3, 2, 4, 5)));
        //4, 15, 4, 4, 15, 18, 20

        // 2,6,4,8,10,9,15
        int[] arr = { 2, 6, 4, 8, 10, 9, 15 };
        System.out.println(findUnsortedSubarray(arr));
    }
}
