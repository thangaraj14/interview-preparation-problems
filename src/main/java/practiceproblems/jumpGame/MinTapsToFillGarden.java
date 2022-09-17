package practiceproblems.jumpGame;

/**
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 */
public class MinTapsToFillGarden {
    public int minTaps(int n, int[] ranges) {
        /**
         * i <= end. Suppose ranges = [1,2,1,0,2,1,0,1], arr becomes [3,3,6,0,6,0,8,0].
         * After the 1st step, farCanReach becomes 3, end also becomes 3.
         * Now we can choose to move to index 1, index 2, or index 3.
         * Since moving to index 2 will give us the largest move (6) in the next step, farCanReach is updated to 6.
         * And we can move from there.
         */
        for (int i=0; i<ranges.length; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = i + ranges[i];
            if (start < i) {
                // update the maximum right bound from left bound start.
                ranges[start] = Math.max(end, ranges[start]);
            }
        }

        int curEnd = 0, steps = 0, curFurtherest = 0;
        for (int i=0; i<n; i++) {
            // update the max point we can reach.
            curFurtherest = Math.max(curFurtherest, ranges[i]);
            if (i == curEnd) {
                // Being greedy.
                // We only have a new jump when we are reaching the furtherest point from previous jump
                // Update the curEnd to the new max point
                steps++;
                curEnd = curFurtherest;
            }
        }
        return curEnd >= n ? steps : -1;
    }
}
