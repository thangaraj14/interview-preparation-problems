package CombinationsAndPermutations;

import java.util.*;

public class SubSets {

    public List<List<Integer>> subsets(int[] nums) {
        if(nums==null || nums.length==0) return Collections.emptyList();

        List<List<Integer>> result= new ArrayList<>();
        subSetGenerationUtil(nums, result, new ArrayList<>(), 0);
        return result;
    }

    public void  subSetGenerationUtil(int[] nums, List<List<Integer>> result,  List<Integer> tempList, int start){

        result.add(new ArrayList<>(tempList));

        for(int i=start;i<nums.length;i++){
            tempList.add(nums[i]);
            subSetGenerationUtil(nums, result, tempList, i+1);
            tempList.remove(tempList.size()-1);
        }

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums==null || nums.length==0) return Collections.emptyList();

        List<List<Integer>> result= new ArrayList<>();
        Arrays.sort(nums);
        //subsetsWithDupUtil(nums, result, new ArrayList<>(), 0);
        dfs(nums,0,result, new ArrayList<>());
        return result;
    }

    public void  subsetsWithDupUtil(int[] nums, List<List<Integer>> result, List<Integer> tempList, int start){
        result.add(new ArrayList<>(tempList));

        for(int i=start; i<nums.length; i++){
            if(i>start && nums[i]==nums[i-1]) continue;
            tempList.add(nums[i]);
            subsetsWithDupUtil(nums, result, tempList, i+1);
            tempList.remove(tempList.size()-1);
        }
    }

    private void dfs(int[] nums, int index, List<List<Integer>> res, List<Integer> curr){
        res.add(new ArrayList<>(curr));
        if(index == nums.length){
            return;
        }
        Set<Integer> visited = new HashSet<Integer>();
        for(int i = index; i < nums.length; i++){
            if(visited.add(nums[i])){
                curr.add(nums[i]);
                dfs(nums, i + 1, res, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        new SubSets().subsetsWithDup(new int[]{1,1,2,4});
    }
}

