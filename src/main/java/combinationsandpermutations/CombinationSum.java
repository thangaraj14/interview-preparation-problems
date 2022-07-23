package combinationsandpermutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();

        combinationSumUtil(candidates, target, result, new ArrayList<>(), 0);

        return result;

    }

    public void combinationSumUtil(int[] candidates, int target, List<List<Integer>> result, List<Integer> tempList, int start) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<>(tempList));

        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;
            tempList.add(candidates[i]);
            // The same repeated number may be chosen from candidates unlimited number of times.
            // that's the reason of using i not i+1
            combinationSumUtil(candidates, target - candidates[i], result, tempList, i);

            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumUtil2(candidates, target, result, new ArrayList<>(), 0);

        return result;
    }

    public void combinationSumUtil2(int[] candidates, int target, List<List<Integer>> result, List<Integer> tempList, int start) {
        if (target < 0) return;

        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
//            when we should skip a number? not just it's the same as previous number,
//            but also when it's previous number haven't been added!
//            i > cur means cand[i - 1] is not added
//            to the path (you should know why if you understand the algorithm),
//            so if cand[i] == cand[i-1], then we shouldn't add cand[i].
//            This tricky is very smart.

            /**
             * i>start && candidates[i]==candidates[i-1] :
             *  when input is like 1,1,2,4,7,8 and target is 9
             *  the first iteration would run for all combinations starts with 1 ([1,1,7], [1,8])
             *  the next number i=1 also starts with one which will run again for all combinations
             *  candidates[i]==candidates[i-1] this will eliminate the duplicate combinations(another [1,8])
             */
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            if (target - candidates[i] < 0) break;
            tempList.add(candidates[i]);
            combinationSumUtil2(candidates, target - candidates[i], result, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public int combinationSum4BottomUp(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        // the difference with coin change is in coin change we see unique combination
        // so outer loop is coins i.e we start with coins, but here we care about
        // the number of permutations, so we start from target
        // if you treat the nums as coins then we have unlimited number of coins
        // that's the reason for the change in order
        // For e.g. the classic coin change will exclude {2,1,1} after considering {1,1,2} as a solution.
        /*
            Coin change-2
            for (int coin : coins) {
                for (int i = 1; i <= amount; i++) {
                    if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
         */
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) dp[i] += dp[i - num];
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[target];
    }

    Integer[] cache;

    public int combinationSum4(int[] nums, int target) {
        return recursionHelper(nums, target, 0, new Integer[target + 1]);
    }

    private int recursionHelper(int[] nums, int target, int pos, Integer[] cache) {
        if (pos == nums.length || target <= 0) {
            return (target == 0) ? 1 : 0;
        }

        if (cache[target] != null) {
            return cache[target];
        }

        int take = recursionHelper(nums, target - nums[pos], 0, cache);
        int skip = recursionHelper(nums, target, pos + 1, cache);

        return cache[target] = take + skip;
    }
}
