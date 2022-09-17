package dynamicProgramming.stocks;

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
            rest = sell + prices[i];
            sell = Math.max(sell, tmp - prices[i]);
        }
        return Math.max(buy, rest);
    }

    /**
     * cooldown[i] = max(cooldown[i - 1], sell[i - 1]); // Stay at cooldown, or rest from sell
     * proceed to buy, ie, we have no stock now, and the max profit should be ''last no stock profit'' or ''last rest profit''
     * <p>
     * buy[i] = max(buy[i - 1], cooldown[i - 1] - prices[i]); // Stay at buy, or buy from cooldown
     * //can proceed to sell, ie, we now have stock, and the profit should be ''last stock profit'' or ''last no stock but buy this time''
     * <p>
     * sell[i] = buy[i - 1] + prices[i]; // Only one way from s1
     * //we should sell then take a rest
     */
    public int maxProfitExtraSpace(int[] prices) {

        int n = prices.length;
        int[] buy = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];

        buy[1] = -prices[0];
        sell[1] = 0;
        if (n == 1) {
            return sell[0];
        }

        for (int i = 2; i <= n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i - 1]);

            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }
        return sell[n];

    }

    public static void main(String[] args) {
        new StockBuySellWithCoolDown().maxProfitExtraSpace(new int[]{1, 2, 3, 0, 2});
    }

    public int maxProfitRecursive(int[] prices) {
        Integer[][] dp = new Integer[prices.length][2];
        return recursionHelper(prices, 0, 0, dp);
    }

    public int recursionHelper(int[] prices, int idx, int canSell, Integer[][] dp) {
        if (idx >= prices.length) return 0;
        if (dp[idx][canSell] != null) return dp[idx][canSell];
        if (canSell == 0) {
            int buy = -prices[idx] + recursionHelper(prices, idx + 1, 1, dp);
            int notBuy = recursionHelper(prices, idx + 1, 0, dp);
            return dp[idx][canSell] = Math.max(buy, notBuy);
        } else {
            int sell = prices[idx] + recursionHelper(prices, idx + 2, 0, dp);
            int notSell = recursionHelper(prices, idx + 1, 1, dp);
            return dp[idx][canSell] = Math.max(sell, notSell);
        }
    }
}
