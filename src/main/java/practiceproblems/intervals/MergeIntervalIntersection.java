package practiceproblems.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * Return the intersection of these two interval lists.
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 *
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class MergeIntervalIntersection {

    // inorder to find a overlapping part alone between two intervals
    // we take  
    //  start = max(a.start, b.start)
    // end = min(a.end, b.end) 
    // That is, the highest start time and the lowest end time will be the overlapping interval.
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < A.length && j < B.length) {

            if (A[i][0] >= B[j][0] && A[i][0] <= B[j][1] ||
                    B[j][0] >= A[i][0] && B[j][0] <= A[i][1]) { // this condition checks if there's a overlapping
                //A=>[0,2], B=> [1,5]
                result.add(new int[]{Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])});
            }
            // once added to result move the i or j based on lesser end time
            if (A[i][1] < B[j][1]) {  // Exhausted this range in A
                i++; // # Point to next range in A
            } else { //# Exhausted this range in B
                j++; //# Point to next range in B
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    public int[][] intervalIntersectionEff(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the starting point of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public int[][] intervalIntersectionBrute(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        for (int[] ints : A) {
            for (int[] value : B) {
                int[] intersect = findIntersection(ints, value);
                if (intersect.length != 0) list.add(intersect);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 1st interval ending should be greater than 2nd 's first
     * 1st interval begin should be less than 2nd's end
     */
    public int[] findIntersection(int[] a, int[] b) {
        if (a[1] < b[0] || b[1] < a[0]) return new int[]{};
        return new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])};
    }
}