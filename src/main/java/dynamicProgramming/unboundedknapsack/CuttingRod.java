package dynamicProgramming.unboundedknapsack;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-cost-to-cut-a-stick
 * https://www.youtube.com/watch?v=xwomavsC86c
 */
public class CuttingRod {

    public int minCost(int n, int[] cuts) {
        int len = cuts.length + 2;

        int[] endpoints = new int[len];
        endpoints[0] = 0;
        for (int i = 1; i < len - 1; i++) endpoints[i] = cuts[i - 1];
        endpoints[len - 1] = n;
        Arrays.sort(endpoints);


        int[][] dp = new int[len][len];

        // d : dist between i & j, the starting & ending position of stick
        for (int d = 2; d < len; d++) {
            for (int i = 0, j = i + d; j < len; i++, j++) {
                dp[i][j] = Integer.MAX_VALUE;

                int curr = endpoints[j] - endpoints[i];
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + curr);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * The algorithm is quite Brute Force, we would try to generate all possible permutations of cuts,
     * and would try to know what permutation would lead to best result, i.e. minimize our cost for cutting.
     * <p>
     * Let us suppose we are currently having a wood piece from index l to index r (i.e. the length of the wood is r - l, indexing is done as illustrated in the problem).
     * Now, we try every possible cut that we could perform in the range from l to r.
     * <p>
     * Since a cut (let's say, cut is at i index) results in our original piece to further split into 2 parts (one from [l, i], and second from [i, r]).
     * Also, lets suppose the minimum cost of cutting, the segment [l, i] is minLeft and similarly for [i, r] is minRight.
     * Hence the cost to cut the rod segment [l, r] would be cost_i = minLeft + minRight + (r - l) (r - l is the cost to perform the cut at i).
     * Similarly, a cut at j index would cost in total, say, cost_j, similarly at k be cost_k and so on...
     * <p>
     * The minimum cost to cut the rod from index l to r hence would be min(cost_i, cost_j, cost_k, ...).
     */
    public int minCostRecursive(int n, int[] cuts) {
        Integer[][] dp = new Integer[101][101];
        int[] cutsWithLengthOfRodAppendedToEnds = Arrays.copyOf(cuts, cuts.length + 2);
        cutsWithLengthOfRodAppendedToEnds[cutsWithLengthOfRodAppendedToEnds.length - 1] = n;
        Arrays.sort(cutsWithLengthOfRodAppendedToEnds);
        return recursionHelper(cutsWithLengthOfRodAppendedToEnds, 1, cutsWithLengthOfRodAppendedToEnds.length - 2, dp);
    }

    public int recursionHelper(int[] cutsWithLengthOfRod, int i, int j, Integer[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int min = Integer.MAX_VALUE;

        for (int mid = i; mid <= j; mid++) {

            int cost = cutsWithLengthOfRod[j + 1] - cutsWithLengthOfRod[i - 1] + recursionHelper(cutsWithLengthOfRod, i, mid - 1, dp) +
                    recursionHelper(cutsWithLengthOfRod, mid + 1, j, dp);
            min = Math.min(cost, min);
        }

        return dp[i][j] = min;

    }

    public static void main(String args[]) {
        CuttingRod cr = new CuttingRod();
        int[] price = {1, 5, 3, 6};
        System.out.println(cr.minCost(9, price));
    }
}