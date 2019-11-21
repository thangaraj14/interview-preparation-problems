package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return result.toArray(new int[0][]);

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int[] arr : intervals) {
            System.out.println(arr[0] + "" + arr[1]);
            if (arr[0] <= end) {
                end = Math.max(end, arr[1]);
            } else {
                result.add(new int[]{start, end});
                start = arr[0];
                end = arr[1];
            }
        }
        result.add(new int[]{start, end});
        return result.toArray(new int[0][]);

    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {2, 4}, {5, 7}, {6, 8}};
        //{{1, 9}, {2, 4}, {4, 7}, {6, 8}};
        System.out.println(Arrays.deepToString(merge(arr)));
    }
}