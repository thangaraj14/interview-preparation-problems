package geeksforgeeks;

public class MaxDistinctElementAfterKRemoval {
   // after removing k elements  
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
               if(map.containsKey(arr[i])) { 
                    int val = map.get(arr[i]); 
                    val++; 
                    map.remove(arr[i]); 
                    map.put(arr[i], val); 
                 } 

               else  
                   map.put(arr[i], 1); 
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
}