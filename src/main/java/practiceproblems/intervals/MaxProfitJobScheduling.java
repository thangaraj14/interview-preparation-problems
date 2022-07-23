package practiceproblems.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * tricky interval
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling
 *
 * https://www.youtube.com/watch?v=ZOP43iB_E_8&ab_channel=CodingDecoded
 *
 * So the goal is to find the profit up until the current start time and add the current profit to it,
 * so we use the TreeMap to store the endTime and profit, if we pass the current startTime and find the floorKey
 * from the map we will get the profit before the start of current startTime
 */
public class MaxProfitJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Interval[] intervals = new Interval[profit.length];

        for (int i = 0; i < profit.length; i++) {
            intervals[i] = new Interval(startTime[i], endTime[i], profit[i]);
        }
        // sort by endTime greedily
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.endTime));
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int result = Integer.MIN_VALUE;

        for (Interval interval : intervals) {
            Integer key = treeMap.floorKey(interval.startTime); // returns endTime which is just before the curr start
            result = Math.max(result, interval.profit + (key == null ? 0 : treeMap.get(key)));
            treeMap.put(interval.endTime, result);
        }

        return result;
    }

    /**
     * Thushar roy's approach
     */
    public int jobSchedulingEff(int[] st, int[] et, int[] profit) {
        int n = profit.length;
        Interval[] jobs = new Interval[n];
        for (int i = 0; i < n; i++)
            jobs[i] = new Interval(st[i], et[i], profit[i]);
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.endTime));

        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        for (int i = 1; i < n; i++) {
            dp[i] = jobs[i].profit;
            // we can further optimise the second for loop with binary search since the arr is sorted
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[j].endTime <= jobs[i].startTime) {
                    dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
                    break;
                }
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        /**
         *  for (int i = 1; i < n; i++) {
         *    int profit = jobs[i].profit;
         *    int l = search(jobs, i);
         *    if (l != -1)
         *       profit += dp[l];
         *    // Store maximum of including and excluding
         *      dp[i] = Math.max(profit, dp[i-1]);
         * }
         */
        return dp[n - 1];
    }

    // binary search code
    private int search(Interval[] jobs, int index) {
        int start = 0, end = index - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (jobs[mid].endTime <= jobs[index].startTime) {
                if (jobs[mid + 1].endTime <= jobs[index].startTime)
                    start = mid + 1;
                else
                    return mid;
            } else
                end = mid - 1;
        }
        return -1;
    }


    private static class Interval {
        int startTime;
        int endTime;
        int profit;

        public Interval(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", profit=" + profit +
                    '}';
        }

    }
}
