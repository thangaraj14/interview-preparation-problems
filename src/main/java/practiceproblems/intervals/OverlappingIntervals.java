package practiceproblems.intervals;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 */

public class OverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int end = intervals[0][1];

        int result = 0;
        for (int i = 1; i < intervals.length; i++) {

            result++;

            if (intervals[i][0] < end) {
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
                 * Hence the assignment of the smaller endtime (e = itv.end).
                 * Interval 3 doesnt overlap with last end time and so we only have to remove 1 interval,
                 * which is what we expected.
                 */
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }

        return result;
    }

}