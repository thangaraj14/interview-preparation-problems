package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/*https://leetcode.com/problems/majority-element/*/
public class MajorityVoting {

    public static List<Integer> majorityElementII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int candidate1 = nums[0];
        int candidate2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1) {
                count1++;
            } else if (nums[i] == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1) {
                count1++;
            } else if (nums[i] == candidate2) {
                count2++;
            }
        }
        if (count1 > len / 3) {
            result.add(candidate1);
        }
        if (count2 > len / 3) {
            result.add(candidate2);
        }
        return result;
    }

    public static int majorityElementI(int[] nums) {
        int count = 1;
        int candidate = nums[0];
        int majority = nums.length / 2;

        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i]) {
                count++;
            } else if (count == 0) {
                count++;
                candidate = nums[i];
            } else {
                count--;
            }
        }
        //what if array is even and it has many elements
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return count > majority ? candidate : 0;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 1, 1, 2, 2, 4, 4, 4, 4, 4, 2, 2 };
        System.out.println(majorityElementI(arr));
        System.out.println(majorityElementII(arr));

    }

}
