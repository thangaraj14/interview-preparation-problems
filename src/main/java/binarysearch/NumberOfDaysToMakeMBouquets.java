package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 */
public class NumberOfDaysToMakeMBouquets {

    public int minDays(int[] bloomDay, int m, int k) {
        if(bloomDay.length< m*k) return -1;

        int left=1;
        int right= Arrays.stream(bloomDay).max().orElse(0);

        while(left<right){
            int mid= left+ (right-left)/2;
            if(isFeasible(bloomDay,mid,m,k)){
                right=mid;
            }else{
                left=mid+1;
            }
        }

        return left;
    }

    public boolean isFeasible(int[] bloomDay, int mid, int m, int k){
        int bouquets=0;
        int window=0;

        for(int day:bloomDay){

            if(day>mid){ // if the day is greater, then break the window
                window=0;
            }else{
                window++; // increase the window size
                if(window==k){ // if the window size is equal to K end the window, we get one bouquet and reset the window for next
                    bouquets++;
                    window=0;
                }
            }
        }
        return bouquets>=m;
    }
}
