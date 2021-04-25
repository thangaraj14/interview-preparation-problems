package dp;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 * <p>
 * What is the least number of egg-droppings that is guaranteed to work in all cases?
 * <p>
 * https://youtu.be/iOaRjDT0vjc?t=1233
 */
public class EggDropping {

    public int calculate(int eggs, int floors) {

        int[][] dp = new int[eggs + 1][floors + 1];
        int c;

        // only one egg
        for (int i = 0; i <= floors; i++) {
            dp[1][i] = i;
        }

        for (int e = 2; e <= eggs; e++) {
            for (int f = 1; f <= floors; f++) {
                dp[e][f] = Integer.MAX_VALUE;
                for (int k = 1; k <= f; k++) {
                    // if egg breaks then egg-1 and floor -1 ==> dp[e - 1][k - 1]
                    // else no change in egg count and remaining floors which is f-k ==> dp[e][f - k]
                    // k means which floor we are in -- > first floor , second floor
                    // 2 Eggs -> 3 Floors
                    //	• 2 Eggs -> lets try from 1st Floor-> 1,0 ,, 2,2
                    //	• 2 Eggs -> lets try from 2nd Floor -> 1,1 ,, 2,1
                    //	  2 Eggs -> lets try from 3rd Floor -> 1,2 ,, 2,0
                    System.out.println(
                            "dp[" + (e - 1) + "][" + (k - 1) + "]" + dp[e - 1][k - 1] + " dp[" + e + "][" + (f - k)
                                    + "])" + dp[e][f - k]);
                    c = 1 + Math.max(dp[e - 1][k - 1], dp[e][f - k]);
                    if (c < dp[e][f]) {
                        dp[e][f] = c;
                    }
                }
            }
        }
        return dp[eggs][floors];
    }

    public static int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        }
        return m;
    }

    public static void main(String args[]) {
        EggDropping ed = new EggDropping();
        System.out.println(ed.calculate(2, 4));
        // System.out.println(superEggDrop(2, 6));
    }

}