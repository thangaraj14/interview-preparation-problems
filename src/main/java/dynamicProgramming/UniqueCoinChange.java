package dynamicProgramming;


/**
 * https://leetcode.com/problems/coin-change
 */
public class UniqueCoinChange {

    public int changeSpaceOptimised(int amount, int[] coins) {
        int[] combi = new int[amount + 1];
        combi[0] = 1;
        for (int coin : coins) {
            for (int j = 1; j <= amount; j++) {
                if (j - coin >= 0)
                    combi[j]+=  combi[j - coin];
            }
        }
        for (int a : combi)
            System.out.print(a + " ");
        return combi[amount];
    }

    public int changeRecursion(int amount, int[] coins) {
        Integer[][] dp = new Integer[amount+1][coins.length+1];
        return recursionHelper(amount,0,coins,dp);
    }

    public int recursionHelper(int amount, int idx, int[] coins,Integer[][] dp){
        if(amount==0) return 1;
        if(amount<=0 || idx>=coins.length) return 0;
        if(dp[amount][idx]!=null) return dp[amount][idx];

        return dp[amount][idx]=recursionHelper(amount-coins[idx],idx,coins,dp)+recursionHelper(amount,idx+1,coins,dp);
    }

}
