package dynamicProgramming;

import java.util.Arrays;

public class MinimumCoinChange {

    public int coinChange(int[] coins, int amount) {
        if(coins.length==0 || amount ==0)return 0;

        int[] result= new int[amount+1]; // each cell in this array will represent an amount
        Arrays.fill(result, amount+1); // fill with random amount first
        result[0]=0; // for 0 amount, 0 is the answer

        // below code is going to solve each sub-problem leading up to 1..amount
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j]>i) continue;
                int change =i-coins[j]; // when i is 5 and coins =[1,2,5] and j=1(first coin)we subtract 5-1=4 we need ans for 4, which we'd have calculated when i=4
                int temp=result[change]+1; // when found a coin we add 1 to the position
                result[i]=Math.min(result[i],temp);

            }

        }

        return result[amount] > amount ? -1 : result[amount];
    }
}
