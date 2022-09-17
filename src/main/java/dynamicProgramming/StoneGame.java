package dynamicProgramming;

/**
 * Pots of Gold
 * https://leetcode.com/problems/stone-game
 *
 * Player    A               B               A
 *
 *                                        - pick index i+2
 *                     - pick index i+1
 *                                        - pick index j
 *     - pick index i
 *                                        - pick index i
 *                     - pick index j
 *                                        - pick index j-1
 *
 *                                        - pick index i+2
 *                     - pick index i
 *                                        - pick index j-1
 *     - pick index j
 *                                        - pick index i
 *                     - pick index j-1
 *                                        - pick index j-2
 *
 *                                        For example, stones = [5, 3, 4, 5]
 *
 * If A picks left most stone 5, remain stones are [3, 4, 5].
 *
 * B with choices to take left most stone 3 or right most stone 5,
 * and B's best choice is to take right most stone 5 because 5 > 3 & 5 > 4.
 * Then A has choice to make among remaining stones [3, 4].
 *
 * From above decisions, what A can get for next to next round?
 * If B takes left most stone 3, A's selectable range are [4, 5]
 * If B takes right most stone 5, A's selectable range are [3, 4]
 *
 * And since B make choice to maximum B's score,
 * choices A can have is not max of([4, 5], [3, 4]) which is [4, 5], but min([4, 5], [3, 4]), which is [3, 4].
 *
 * This is a little complicated,
 * but since B also wants to get maximum score, remaining choices for A is not max but min.
 *
 * Use memo for recursion, memo[i][j] means maximum score can get form index i ~j.
 * The condition for A to win is to make sure either of one condition follows:
 *      memo[0][size-1] > max(memo[1][size-1], memo[0][size-2]).
 */
public class StoneGame {

    Integer[][] cache;

    public boolean stoneGame(int[] piles) {
        cache = new Integer[piles.length + 2][piles.length + 2];
        int totalSum = 0;
        for (int pile : piles) {
            totalSum += pile;
        }

        return dfsHelper(piles, 0, piles.length - 1) > totalSum / 2;
    }

    public int dfsHelper(int[] piles, int startIndex, int endIndex) {
        if (startIndex >= piles.length || endIndex < 0) return 0;

        if (cache[startIndex][endIndex] != null) return cache[startIndex][endIndex];

        int maxStonesPickedFromStart= piles[startIndex] + Math.min(
                dfsHelper(piles,startIndex+2,endIndex),
                dfsHelper(piles,startIndex+1,endIndex-1)
        );

        int maxStonesPickedFromEnd = piles[endIndex] + Math.min(
                dfsHelper(piles,startIndex,endIndex-2),
                dfsHelper(piles,startIndex+1,endIndex-1)
        );

        return Math.max(maxStonesPickedFromEnd,maxStonesPickedFromStart);
    }
}
