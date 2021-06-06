package dsa;

/**
 * http://www.glassdoor.com/Interview/N-pots-each-with-some-number-of-gold-coins-are-arranged-in-a-line-You-are-playing-a-game-against-another-player-You-tak-QTN_350584.htm
 *
 * <p>
 * https://www.techiedelight.com/pots-gold-game-dynamic-programming/
 */
public class OptimalStrategy {
    // Function to maximize the number of coins collected by a player,
    // assuming that opponent also plays optimally
    //6, 1, 4, 9, 8, 5
    public static int optimalStrategy(int[] coin, int i, int j, int[][] lookup) {
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

            int start = coin[i] + Math.min(optimalStrategy(coin, i + 2, j, lookup),
                    optimalStrategy(coin, i + 1, j - 1, lookup));

            // if player chooses rear coin j, opponent is left to choose
            // from [i, j-1].
            // 1. if opponent chooses front coin i, recur for [i+1, j-1]
            // 2. if opponent chooses rear coin j-1, recur for [i, j-2]

            int end = coin[j] + Math.min(optimalStrategy(coin, i + 1, j - 1, lookup),
                    optimalStrategy(coin, i, j - 2, lookup));

            // assign maximum of two choices
            lookup[i][j] = Math.max(start, end);
        }

        return lookup[i][j];
    }

    public static void main(String[] args) {
        // pots of gold arranged in a line
        int[] coin = { 6, 1, 4, 9, 8, 5 };

        int[][] lookup = new int[coin.length][coin.length];

        System.out.println("Maximum coins collected by player is " + optimalStrategy(coin, 0, coin.length - 1, lookup));
    }
}