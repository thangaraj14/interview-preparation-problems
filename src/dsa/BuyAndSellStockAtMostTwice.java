package dsa;

/**
 * https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * <p>
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution
 */
class BuyAndSellStockAtMostTwice {
    // { 2, 30, 15, 10, 8, 25, 80 };
    // 78,72,72,72,72,55
    //
    static int maxProfit(int price[], int n) {

        int profit[] = new int[n];
        /*
         * Get the maximum profit with only one transaction allowed. After this loop,
         * profit[i] contains maximum profit from price[i..n-1] using at most one trans.
         */
        int max_price = price[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // max_price has maximum of price[i..n-1]
            if (price[i] > max_price) {
                max_price = price[i];
            }

            // we can get profit[i] by taking maximum of:
            // a) previous maximum, i.e., profit[i+1]
            // b) profit by buying at price[i] and selling at
            // max_price
            profit[i] = Math.max(profit[i + 1], max_price - price[i]);
        }

        /*
         * Get the maximum profit with two transactions allowed After this loop,
         * profit[n-1] contains the result
         */
        int min_price = price[0];
        for (int i = 1; i < n; i++) {
            // min_price is minimum price in price[0..i]
            if (price[i] < min_price) {
                min_price = price[i];
            }

            // Maximum profit is maximum of:
            // a) previous maximum, i.e., profit[i-1]
            // b) (Buy, Sell) at (min_price, price[i]) and add profit of first trans. stored in profit[i]
            profit[i] = Math.max(profit[i - 1], profit[i] + (price[i] - min_price));

            //1->28+72
            //

        }
        return profit[n - 1];
    }

    public static void main(String args[]) {
        int price[] = { 2, 30, 15, 10, 8, 25, 80 };
        int n = price.length;
        System.out.println("Maximum Profit = " + maxProfit(price, n));
    }

    public int maxProfit(int[] prices) {
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for (int i = 0; i < prices.length; i++) {
            oneBuy = Math.min(oneBuy, prices[i]);//we set prices to negative, so the calculation of profit will be
            // convenient
            oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
            twoBuy = Math.min(twoBuy, prices[i] - oneBuyOneSell);//we can buy the second only after first is sold
            twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
        }

        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }

}
