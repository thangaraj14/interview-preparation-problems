package dynamicProgramming;

public class UniqueCoinChange {

    public int change(int amount, int[] coins) {
        int[][] dp= new int[coins.length+1][amount+1];

        for(int i=0;i<=coins.length; i++){
            dp[i][0]=1;
        }

        for(int i=1; i<=coins.length; i++){
            for(int j=1; j<=amount; j++){
                // total ways= when i don't use the current coin([i-1,j])+ when i use the current coin, i go back to check
                // what is the best i got when j-coins[i-1] happened
                dp[i][j]= dp[i-1][j];
                if(j-coins[i-1]>=0)
                    dp[i][j]+= dp[i][j-coins[i-1]];
            }
        }


        return dp[coins.length][amount];
    }
}
