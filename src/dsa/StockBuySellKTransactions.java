package dsa;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 12/22/2015
 *
 * @author Tushar Roy
 * <p>
 * Time complexity - O(number of transactions * number of days) Space
 * complexity - O(number of transactions * number of days)
 * <p>
 * https://leetcode.com/discuss/15153/a-clean-dp-solution-which-generalizes-to-k-transactions
 */
public class StockBuySellKTransactions {

    public int maxProfit(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K + 1][prices.length];

        for (int i = 1; i < T.length; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = Math.max(T[i][j - 1], prices[j] + maxDiff);
                //   T[i - 1][j] - prices[j] means that we are buying the stock(taking amount) from the previous profit
                maxDiff = Math.max(maxDiff, T[i - 1][j] - prices[j]);
            }
        }

        System.out.println(Arrays.deepToString(T));
        printActualSolution(T, prices);
        return T[K][prices.length - 1];
    }

    public void printActualSolution(int T[][], int prices[]) {
        int i = T.length - 1;
        int j = T[0].length - 1;

        Deque<Integer> stack = new LinkedList<>();
        while (true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j - 1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];
                for (int k = j - 1; k >= 0; k--) {
                    if (T[i - 1][k] - prices[k] == maxDiff) {
                        i = i - 1;
                        j = k;
                        stack.addFirst(j);
                        break;
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            System.out.println("Buy at price " + prices[stack.pollFirst()]);
            System.out.println("Sell at price " + prices[stack.pollFirst()]);
        }

    }

    public static void main(String args[]) {
        StockBuySellKTransactions sbt = new StockBuySellKTransactions();
        int prices[] = { 3, 2, 6, 5, 0, 3 };

        System.out.println("Max profit fast solution " + sbt.maxProfit(prices, 3));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0) {
            return 0;
        }

        if (k > prices.length / 2) {
            return quickSolve(prices);
        }

        int[][] maxProfit = new int[k + 1][prices.length];
        //int diff=0;
        for (int i = 1; i <= k; i++) {
            int diff = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                maxProfit[i][j] = Math.max(maxProfit[i][j - 1], prices[j] + diff);
                diff = Math.max(diff, maxProfit[i - 1][j] - prices[j]);
            }
        }

        return maxProfit[k][prices.length - 1];
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        return profit;
    }
}
