package dp.oiknapsack;

import java.util.Arrays;

/*
 * Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space Note: This code
 * shows the use of O(mn) space. This problem can be solved using only O(m)
 * space.  https://www.youtube.com/watch?v=DJ4a7cmjZY0
 *
 * https://leetcode.com/problems/coin-change-2/
 */
public class NumberOfUniqueWaysToMakeChange {

    public int numberOfSolutions(int total, int[] coins) {
        int[][] temp = new int[coins.length + 1][total + 1];
        for (int i = 0; i <= coins.length; i++) {
            temp[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (coins[i - 1] > j) {
                    temp[i][j] = temp[i - 1][j];
                } else {
                    temp[i][j] = temp[i][j - coins[i - 1]] + temp[i - 1][j];
                }
            }
            System.out.println(Arrays.toString(temp[i]));
        }
        return temp[coins.length][total];
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        System.out.println(new NumberOfUniqueWaysToMakeChange().numberOfSolutions(5, coins));
    }
}
