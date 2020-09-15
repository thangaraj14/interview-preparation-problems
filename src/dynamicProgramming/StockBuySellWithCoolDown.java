package dynamicProgramming;

public class StockBuySellWithCoolDown {

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length < 2) return 0;
        int buy = 0, sell = -prices[0], rest = 0;

        // Assume the buy, sell and rest are states
        // the transistions would be
        // 1) from Rest you have to come to buy
        // 2) from buy you can rest/hold or you can sell
        // 3) from sell you can hold or sell and go to Rest

        // state 1=> first transistion max(buy, rest) we can either buy or rest at this point
        // state 2=> we can either hold what was there in previous state or buy so '-' price[i]
        // state 3=> to come to rest we have to sell and make profit so only the '+' sign

        for (int i = 1; i < prices.length; i++) {
            int tmp = buy;
            buy = Math.max(buy, rest);
            rest = sell+prices[i];
            sell = Math.max(sell,tmp -prices[i]);
        }
        return Math.max(buy,rest);
    }

    public static int maxProfit1(int[] prices){
        if(prices==null || prices.length<=1){
            return 0;
        }
        /**
         there can be two types of profit we need to track
         sellProf[i] - profit earned by selling on ith day
         restProf[i] - profit earned by resting on ith day
         */
        int sellProf = 0;
        int restProf = 0;
        int lastProf = 0;
        for(int i = 1;i<prices.length; i++){
            lastProf = sellProf;
            //the current sellProf is either by selling on ith day or by resting on ith day
            sellProf = Math.max(sellProf + prices[i] - prices[i-1], restProf);
            restProf = Math.max(lastProf, restProf);
        }
        return Math.max(sellProf, restProf);
    }

    public static void main(String[] args) {
        new StockBuySellWithCoolDown().maxProfit1(new int[]{1,2});
    }
}
