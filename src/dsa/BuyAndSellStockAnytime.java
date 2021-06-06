package dsa;

/**
 * Kadane's Algorithm
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 * <p>
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BuyAndSellStockAnytime {

    public int maxProfitI(int[] prices) {

        int sum = 0;
        int maxDiff = 0;

        for (int i = 1; i < prices.length; i++) {
            sum += (prices[i] - prices[i - 1]);
            if (sum < 0) {
                sum = 0;
            }
            maxDiff = Math.max(maxDiff, sum);
        }
        return maxDiff;
    }

    public int maxProfitII(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                total += prices[i + 1] - prices[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        BuyAndSellStockAnytime stock = new BuyAndSellStockAnytime();
        int[] arr = { 7, 1, 5, 3, 6, 4 };
        //100, 180, 260, 310,40, 535, 695
        System.out.println(stock.maxProfitI(arr));
        System.out.println(stock.maxProfitII(arr));
    }
}
