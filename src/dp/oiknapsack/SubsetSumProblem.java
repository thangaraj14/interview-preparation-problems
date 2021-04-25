package dp.oiknapsack;

import java.util.ArrayList;

/**
 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 */
public class SubsetSumProblem {

    static boolean[][] dp;

    static void display(ArrayList<Integer> v) {
        System.out.println(v);
    }

    static void printAllSubsets(int[] arr, int n, int sum) {
        if (n == 0 || sum < 0) {
            return;
        }

        dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // first row's value is arr[0]. so setting the value as true for only the first index
        // arr[0] is 2, so dp[0][2] = true;
        if (arr[0] <= sum) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < sum + 1; ++j) {
                if (arr[i] <= j) {
                    dp[i][j] = (dp[i - 1][j] || dp[i - 1][j - arr[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        if (!dp[n - 1][sum]) {
            System.out.println("There are no subsets with" + " sum " + sum);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 8, 10 };
        int n = arr.length;
        int sum = 10;
        printAllSubsets(arr, n, sum);
    }
}