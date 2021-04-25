package dp.unboundedknapsack;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 */
class MinimumCostTickets {

    public static int mincostTickets(int[] days, int[] costs) {
        if (days.length == 0) {
            return 0;
        }

        boolean[] travelledDays = new boolean[366];

        for (int i = 0; i < days.length; i++) {
            travelledDays[days[i]] = true;
        }

        int[] result = new int[366];
        result[0] = 0;
        for (int i = 1; i < result.length; i++) {
            if (!travelledDays[i]) {
                result[i] = result[i - 1];
                continue;
            }
            int min = result[i - 1] + costs[0];
            min = Math.min(min, result[Math.max(0, i - 7)] + costs[1]);
            min = Math.min(min, result[Math.max(0, i - 30)] + costs[2]);
            result[i] = min;
        }

        return result[365];

    }

    public static void main(String[] args) {
        mincostTickets(new int[] { 1, 4, 6, 7, 8, 20 }, new int[] { 2, 7, 15 });
    }
}