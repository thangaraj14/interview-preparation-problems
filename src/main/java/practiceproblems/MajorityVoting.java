package practiceproblems;

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
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
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
        int count = 0;
        int majorElem = 0;

        for (int i : nums) {
            if (count == 0) {
                majorElem = i;
            }
            if (i == majorElem) {
                count++;
            } else {
                count--;
            }

        }

        return majorElem;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1, 2, 2, 4, 4, 4, 4, 4, 2, 2};
        System.out.println(majorityElementI(arr));
        System.out.println(majorityElementII(arr));

    }

}
