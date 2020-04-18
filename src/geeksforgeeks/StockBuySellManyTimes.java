package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int buy, sell;
}

/**
 * https://www.geeksforgeeks.org/stock-buy-sell/
 */
// unresolved
class StockBuySellManyTimes {
    public int maxProfit(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i])
                total += prices[i + 1] - prices[i];
        }

        return total;
    }

    public static void main(String args[]) {
        StockBuySellManyTimes stock = new StockBuySellManyTimes();

        int price[] = { 200, 180, 260, 310, 40, 535, 695 };
        int n = price.length;

        stock.stockBuySell(price, n);
    }
}
