package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {

                int left = i + 1;
                int right = nums.length - 1;
                int sum = -nums[i];
                while (left < right) {
                    if (nums[left] + nums[right] == sum) {

                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (nums[left] + nums[right] < sum) {
                        left++;
                    } else if (nums[left] + nums[right] > sum) {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int initialSum = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum > target) {
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else {
                    j++;
                    while (j < k && nums[k] == nums[j - 1]) j++;
                }

                if (Math.abs(target - sum) < Math.abs(target - initialSum)) {
                    initialSum = sum;
                }
            }
        }

        return initialSum;
    }
}