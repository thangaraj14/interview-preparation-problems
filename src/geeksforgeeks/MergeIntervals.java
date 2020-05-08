package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 */
class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

   /*     Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));*/
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();
        int[] prevInterval = intervals[0];
        result.add(prevInterval);
        for (int[] currInterval : intervals) {
            if (currInterval[0] <= prevInterval[1]) // Overlapping intervals, move the end if needed
            {
                prevInterval[1] = Math.max(prevInterval[1], currInterval[1]);
            } else {                             // Disjoint intervals, add the new interval to the list
                prevInterval = currInterval;
                result.add(prevInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // [[1,3],[2,6],[8,10],[15,18]]
        int[][] arr = { { 1, 9 }, { 6, 8 }, { 2, 4 }, { 4, 7 } };
        //{ { 1, 3 }, { 2, 4 }, { 5, 7 }, { 6, 8 } };
        //{{1, 9}, {2, 4}, {4, 7}, {6, 8}};
        System.out.println(Arrays.deepToString(merge(arr)));
    }
}