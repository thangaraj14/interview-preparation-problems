package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int buy, sell;
    int start; // for meeting problem
    int end;
    Interval(int buy, int sell){
        this.buy=buy;
        this.sell=sell;
    }
    Interval(){

    }
}

/**
 * https://www.geeksforgeeks.org/stock-buy-sell/
 */
// unresolved
class StockBuySellManyTimes {

    //200, 180, 260, 310, 40, 535, 695
    void stockBuySell(int price[], int n) {
        // Prices must be given for at least two days
        if (n == 1) {
            return;
        }

        int count = 0;

        List<Interval> result = new ArrayList<>();

        int i = 0;
        while (i < n - 1) {
            // Find Local Minima. Note that the limit is (n-2) as we are
            // comparing present element to the next element.
            while ((i < n - 1) && (price[i + 1] <= price[i]))
                i++;

            // If we reached the end, break as no further solution possible
            if (i == n - 1) {
                break;
            }

            Interval e = new Interval();
            e.buy = i++;
            // Store the index of minima

            // Find Local Maxima. Note that the limit is (n-1) as we are
            // comparing to previous element
            while ((i < n) && (price[i] >= price[i - 1]))
                i++;

            // Store the index of maxima
            e.sell = i - 1;
            result.add(e);

            // Increment number of buy/sell
            count++;
        }

        if (count == 0) {
            System.out.println("There is no day when buying the stock " + "will make profit");
        } else {
            for (int j = 0; j < count; j++)
                System.out.println(
                        "Buy on day: " + result.get(j).buy + "	 " + "Sell on day : " + result.get(j).sell);
        }

        return;
    }

    public static void main(String args[]) {
        StockBuySellManyTimes stock = new StockBuySellManyTimes();

        int price[] = { 200, 180, 260, 310, 40, 535, 695 };
        int n = price.length;

        stock.stockBuySell(price, n);
    }
}
