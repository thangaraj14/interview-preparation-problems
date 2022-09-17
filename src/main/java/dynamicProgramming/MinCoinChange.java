package dynamicProgramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change-2/
 */
public class MinCoinChange {

    Integer[] cache;

    public int coinChange(int[] coins, int amount) {
        cache = new Integer[amount + 1];
        return recursionHelper(amount, coins);
    }

    public int recursionHelper(int amount, int[] coins) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        if (cache[amount] != null) return cache[amount];

        int minCount = Integer.MAX_VALUE;

        for (int coin : coins) {
            int count = recursionHelper(amount - coin, coins);
            if (count == -1) continue;
            minCount = Math.min(minCount, count + 1);
        }

        return cache[amount] = minCount == Integer.MAX_VALUE ? -1 : minCount;

    }


    public int coinChangeBottomUp(int[] coins, int amount) {
        Integer[] cache = new Integer[amount + 1];
        Arrays.fill(cache, amount + 1);
        cache[0] = 0;
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i - coin >= 0) {
                    cache[i] = Math.min(cache[i], cache[i - coin] + 1);
                }
            }
        }

        return cache[amount] == amount + 1 ? -1 : cache[amount];
    }
}
