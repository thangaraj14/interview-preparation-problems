package practiceproblems.intervals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.lintcode.com/problem/meeting-rooms-ii/
 */

public class MeetingRoomsII {
    // [(0,30),(5,10),(15,20)]

    // without using priority queue these cases will fail
    // [[1,5],[8,9],[8,9]]
    // [[9,10],[4,9],[4,17]]
    public int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Use a min heap to track the minimum end time of merged intervals
        // this will take care of multiple same start time
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int end = intervals[0][1];
        queue.offer(end);
        for (int i = 1; i < intervals.length; i++) {
            if (queue.peek() <= intervals[i][0]) {
                queue.poll();
            }
            queue.offer(intervals[i][1]);
        }

        return queue.size();

    }

    // Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
    // Output: [[3,4]]

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Interval> que = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));

        for (List<Interval> list : schedule) {
            for (Interval i : list) {
                que.add(i);
            }
        }

        List<Interval> rt = new ArrayList<>();
        int max = -1;
        while (!que.isEmpty()) {
            Interval top = que.poll();
            if (max != -1 && top.start > max) {
                rt.add(new Interval(max, top.start));
            }
            max = Math.max(max, top.end);
        }

        return rt;
    }

    class Interval {

        public int buy, sell;
        int start; // for meeting problem
        int end;

        public Interval(int buy, int sell) {
            this.buy = buy;
            this.sell = sell;
        }

        public Interval() {

        }
    }

}
