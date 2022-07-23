package dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/
 * <p>
 * https://www.youtube.com/watch?v=TaZxJt4-FHk
 * <p>
 * For example, we take array [2,3,3,2,5]. We partition it into two sets [2,3,2] and [3,5], with sum 7 and 8 respectively.
 * If we have collision with numbers from set 1 with those of set 2, only 7~8=1 will remain.
 * so this becomes subset sum with min difference
 * <p>
 * So the problem asks us to accumulate stones such the result is minimum,
 * but it really is this problem in disguise, divide the array into two subsets such that their sum is minimum. Why?
 * Suppose given array is [1,5,3,2] and you go about solving the problem as given you would take the following steps right?
 * <p>
 * 5 - 3 = 2
 * 2 - 2 = 0 (or) 2 - (5 - 3)
 * 1 - 0 = 1 (or) 1 - (2 - (5 - 3)) => sum{1,5} - sum{2,3}
 * So do you see why we want the minimum difference between two subsets?(this is just intuition not exact mathematical proof)
 */
public class LastStoneWeight {
    static Map<String, Integer> cache = new HashMap<>();

    public static int lastStoneWeightIIRecur(int[] stones) {

        int ans = recursionHelper(stones, 0, 0, 0);
        cache.forEach((key, value) -> System.out.print(key + "  :: " + value + ""));
        return ans;
    }

    public static int recursionHelper(int[] stones, int index, int sum1, int sum2) {
        if (index == stones.length) {
            return Math.abs(sum1 - sum2);
        }
        String uniqueKey = index+"-"+sum1 + "-" + sum2;

        if (cache.containsKey(uniqueKey)) {
            return cache.get(uniqueKey);
        }
        int leftPath = recursionHelper(stones, index + 1, sum1 + stones[index], sum2);
        int rightPath = recursionHelper(stones, index + 1, sum1, sum2 + stones[index]);

        cache.put(uniqueKey, Math.min(leftPath, rightPath));
        return cache.get(uniqueKey);

    }

    public static void main(String[] args) {
        lastStoneWeightII2D(new int[]{31,26,33,21,40});
    }

    public static int lastStoneWeightII(int[] stones) {

        int total = 0;
        for (int stone : stones) total += stone;
        int S2 = 0;
        int target = total/2;
        boolean[] dp = new boolean[target + 1];  // use only 1D array to store the status
        dp[0] = true;
        for (int s : stones) {
            boolean[] cur = dp.clone();
            for (int i = s; i <= target; i++) {  // wont affect the value that is smaller than current stone's weight
                if (dp[i - s]) { // checks if the subset sum is there if i=9 and s=7 at 9th pos true entry
                    // will be present because the 9-7=2 is there, like wise it checks for all combinations
                    cur[i] = true;
                    S2 = Math.max(S2, i);
                }
            }
            dp = cur;
        }
        return total - 2 * S2;
    }
    public static int lastStoneWeightII2D(int[] stones) {
        int n = stones.length, sum = 0;
        for (int s : stones) sum += s;
        int target = sum/2;

        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= n; i++) {
            int currStone = stones[i-1];
            for (int j = 0; j <= target; j++) {
                if (j >= currStone) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - currStone] + currStone);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("Sum is :: "+sum);
        for(int []d:dp){
            System.out.println(Arrays.toString(d));
        }
        System.out.println(sum +" - "+ 2 * dp[n][target]);

        /** dp[n][target] -> this is closest we can partition to sum/2
         * //notice that in general I can say that
         * //answer = S1-S2
         * //where S1 is sum of some numbers and S2 is sum of rest of numbers
         * //also note that S1+S2 = SUM (sum of all numbers)
         * //S1 >= S2 because negative answer is not possible
         * //now we have to minimise answer
         * //answer = (SUM-S2)-S2 = > SUM-2*S2 (Just substituting S1 by SUM-S2)
         */
        return sum - 2 * dp[n][target];
    }
}
