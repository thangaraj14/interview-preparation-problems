package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * https://ibb.co/k4zv00
 */
public class Permutation {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        constructPermutation(result, new ArrayList<>(), nums);
        return result;
    }

    private void constructPermutation(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                constructPermutation(result, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> list, int[] arr, boolean[] used) {
        if (list.size() == arr.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (used[i] || i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            list.add(arr[i]);
            backtrack(result, list, arr, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        List<List<Integer>> lists = permutation.permuteUnique(new int[] { 1, 1, 2 });
        lists.forEach(System.out::print);
        System.out.println();
        lists = permutation.permuteUnique(new int[] { 1, 2, 3 });
        lists.forEach(System.out::print);
    }
}
