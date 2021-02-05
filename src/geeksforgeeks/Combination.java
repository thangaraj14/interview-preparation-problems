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

    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), arr, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currList, int[] arr, int start) {
        result.add(new ArrayList<>(currList));
        for (int i = start; i < arr.length; i++) {
            currList.add(arr[i]);
            backtrack(result, currList, arr, i + 1);
            currList.remove(currList.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        dfs(arr, new ArrayList<>(), 0, result);
        return result;
    }

    private void dfs(int[] arr, List<Integer> currList, int index, List<List<Integer>> result) {
        result.add(new ArrayList<>(currList));
        if (index == arr.length) {
            return;
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (visited.add(arr[i])) {
                currList.add(arr[i]);
                dfs(arr, currList, i + 1, result);
                currList.remove(currList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Combination subSet = new Combination();
/*        List<List<Integer>> subsets = subSet.subsets(new int[] { 1, 2, 3 });
        subsets.forEach(System.out::println);*/
        List<List<Integer>> lists = subSet.subsetsWithDup(new int[] { 1, 1, 2, 4 });
        lists.forEach(System.out::print);
    }
}