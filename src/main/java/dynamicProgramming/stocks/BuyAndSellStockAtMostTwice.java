package dynamicProgramming.stocks;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
class BuyAndSellStockAtMostTwice {

    public static void main(String args[]) {
        System.out.println("Maximum Profit = " + maxProfit(new int[]{2, 30, 15, 10, 8, 25, 80}));
    }

    /**
     * the idea is when we find a profit which is from
     * i to n, we can break it in to i to k, k+1 to n
     * in this manner at each point we can calculate profit from
     * (left min element to current element) and (current element to right max element)
     * the second part of the above eq can be achieved by coming from right to left
     * for input          [3,3,5,0,0,3,1,4]
     * profit from	l->r  [0,0,2,2,2,3,3,4]
     * profit from r->l  [4,4,4,4,4,3,3,0]
     * this simply states that at index 2 if we come from left the profit is 2
     * and we can initiate another transaction to obtain another profit
     */
    public static int maxProfit(int[] prices) {
        int[] profit1 = new int[prices.length];
        int[] profit2 = new int[prices.length];

        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit1[i] = Math.max(profit1[i - 1], prices[i] - min);
        }


        int max = prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            profit2[i] = Math.max(profit2[i + 1], max - prices[i]);
        }
        //at any pos 'i' profit1[i] denotes profit upto 0 to i
        // at any pos 'i' profit2[i] denotes profit upto i+1 to n
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            result = Math.max(result, profit2[i] + profit1[i]);
        }
        return result;
    }

    /**
     * Buy sell recursive template
     */
    public int maxProfitRecursive(int[] prices) {
        Integer[][][] dp = new Integer[prices.length + 1][2][3];
        return recursionHelper(prices, 0, 0, 2, dp);
    }

    public int recursionHelper(int[] prices, int idx, int canSell, int txn, Integer[][][] dp) {

        if (txn == 0) return 0;
        if (idx >= prices.length) return 0;
        if (dp[idx][canSell][txn] != null) return dp[idx][canSell][txn];
        if (canSell == 0) {
            int buy = -prices[idx] + recursionHelper(prices, idx + 1, 1, txn, dp);
            int notBuy = recursionHelper(prices, idx + 1, 0, txn, dp);
            return dp[idx][canSell][txn] = Math.max(buy, notBuy);
        } else {
            int sell = prices[idx] + recursionHelper(prices, idx + 1, 0, txn - 1, dp);
            int notSell = recursionHelper(prices, idx + 1, 1, txn, dp);
            return dp[idx][canSell][txn] = Math.max(sell, notSell);
        }
    }

}
