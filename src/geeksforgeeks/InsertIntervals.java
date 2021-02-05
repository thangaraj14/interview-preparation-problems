package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertIntervals {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }

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
}