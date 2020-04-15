package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
class Combination {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), 0, result);
        return result;
    }

    private void dfs(int[] nums, List<Integer> curr, int index, List<List<Integer>> result) {
        result.add(new ArrayList<>(curr));
        if (index == nums.length) {
            return;
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (visited.add(nums[i])) {
                curr.add(nums[i]);
                dfs(nums, curr, i + 1, result);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Combination subSet = new Combination();
        subSet.subsets(new int[] { 1, 2, 3 });
        List<List<Integer>> lists = subSet.subsetsWithDup(new int[] { 1, 1, 2, 4 });
        lists.stream().forEach(System.out::print);

    }
}