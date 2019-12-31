package geeksforgeeks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 */
public class RailwayPlatformProblem {

    static int findPlatform(int[] arrival, int[] departure, int n) {
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int platNeeded = 1;
        int result = 1;
        int i = 1;
        int j = 0;

        while (i < n && j < n) {
            if (arrival[i] <= departure[j]) {
                platNeeded++;
                i++;

                if (platNeeded > result) {
                    result = platNeeded;
                }
            } else {
                platNeeded--;
                j++;
            }
        }
        return result;
    }

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return -1;
        }
        //[(0,12),(5,10),(15,20)]

        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals.get(0).end);
        for (int i = 1; i < intervals.size(); i++) {
            Interval temp = intervals.get(i);
            if (queue.peek() <= temp.start) {
                queue.poll();
            }
            queue.offer(temp.end);
        }
        return queue.size();
    }

    public static void main(String[] args) {

        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };

        int n = arr.length;
        System.out.println("Minimum Number of Platforms Required = " + findPlatform(arr, dep, n));
    }

    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}

