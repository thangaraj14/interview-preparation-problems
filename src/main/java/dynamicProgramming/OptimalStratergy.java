package dynamicProgramming;

import java.util.Arrays;

/**
 * http://www.glassdoor.com/Interview/N-pots-each-with-some-number-of-gold-coins-are-arranged-in-a-line-You-are-playing-a-game-against-another-player-You-tak-QTN_350584.htm
 * <p>
 * https://www.techiedelight.com/pots-gold-game-dynamic-programming/
 */
public class OptimalStratergy {
    // Function to maximize the number of coins collected by a player,
    // assuming that opponent also plays optimally
    public static int optimalStrategy(int[] coin, int i, int j,
                                      int[][] lookup) {
        // base case: one pot left, only one choice possible
        if (i == j) {
            return coin[i];
        }

        // if we're left with only two pots, choose one with maximum coins
        if (i + 1 == j) {
            return Integer.max(coin[i], coin[j]);
        }

        // if sub-problem is seen for the first time, solve it and
        // store its result in a lookup table
        if (lookup[i][j] == 0) {
            // if player chooses front coin i, opponent is left to choose
            // from [i+1, j].
            // 1. if opponent chooses front coin i+1, recur for [i+2, j]
            // 2. if opponent chooses rear coin j, recur for [i+1, j-1]

            int start = coin[i] + Integer.min(optimalStrategy(coin, i + 2,
                            j, lookup),
                    optimalStrategy(coin, i + 1, j - 1, lookup));

            // if player chooses rear coin j, opponent is left to choose
            // from [i, j-1].
            // 1. if opponent chooses front coin i, recur for [i+1, j-1]
            // 2. if opponent chooses rear coin j-1, recur for [i, j-2]

            int end = coin[j] + Integer.min(optimalStrategy(coin, i + 1,
                            j - 1, lookup),
                    optimalStrategy(coin, i, j - 2, lookup));

            // assign maximum of two choices
            lookup[i][j] = Integer.max(start, end);
        }

        // return the subproblem solution from the map
        return lookup[i][j];
    }

    // main function
    public static void main(String[] args) {
        // pots of gold arranged in a line
        int[] coin = {4, 6, 2, 3};

        // Create a table to store solutions of subproblems
        int[][] lookup = new int[coin.length][coin.length];

        System.out.println("Maximum coins collected by player is "
                + optimalStrategy(coin, 0, coin.length - 1, lookup));
    }

    public boolean PredictTheWinner(int[] nums) {
        Integer[][] dp = new Integer[nums.length + 1][nums.length + 1];
        int playerA = recursionHelper(nums, 0, nums.length - 1, dp);
        int playerB = Arrays.stream(nums).sum() - playerA;

        return playerA >= playerB;
    }

    public int recursionHelper(int[] nums, int i, int j, Integer[][] dp) {

        if (i > j) return 0;
        if (i == j) return nums[i];
        if (dp[i][j] != null) return dp[i][j];

        int takeFront = nums[i] + Math.min(recursionHelper(nums, i + 2, j, dp), recursionHelper(nums, i + 1, j - 1, dp));
        int takeBack = nums[j] + Math.min(recursionHelper(nums, i + 1, j - 1, dp), recursionHelper(nums, i, j - 2, dp));
        return dp[i][j] = Math.max(takeFront, takeBack);
    }

    public boolean PredictTheWinnerBottomUp(int[] nums) {
        int n = nums.length;

        /*
        dp[i][j] -> score of the first player for picks between nums[i..j]
        */
        int[][] dp = new int[n][n];

        // total of nums
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                /*
                First player has option to choose i or j
                If he chooses i then 2nd player to choose in (i+1, j)
                    - if 2nd player chooses i+1, then player 1 will next choose from (i+2,j)
                    - if 2nd player chooses j, then player 1 will next choose from (i+1,j-1)
                If he chooses j then 2nd player to choose in (i, j-1)
                    - if 2nd player chooses i, then player 1 will next choose from (i+1,j-1)
                    - if 2nd player chooses j-1, then player 1 will next choose from (i,j-2)

                We know that player 2 would have played wisely and player 1 will get the the minimum in the next move.
                We choose the best(Max) of the above 2 scenarios
                */
                int a = (i + 1 < n && j - 1 >= 0) ? dp[i + 1][j - 1] : 0;
                int b = (i + 2 < n) ? dp[i + 2][j] : 0;
                int c = (j - 2 >= 0) ? dp[i][j - 2] : 0;

                dp[i][j] = Math.max(nums[i] + Math.min(a, b), nums[j] + Math.min(a, c));
            }
        }

        /*
        dp[0][n-1] will have the score for the first player.
        */
        int player1Score = dp[0][n - 1];
        int player2Score = total - player1Score;
        return player1Score >= player2Score;
    }
}