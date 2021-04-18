package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * concept is same as shipContainer in D days problem
 */
public class SubArraySplitSum {

    public int splitArray(int[] nums, int m) {
        int left= Arrays.stream(nums).max().orElse(0);
        int right= Arrays.stream(nums).sum();

        while(left<right){
            int mid= left+ (right-left)/2;
            System.out.println("mid "+mid);

            if(isFeasible(nums,mid, m)){
                //if count<=m, we use r=mid; Because mid is still a possible value to divide the array into m subarrays.
                // if we use r=mid-1, we may bypass the true value.
                right=mid;
            }else{
                /*
                 * it means that the mid value we pick is too small because
                 * we've already tried our best to make sure each part holds as many non-negative numbers
                 *  as we can but we still have numbers left.
                 * So, it is impossible to cut the array into m parts and make sure each parts is no larger than mid.
                 * We should increase m. This leads to l = mid + 1
                 */
                left=mid+1;
            }

            System.out.println("after isFeasible:: left "+left+" right "+right);
            System.out.println();
        }

        return left;
    }

    /**
     * Use greedy to narrow down left and right boundaries in binary search.
     * 3.1 Cut the array from left.
     * 3.2 Try our best to make sure that the sum of numbers between each two cuts (inclusive) is large enough but still less than mid.
     * 3.3 We'll end up with two results: either we can divide the array into more than m subarrays or we cannot.
     * @param nums
     * @param limit
     * @param m
     * @return
     */
    private boolean isFeasible(int[] nums, int limit, int m){
        int sum=0;
        int partition=1;
        for(int num:nums){
            sum+=num;
            if(sum>limit){
                partition++;
                System.out.println("isFeasible  limit is:: "+ limit +" "+ "but sum is " + sum  + " so increasing partition "+partition);
                sum=num;
            }

        }
        System.out.println("End of isFeasible:: sum is "+ sum  + " and partition is "+partition);
        return partition<=m;
    }

    public static void main(String[] args) {
        System.out.println(new SubArraySplitSum().splitArray(new int[]{7,2,5,10,8}, 2));
        //System.out.println(new SubArraySplitSum().splitArray(new int[]{1,4,4}, 3));
    }
}
