package dsa;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=8LusJS5-AGo&t=300s&ab_channel=TusharRoy-CodingMadeSimple
 */
class KnapsackProblem {

    static int knapSack(int[] val, int[] wt, int n, int W) {
        int[][] arr = new int[2][W + 1];

        int i = 0;
        while (i < n) {
            int j = 0;
            if (i % 2 != 0) {
                while (++j <= W) {
                    if (wt[i] <= j) {
                        arr[1][j] = Math.max(val[i] + arr[0][j - wt[i]], arr[0][j]);
                    } else {
                        arr[1][j] = arr[0][j];
                    }
                }
            } else {
                while (++j <= W) {
                    if (wt[i] <= j) {
                        arr[0][j] = Math.max(val[i] + arr[1][j - wt[i]], arr[1][j]);
                    } else {
                        arr[0][j] = arr[1][j];
                    }
                }
            }
            i++;
            System.out.println(Arrays.deepToString(arr));
        }
        return (n % 2 != 0) ? arr[0][W] : arr[1][W];
    }

    public static void main(String[] args) {
        int[] wt = { 2, 3, 4, 5 };
        int[] val = { 1, 2, 4, 6 };
        int W = 7;
        int n = 4;
        System.out.println(knapSack(val, wt, n, W));
    }

    public int findProfit(int[] val, int[] wt, int W) {
        int[][] K = new int[val.length + 1][W + 1];
        for (int i = 0; i <= val.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    K[i][j] = 0;
                    continue;
                }
                if (j - wt[i - 1] >= 0) {
                    K[i][j] = Math.max(K[i - 1][j], K[i - 1][j - wt[i - 1]] + val[i - 1]);
                } else {
                    K[i][j] = K[i - 1][j];
                }
            }
        }
        return K[val.length][W];
    }

    //    https://www.lintcode.com/problem/backpack-ii/
    public int backPackII(int m, int[] A, int[] V) {
        int[] f = new int[m + 1];
        for (int i = 0; i <= m; ++i)
            f[i] = 0;
        int n = A.length, i, j;
        for (i = 0; i < n; i++) {
            for (j = m; j >= A[i]; j--) {
                if (f[j] < f[j - A[i]] + V[i]) {
                    f[j] = f[j - A[i]] + V[i];
                }
            }
        }
        return f[m];
    }

    public int backPackUnBounded(int m, int[] A) {
        if (m == 0 || A == null) {
            return 0;
        }

        int[] dp = new int[m + 1];

        // in 0-1 knapsack we check max with previous row item, here since we can take multiple of
        // same item, we can take from same row itself, so only 1-D array
        // we take the item A[i]+ we take another item in same row after reducing weight A[i] => dp[j-A[i]]
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], A[i] + dp[j - A[i]]);
            }
        }

        return dp[m];
    }
}