package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
   Si % Sj = 0 or Sj % Si = 0.
   If there are multiple solutions, return any subset is fine.
   Input: [2,3,4,6,10,8,24]
    Output: [2,4,8,24] each pair's modulo is 0
 */
public class LargestDivisibleSubset {

    /**
     * if a%b==0 means a>b, if b>a then the ans is b itself
     *inorder to have that we need to sort the array in increasing order
     at first each val is ans to itself, then we come from last so a is higher in a%b 
                                [2,   3,   4,  6,     8,   10,  24] 
                                 {2}  {3} {4}  {6}   {8}  {10} {24}
                                                   {8,24}

                                              {6,24}

                                         {4,8,24}

                                      {3,6,24}
                                      
                                {2,4,8,24}
     * 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums==null || nums.length==0) return Collections.emptyList();
        
        Arrays.sort(nums);
        List<Integer>[] result= new ArrayList[nums.length];

        int maxLength=0;
        int resIndex=-1;
        List<Integer> re;

        for(int i=nums.length-1;i>=0;i--){
            result[i]=new ArrayList<>(); // every element is an answer itself
            re= new ArrayList<>();
            result[i].add(nums[i]);
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]%nums[i]==0){ // when mod is zero, check for greater list size that posis has
                    if(result[j].size() > re.size()){ // this is to take even if 1 element is at j position
                        re=result[j]; // the reason we take list is consider 4,8,24 when i is at 4 and j is 8 mod is 0 means 4%24 is also zero
                    }
                }
            }
            result[i].addAll(re);
            if(result[i].size()>maxLength){
                maxLength=result[i].size();
                resIndex=i;
            }
        }
        Collections.sort(result[resIndex]);
        return result[resIndex];
    }
}