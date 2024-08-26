package practiceproblems.intervals;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 */

public class OverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int maxObservedEnd = intervals[0][1];
        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start < maxObservedEnd) {
                result++;
                /**
                 * The reason we choose min of ends
                 * 1 --------------------
                 * 2    -----        ----------------- 3
                 *
                 * Suppose we have three intervals as above, and we sort them acc to start time.
                 * Then for interval 2, which overlaps with 1, we can either remove 1 or 2.
                 * If we removed 2, then interval 3 will overlap again with 1 and we have to remove one more interval(mostly 3).
                 * We are removing 2 intervals which is not the correct ans.
                 * We need more logic to make the code work correctly.
                 *
                 * So instead of 2 if we removed 1 then we have to update the previous end point as 1 is now gone.
                 * Hence, the assignment of the smaller endtime (e = itv.end).
                 * Interval 3 doesnt overlap with last end time and so we only have to remove 1 interval,
                 * which is what we expected.
                 */
                maxObservedEnd = Math.min(maxObservedEnd, end);
            } else {
                maxObservedEnd = end;
            }
        }

        return result;
    }

    public int eraseOverlappingIntervalsAlter(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));

        int ct = 1;
        int prev = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prev) {
                ct++;
                prev = intervals[i][1];
            }
        }
        return intervals.length - ct;
    }

}