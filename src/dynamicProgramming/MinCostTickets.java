package dynamicProgramming;

public class MinCostTickets {

    //1,4,6,7,8,20
    //2,7,50
    public int mincostTickets(int[] days, int[] costs) {
        if(days.length==0) return 0;

        boolean[] travelledDays= new boolean [366];

        for(int i=0; i< days.length;i++){
            travelledDays[days[i]]=true;
        }

        int[] result= new int[366];
        result[0]=0;
        for(int i=1; i<result.length; i++){
            if(!travelledDays[i]){
                result[i]=result[i-1];
                continue;
            }

//            If no trip on day i, then minCost(i) = minCost(i-1).
//                    minCost(i)=0 for all i ≤ 0.
//            Otherwise:
//            If a 1-day pass on day i. In this case, minCost(i) = minCost(i) + costs[0].
//                    If a 7-day pass ending on day i. then : In this case, minCost(i) = min(minCost(i − 7), minCost(i − 6), …, minCost(i − 1)) + costs[1].
//                    But since since minCost is increasing (adding a day never reduces the minCost) hence:
//            minCost(i) = minCost(i − 7) + costs[2]
//
//                Same case for 30-day pass also.
            int min=result[i-1]+costs[0];
            min=Math.min(min, result[Math.max(0,i-7)]+costs[1]);
            min=Math.min(min, result[Math.max(0,i-30)]+costs[2]);
            result[i]=min;
        }

        return result[365];

    }
}
