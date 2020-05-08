package geeksforgeeks;
public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int left=0;
           int right=0;
           int sum=0;
           int result=0;
         
           Map<Integer,Integer> map= new HashMap<>();
           map.put(0,1);
            // Let’s assume (D+E+3=k)
            // sum =A+B+C+D+E+3
            // preSum = A+B+C
            // Thus, we can compose critical equation
            // sum - preSum = k
            // Since we’re looking for specific preSum to compose value k
            // What specific preSum were looking?
            // Some math operation results in
            // sum - k = preSum
            // For multiple matching counts
            // Ex: 
            // input [0,-1,1,2,3] k=5
            // We notice that it’s necessary to record occurrence of specific preSum.
            // In this case, we need to know preSum 2 occur three times. [0,-1,1,2], [-1,1,2], [2]
            // This indicate that it's necessary to record preSum counts

          while(left<nums.length){
               sum+=nums[left];
              if(map.get(sum-k)!=null){
                  result+=map.get(sum-k);
              }
              map.put(sum,map.getOrDefault(sum,0)+1);
              left++;
             
           }
           return result;
     
       }
}