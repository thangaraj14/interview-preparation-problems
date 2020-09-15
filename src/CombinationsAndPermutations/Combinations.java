package CombinationsAndPermutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates==null || candidates.length==0) return Collections.emptyList();
        List<List<Integer>> result= new ArrayList<>();

        combinationSumUtil(candidates, target, result, new ArrayList<>(), 0);

        return result;

    }

    public void combinationSumUtil(int[] candidates, int target, List<List<Integer>> result, List<Integer> tempList, int start ){
        if(target<0) return;
        if(target==0){
            result.add(new ArrayList(tempList));

        }

        for(int i=start; i<candidates.length; i++){
            if (target - candidates[i] < 0) break;
            tempList.add(candidates[i]);
           // The same repeated number may be chosen from candidates unlimited number of times. that's the reason of using i not i+1
            combinationSumUtil(candidates, target-candidates[i], result, tempList, i);

            tempList.remove(tempList.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates==null || candidates.length==0) return Collections.emptyList();
        List<List<Integer>> result= new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumUtil1(candidates, target, result, new ArrayList<>(), 0);

        return result;
    }

    public void combinationSumUtil1(int[] candidates, int target,  List<List<Integer>> result, List<Integer> tempList, int start){
        if(target<0) return;

        if(target==0){
            result.add(new ArrayList<>(tempList));
            return;
        }

        for(int i=start; i<candidates.length; i++){
//            when we should skip a number? not just it's the same as previous number, but also when it's previous number haven't been added!
//
//            i > cur means cand[i - 1] is not added to the path (you should know why if you understand the algorithm), so if cand[i] == cand[i-1], then we shouldn't add cand[i].
//
//            This tricky is very smart.
            if(i>start && candidates[i]==candidates[i-1]) continue;
            if (target - candidates[i] < 0) break;
            tempList.add(candidates[i]);
            combinationSumUtil1(candidates, target-candidates[i], result, tempList, i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}
