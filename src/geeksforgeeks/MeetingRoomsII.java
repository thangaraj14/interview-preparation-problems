package geeksforgeeks;

public class MeetingRoomsII {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals==null || intervals.size()==0) return -1;
        
        Collections.sort(intervals,(a,b)->Integer.compare(a.start,b.start));
        PriorityQueue<Integer> queue= new PriorityQueue<>();
        //Interval max= intervals.get(0);
        queue.offer(intervals.get(0).end);
        for(int i=1;i<intervals.size();i++){
             Interval temp= intervals.get(i);
             if(queue.peek()<=temp.start) queue.poll();
             queue.offer(temp.end);
        }
        return queue.size();
    }
}