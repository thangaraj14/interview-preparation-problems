package practiceproblems.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class IntervalsBetweenIdenticalElements {

    /**
     * https://leetcode.com/problems/intervals-between-identical-elements
     *
     * Consider 1's at different postions of an array.
     * x  y  z   p   q
     * 1  1  1   1   1
     *
     * consider 1 at index z: |z - x| + |z - y| + |z - p| + |z - q|
     *
     * when we are looping from left to right we are storing sum and count of previous indices of num in maps.
     * |z - x| + |z - y| = z - x + z - y, since z is greater than x and y.
     * z - x + z - y = 2z - (x + y) = (count) * (currentIndex) - (sum).
     *
     * Similarly, we can calculate the |z - p| + |z - q| when we loop from right to left.
     *
     * @param arr
     * @return
     */
    public long[] getDistances(int[] arr) {
        long[] output = new long[arr.length];
        Map<Integer, Long> sumMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.length; ++i) {
            long leftsum = sumMap.getOrDefault(arr[i], 0L);
            int leftcount = countMap.getOrDefault(arr[i], 0);

            output[i] += i * (long) leftcount - leftsum;
            sumMap.put(arr[i], leftsum + i);
            countMap.put(arr[i], leftcount + 1);
        }

        sumMap = new HashMap<>();
        countMap = new HashMap<>();
        int len = arr.length;
        for (int i = len - 1; i >= 0; --i) {
            //since now the current index goes from right to left, all of the prev would be greater than our current idx
            long rightsum = sumMap.getOrDefault(arr[i], 0L);
            int rightcount = countMap.getOrDefault(arr[i], 0);

            output[i] += rightsum - i * (long) rightcount;
            sumMap.put(arr[i], rightsum + i);
            countMap.put(arr[i], rightcount + 1);
        }

        return output;
    }
}
