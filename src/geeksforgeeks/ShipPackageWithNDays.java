package geeksforgeeks;

import java.util.Arrays;

public class ShipPackageWithNDays{
    public int shipWithinDays(int[] weights, int D) {
        if(weights.length==0 || D==0) return 0;
        
        int lowerBound= Arrays.stream(weights).max().orElse(0);
        int upperBound= Arrays.stream(weights).sum();
        int mid=0;
        while(lowerBound<upperBound){
             mid= (lowerBound+upperBound)/2;
            boolean canCarry= binarySearchUtil(weights,mid,D);
            if(canCarry) lowerBound=mid+1;
            else upperBound=mid;
            
        }
        
        return lowerBound;
    }
    
    
    public boolean binarySearchUtil(int[] weights, int mid, int D){
        int sum=0;
        int currentDay=1;
        for(int weight:weights){
           if(sum+weight>mid){
               currentDay+=1;
               sum=0;
               
           }
           sum+=weight; 
        }
        
        return currentDay>D;
    }
}