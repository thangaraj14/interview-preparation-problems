package geeksforgeeks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array arr[] containing n elements.
 * The problem is to find maximum number of distinct elements (non-repeating) after removing k elements from the array.
 * Input : arr[] = {5, 7, 5, 5, 1, 2, 2}, k = 3
 * Output : 4
 *
 * Remove 2 occurrences of element 5 and
 * 1 occurrence of element 2.
 *
 * Input : arr[] = {1, 2, 3, 4, 5, 6, 7}, k = 5
 * Output : 2
 */
public class MaxDistinctElementAfterKRemoval {
   // after removing k elements

    /**
     * Create a hash table to store the frequency of each element.
     * Insert frequency of each element in a max heap.
     * Now, perform the following operation k times.
     * Remove an element from the max heap. Decrement its value by 1. After this if element is not equal to 0, then again push the element in the max heap.
     * @param arr
     * @param n
     * @param k
     * @return
     */
   static int maxDistinctNum(int[] arr, int n, int k) 
   { 
          // hash map to store  
          // frequency of each element  
          HashMap<Integer, Integer> map = new HashMap<>();

          // priority_queue 'pq' implemented as  
          // max heap  
          PriorityQueue<Integer> pq =
                      new PriorityQueue<>(Collections.reverseOrder());
            
          // storing frequency of each element in map 
          for (int i = 0; i < n; i++) {
               map.put(arr[i], map.getOrDefault(arr[i],0)+1);
          } 

          // inserting frequency of each element in 'pq' 
          for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
               pq.add(entry.getValue()); 
          } 

          while (k > 0) { 
                // get the top element of 'pq' 
                int temp = pq.poll(); 

                // decrement the popped element by 1  
                temp--;  

                // if true, then push the element in 'pq' 
                if (temp > 0) 
                    pq.add(temp); 
                k--; 
          }  

          // Count all those elements that appear  
          // once after above operations.  
          int res = 0; 
          while (pq.size() != 0) { 
                pq.poll(); 
                res++; 
          } 

          return res; 
   }

    /**
     * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
     * Input: arr = [5,5,4], k = 1
     * Output: 1
     * Explanation: Remove the single 4, only 5 is left.
     *
     * Input: arr = [4,3,1,1,3,3,2], k = 3
     * Output: 2
     * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
     * @param arr
     * @param k
     * @return
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if(arr.length==0) return 0;
        Map<Integer, Integer> frequencyMap= new HashMap<>();

        for(int i: arr){
            frequencyMap.put(i,frequencyMap.getOrDefault(i,0)+1);
        }

        PriorityQueue<Integer> maxQueue= new PriorityQueue<>();

        for(Map.Entry<Integer,Integer> entry: frequencyMap.entrySet()){
            maxQueue.offer(entry.getValue());
        }

        while(k-- >0){
            int temp= maxQueue.poll();
            temp-=1;
            if(temp>0) maxQueue.offer(temp);

        }

        int result=0;
        while(!maxQueue.isEmpty()){
            result++;
        }
        return result;
    }
}