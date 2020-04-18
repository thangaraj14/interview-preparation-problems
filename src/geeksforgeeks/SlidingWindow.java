package geeksforgeeks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.*;
public class SlidingWindow {

    public static void main(String[] args) {
        int arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int k = 3;
        maxSlidingWindow(arr, k);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k==0) return new int[0];
         int[] result= new int[nums.length-k+1];
         int index=0;
        Deque<Integer> deque= new ArrayDeque<>();
        
         for(int i=0;i<nums.length;i++){
             
             while( !deque.isEmpty() &&(deque.peek()<i-k+1 || nums[deque.peekLast()]<nums[i])){
                 if(nums[deque.peekLast()]<nums[i]){
                      deque.pollLast();
                 }else{
                     deque.poll();
                 }
                 
             }
             
             deque.offer(i);
             
             if(i>=k-1){
                 result[index++]=nums[deque.peekFirst()];
             }
         }
         
         return result;
     }
}