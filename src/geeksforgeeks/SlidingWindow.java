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
        if(nums.length==0)return new int[0];
        List<Integer> result= new ArrayList<>();
        
        Deque<Integer> queue= new ArrayDeque<>();
       // queue.offer(nums[0]);
        for(int i=0;i<nums.length;i++){
            while(!queue.isEmpty() && queue.peek()<i-k+1){
                queue.poll();
            }
            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            
            queue.offer(i);
            
            if(i>=k-1){
                result.add(nums[queue.peekFirst()]);
            }
        }
        //int[] res= new int[result.size()];
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}