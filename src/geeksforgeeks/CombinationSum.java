package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] arr, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);
        backtrack(list, new ArrayList<>(), arr, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> currList, int[] arr, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            list.add(new ArrayList<>(currList));
        } else {
            for (int i = start; i < arr.length; i++) {
                currList.add(arr[i]);
                backtrack(list, currList, arr, remain - arr[i], i);
                currList.remove(currList.size() - 1);
            }
        }
    }

    public static List<List<Integer>> combinationSum2(int[] arr, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);
        backtrackII(list, new ArrayList<>(), arr, target, 0);
        return list;

    }

    private static void backtrackII(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain,
            int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue; // skip duplicates , after executing 1st 1's combination
                    // next 1 will also execute the same combination like 1,2,5
                }
                tempList.add(nums[i]);
                backtrackII(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[] { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        List<List<Integer>> lists = combinationSum2(candidates, target);
        lists.forEach(System.out::println);
    }
}
