package practiceproblems.intervals;

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
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (result.isEmpty() || start > result.get(result.size() - 1)[1]) {
                result.add(new int[]{start, end});
            } else {
                result.get(result.size() - 1)[1] = Math.max(end, result.get(result.size() - 1)[1]);
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