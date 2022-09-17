package dynamicProgramming.stocks;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 */
public class BuyAndSellWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int days = prices.length;
        int[] buy = new int[days]; // the max profit in ith day when the last operation is buy
        int[] sell = new int[days]; // the max profit in ith day when the last operation is sell

        buy[0] = -prices[0];

        for (int i = 1; i < days; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }

        return sell[days - 1];
    }

    public int maxProfitRecursive(int[] prices, int fee) {
        Integer[][] dp = new Integer[prices.length + 1][2];
        return recursionHelper(prices, 0, 0, fee, dp);
    }

    public int recursionHelper(int[] prices, int idx, int canSell, int fee, Integer[][] dp) {

        if (idx >= prices.length) return 0;
        if (dp[idx][canSell] != null) return dp[idx][canSell];
        if (canSell == 0) {

            int buy = -prices[idx] + recursionHelper(prices, idx + 1, 1, fee, dp);
            int notBuy = recursionHelper(prices, idx + 1, 0, fee, dp);
            return dp[idx][canSell] = Math.max(buy, notBuy);
        } else {
            int sell = prices[idx] + recursionHelper(prices, idx + 1, 0, fee, dp) - fee;
            int notSell = recursionHelper(prices, idx + 1, 1, fee, dp);
            return dp[idx][canSell] = Math.max(sell, notSell);
        }
    }

}
