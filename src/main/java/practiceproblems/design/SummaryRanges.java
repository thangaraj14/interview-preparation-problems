package practiceproblems.design;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals
 *
 * // When do we merge the number to an existing interval?
 * // 1. If it's within 1 interval(No merge operation needed in this case)
 * // 2. If it's 1 less than left bound or 1 larger than right bound of 1 interval
 */
public class SummaryRanges {
    TreeMap<Integer, Interval> sortedMap;

    /**
     * Initialize your data structure here.
     */
    public SummaryRanges() {
        sortedMap = new TreeMap<>();
    }

    public void addNum(int val) {
        if (sortedMap.containsKey(val)) return;

        Integer floor = sortedMap.lowerKey(val);
        Integer ceiling = sortedMap.higherKey(val);

        /*
             When both ceiling and floor are  there
             for example 1,1 and 3,3 are there and val=2 comes
             sortedMap.get(floor).end + 1 == val => 1+1 == 2
             ceiling - 1 == val => 3-1 ==2
             in this case remove 3 and update 1,1's end as 1,3
         */
        if (floor != null && ceiling != null && sortedMap.get(floor).end + 1 == val && ceiling - 1 == val) {

            sortedMap.get(floor).end = sortedMap.get(ceiling).end;
            sortedMap.remove(ceiling);

        } else if (floor != null && val <= sortedMap.get(floor).end + 1) {

            /*
             *  When ceiling is null or ceiling - 1 != val
             *  for ex 1 => [1,4] and val = 5 comes
             *      or
             *  1 => [1,6] and val = 5 comes
             */
            sortedMap.get(floor).end = Math.max(sortedMap.get(floor).end, val);

        } else if (ceiling != null && ceiling - 1 == val) {

            /*
             *  when [7,7] is there in the map and val=6 comes
             *  ceiling =7 and val =6
             *  update the val inside the map and remove the ceiling
             */
            sortedMap.put(val, new Interval(val, sortedMap.get(ceiling).end));
            sortedMap.remove(ceiling);

        } else {
            /*
                When both ceiling and floor are not there
             */
            sortedMap.put(val, new Interval(val, val));
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[sortedMap.size()][2];
        int i = 0;
        for (Integer key : sortedMap.keySet()) {
            result[i] = new int[]{sortedMap.get(key).start, sortedMap.get(key).end};
            i++;
        }

        return result;
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
