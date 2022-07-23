package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/knight-dialer/
 */
public class KnightDialer {
    int[][] pos = new int[][]{{2, -1}, {2, 1}, {1, -2}, {1, 2}, {-1, 2}, {-1, -2}, {-2, -1}, {-2, 1}};
    int max = (int) Math.pow(10, 9) + 7;
    Map<String, Integer> cache = new HashMap<>();

    public int knightDialer(int n) {
        long s = 0;
        //do n hops from every i, j index (the very requirement of the problem)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                s = (s + recursionHelper(n, i, j)) % max;
            }
        }
        return (int) s;
    }

    public int recursionHelper(int n, int i, int j) {
        if (i < 0 || j < 0 || i >= 4 || j >= 3 || (i == 3 && j != 1)) return 0;

        if (1 == n) return 1;
        String key = i + "" + j + "" + n;
        if (cache.containsKey(key)) return cache.get(key);

        int res = 0;
        for (int[] p : pos) {
            res += recursionHelper(n - 1, i + p[0], j + p[1]);
            res %= max;
        }
        cache.put(key, res);
        return cache.get(key);
    }

    public static int knightDialerBottomUp(int n) {
        int MOD = 1000000007;
        int[][] paths = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}}; // Previous moves of knight-> For instance, if a knight is at 0, it reached from either 4 or 6. Similarly if it is at 1, it is reached from 7 or 9  & so on
        int[][] dp = new int[n + 1][10]; // rows -> no of steps taken to reach row i      cols-> no of digits
        for (int j = 0; j < 10; j++)
            dp[1][j] = 1; //populate the base case for n =1

        for (int i = 2; i <=n ; i++) { // no of steps taken by knight to reach i
            for (int j = 0; j < 10; j++) { // no of digits
                for (int p : paths[j]) { // Previous move of knight in order to reach digit j
                    dp[i][j] += dp[i - 1][p]; // cumulatively add from the previous knight move. For instance., F(2, 0) -> F(1,4) +  F(1,6) F(2,6) -> F(1,0) + F(1,1) + F(1,7)
                }
                dp[i][j] %= MOD;
            }
        }

        for (int i = 0; i <=n ; i++) { // no of steps taken by knight to reach i
            for (int j = 0; j < 10; j++) {
                System.out.print(dp[i][j] +" ");
            }
            System.out.println();
        }

        double sum = 0d;
        for (int j = 0; j < 10; j++)
            sum += dp[n][j];
        return (int) (sum % MOD);
    }

    public static void main(String[] args) {
        System.out.println(knightDialerBottomUp(3));
    }
}
