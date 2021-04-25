package dp.unboundedknapsack;

import java.util.Arrays;

/**
 * Time complexity - O(coins.size * total) Space complexity - O(coins.size *
 * total)
 * <p>
 * https://www.youtube.com/watch?v=NJuKJ8sasGk&t=324s
 * <p>
 * https://leetcode.com/problems/coin-change/
 * https://www.youtube.com/watch?v=jgiZlGzXMBw&t=139s
 */
public class CoinChangingMinimumCoin {

    public int minimumCoinBottomUp(int[] coins, int total) {

        int[] T = new int[total + 1];
        int[] R = new int[total + 1];
        T[0] = 0;
        for (int i = 1; i <= total; i++) {
            T[i] = Integer.MAX_VALUE - 1;
            R[i] = -1;
        }

        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= total; i++) {
                if (i >= coins[j]) {
                    if (T[i - coins[j]] + 1 < T[i]) {
                        T[i] = 1 + T[i - coins[j]];
                        R[i] = j;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(T));
        System.out.println(Arrays.toString(R));
        printCoinCombination(R, coins);
        return T[total];
    }

    private void printCoinCombination(int[] R, int[] coins) {
        if (R[R.length - 1] == -1) {
            System.out.print("No solution is possible");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins used to form total ");
        while (start != 0) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
        System.out.print("\n");
    }

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0 || amount == 0) {
            return 0;
        }

        int[] result = new int[amount + 1];
        Arrays.fill(result, amount + 1);
        result[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    continue;
                }
                System.out.println(i + "--" + coin);
                int temp = result[i - coin] + 1;
                result[i] = Math.min(result[i], temp);
            }
        }
        return result[amount] > amount ? -1 : result[amount];
    }

    public static void main(String[] args) {
        int total = 13;
        int[] coins = { 7, 2, 6, 3 };
        CoinChangingMinimumCoin cc = new CoinChangingMinimumCoin();
        int bottomUpValue = cc.minimumCoinBottomUp(coins, total);

        System.out.print(bottomUpValue);
    }
}