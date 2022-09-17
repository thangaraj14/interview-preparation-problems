package practiceproblems.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        //Add to the output all the intervals starting before newInterval.
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }

        //Add to the output newInterval.
        // Merge it with the last added interval if newInterval starts before the last added interval.
        int[] mI = newInterval;
        while (i < n && intervals[i][0] <= newInterval[1]) {
            mI[0] = Math.min(intervals[i][0], mI[0]);
            mI[1] = Math.max(intervals[i][1], mI[1]);
            i++;
        }
        result.add(mI);
        while (i < n) {
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size() - 1][2]);
    }

    public static int[][] insertEff(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        // Iterate through all slots
        for (int[] interval : intervals) {

            // if newInterval before interval insert newInterval & update slot as new interval
            if (newInterval[1] < interval[0]) {
                result.add(newInterval);
                newInterval = interval;
            }

            // if interval is lesser than new Interval insert slot
            else if (interval[1] < newInterval[0]) {
                result.add(interval);
            }

            // if above conditions fail it's an overlap since possibility of new interval existing in left & right of interval is checked
            // update lowest of start & highest of end & not insert
            else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }

        // insert the last newInterval
        result.add(newInterval);

        // convert to int[][] array
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(insertEff(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
    }
}