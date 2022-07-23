package dynamicProgramming.stocks;

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
 * https://www.youtube.com/watch?v=Pw6lrYANjz4&t=1228s
 */
public class StockBuySellKTransactions {

    public int maxProfit(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int[][] T = new int[K + 1][prices.length];

        for (int i = 1; i < T.length; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = Math.max(T[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, T[i - 1][j] - prices[j]);
            }
        }

        System.out.println(Arrays.deepToString(T));
        printActualSolution(T, prices);
        return T[K][prices.length - 1];
    }

    public int maxProfitSpaceEfficient(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) { //if k >= n/2, then you can make maximum number of transactions
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        int[] buy = new int[k + 1], sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }

    public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[k + 1][prices.length + 1];

        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        if (k >= n / 2) { //if k >= n/2, then you can make maximum number of transactions
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        for (int i = 1; i <= k; i++) {
            for (int priceDay = 1; priceDay <= prices.length; priceDay++) {

                int notTransactingAtPriceDay = dp[i][priceDay - 1];

                for (int m = 0; m < priceDay; m++) {
                    int transactionTillPriceDay = prices[priceDay - 1] - prices[m]; //[priceDay-1] because priceday begins at 1
                    /*
                     *  previous transaction till m,
                     *  because if we are buy at m '(prices[priceDay - 1] - prices[m])',
                     *  we should've completed transaction till m 'dp[i - 1][m]'
                     */
                    int previousTransactionTillM = dp[i - 1][m];
                    int temp = Math.max(notTransactingAtPriceDay, transactionTillPriceDay + previousTransactionTillM);
                    dp[i][priceDay] = Math.max(dp[i][priceDay], temp);
                }
            }
        }
        return dp[k][prices.length];
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
    public int maxProfitRecursive(int k, int[] prices) {
        Integer[][][] dp = new Integer[prices.length+1][2][k+1];
        return recursionHelper(prices,0,0,k,dp);
    }

    public int recursionHelper(int[] prices, int idx,int canSell, int txn,Integer[][][] dp){
        if(idx>=prices.length || txn==0 ) return 0;
        if(dp[idx][canSell][txn]!=null) return dp[idx][canSell][txn];
        if(canSell==0){

            int buy = -prices[idx]+recursionHelper(prices,idx+1,1,txn,dp);
            int notBuy = recursionHelper(prices,idx+1,0,txn,dp);
            return dp[idx][canSell][txn]=Math.max(buy,notBuy);
        }else{
            int sell = prices[idx]+recursionHelper(prices,idx+1,0,txn-1,dp);
            int notSell = recursionHelper(prices,idx+1,1,txn,dp);
            return dp[idx][canSell][txn]=Math.max(sell,notSell);
        }
    }

    public static void main(String args[]) {
        StockBuySellKTransactions sbt = new StockBuySellKTransactions();
        int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};

        System.out.println("Max profit fast solution " + sbt.maxProfit(prices, 3));
    }
}
