package geeksforgeeks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.lintcode.com/problem/meeting-rooms-ii/
 */

public class MeetingRoomsII {
    /**
     * @param intervals: an array of meeting time intervals
     *
     * @return: the minimum number of conference rooms required
     */
    // [(0,30),(5,10),(15,20)]
    public int minMeetingRooms(List<Interval> intervals) {
<<<<<<< HEAD
        if (intervals == null || intervals.size() == 0)
            return -1;

        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // Interval max= intervals.get(0);
        queue.offer(intervals.get(0).end);
        for (int i = 1; i < intervals.size(); i++) {
            Interval temp = intervals.get(i);
            if (queue.peek() <= temp.start)
                queue.poll();
=======
        if (intervals == null || intervals.size() == 0) {
            return -1;
        }

        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals.get(0).end);
        for (int i = 1; i < intervals.size(); i++) {
            Interval temp = intervals.get(i);
            if (queue.peek() <= temp.start) {
                queue.poll();
            }
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
            queue.offer(temp.end);
        }

        return queue.size();

    }
}
