package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums==null || nums.length==0) return Collections.emptyList();
        
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
           if (i == 0 || (nums[i] != nums[i-1])){
               
           
            int left=i+1;
            int right=nums.length-1;
            int sum= 0 - nums[i];
            while(left<right){
                if(nums[left]+nums[right]==sum){
                    
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                    while(left<right && nums[left]==nums[left-1]) left++;
                    while(left<right && nums[right]==nums[right+1]) right--;
                }else if(nums[left]+nums[right]<sum){
                    left++;
                }else if(nums[left]+nums[right]>sum){
                    right--;
                } 
            }
        }
        }
        
        return result;
    }
}