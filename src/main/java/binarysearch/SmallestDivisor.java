package binarysearch;

import java.util.Arrays;

// https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
public class SmallestDivisor {

    public int smallestDivisor(int[] nums, int threshold) {
        int left=1;
        int right= Arrays.stream(nums).max().orElse(0);

        while(left<right){
            int mid = left+(right-left)/2;

            if(isFeasible(nums,mid,threshold)){
                right=mid;
            }else{
                left=mid+1;
            }
        }

        return left;
    }

    public boolean isFeasible(int[]nums, int mid, int thres){
        int result=0;
        for(int i:nums){
            result+= Math.ceil((double)(i)/(double)(mid));
        }

        return result<=thres;
    }
}
