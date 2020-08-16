package geeksforgeeks;

/**
 * Given a collection of intervals, find the minimum number of intervals 
 * you need to remove to make the rest of the intervals non-overlapping.
 * Input: [[1,2],[2,3],[3,4],[1,3]]
   Output: 1
   Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
   Input: [[1,2],[1,2],[1,2]]
   Output: 2
   Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 */

public class OverlappingIntervals{
   
        public int eraseOverlapIntervals(int[][] intervals) {
            if(intervals.length==0) return 0;
            
          PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) ->  Integer.compare(a[0],b[0]));
            
            for(int[] interval: intervals){
                queue.offer(interval);
            }
            int result=0;
           
            int end= queue.poll()[1];
            
            while(!queue.isEmpty()){
                if(end>queue.peek()[0]) {
                    result++;
                    end=Math.min(end, queue.poll()[1]);
                }else{
                   end=queue.poll()[1];
                }
            }
            
            return result;
        }
    
}