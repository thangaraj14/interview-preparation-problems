package dynamicProgramming;

public class MinCostTickets {

    //1,4,6,7,8,20
    //2,7,50
    public int mincostTickets(int[] days, int[] cost) {
        int lastDayTravel = days[days.length - 1];

        int[] daysDp = new int[lastDayTravel + 1];
        boolean[] travelDays = new boolean[lastDayTravel + 1];

        for (int day : days) {
            travelDays[day] = true;
        }

        for (int i = 1; i <= lastDayTravel; i++) {

            if (!travelDays[i]) {
                daysDp[i] = daysDp[i - 1];
            } else {
                int oneDayPass = daysDp[i - 1] + cost[0];
                int sevenDaysPass = daysDp[Math.max(0, i - 7)] + cost[1];
                int thirtyDayPass = daysDp[Math.max(0, i - 30)] + cost[2];

                daysDp[i] = Math.min(oneDayPass, Math.min(sevenDaysPass, thirtyDayPass));
            }
        }

        return daysDp[lastDayTravel];
    }


    private int recursionUtil(int totalDaysOfTravel, boolean[] willTravel, int[] costs, Integer[] dp) {
        if (totalDaysOfTravel <= 0) return 0;

        if (dp[totalDaysOfTravel] != null) return dp[totalDaysOfTravel];

        int daily;
        if (willTravel[totalDaysOfTravel]) {
            daily = recursionUtil(totalDaysOfTravel - 1, willTravel, costs, dp) + costs[0];// Corresponding to daily package
        } else {
            daily = recursionUtil(totalDaysOfTravel - 1, willTravel, costs, dp);// This is the case when we cant take daily package but still we need to make recursive call to change the state
        }

        int weekly = recursionUtil(totalDaysOfTravel - 7, willTravel, costs, dp) + costs[1];// Corresponding to weekly package
        int monthly = recursionUtil(totalDaysOfTravel - 30, willTravel, costs, dp) + costs[2];// Corresponding to monthly package
        dp[totalDaysOfTravel] = Math.min(daily, Math.min(weekly, monthly));
        return dp[totalDaysOfTravel];

    }

    // Time Complexity-: O(N)  Space Complexity -: O(366)
    public int mincostTicketsRecur(int[] days, int[] costs) {
        int totalDaysOfTravel = days[days.length - 1];
        boolean[] willTravel = new boolean[totalDaysOfTravel+1];
        for (int day : days) {
            willTravel[day] = true;
        }

        Integer[] dp = new Integer[totalDaysOfTravel+1];
        recursionUtil(totalDaysOfTravel, willTravel, costs, dp);
        return dp[totalDaysOfTravel];
    }


}
