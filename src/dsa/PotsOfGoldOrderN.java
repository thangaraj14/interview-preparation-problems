package dsa;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://www.mimuw.edu.pl/~idziaszek/termity/termity.pdf
 *
 * @author Andre
 */
public class PotsOfGoldOrderN {// implements Player {

    private final String name;

    public PotsOfGoldOrderN(String name) {
        this.name = name;
    }

    /**
     * Fuses together coins x, M, y, if M >= x and M >= y. This is applied to the whole array. The function's output
     * is either an increasing array, a decreasing array, or a bitonic array. In all cases, we could then use a greedy
     * strategy of just picking the largest of the coins at either end to determine the value of the game.
     *
     * @param coins
     * @param l     - left index, inclusive
     * @param r     - right index, inclusive
     *
     * @return
     */
    int[] fuse(int[] coins, int l, int r) {

        if (r - l < 2) {
            return Arrays.copyOf(coins, coins.length);
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = l; i <= r; i++) {
            stack.push(coins[i]);

            while (stack.size() >= 3) {
                Integer y = stack.pop();
                Integer M = stack.pop();
                Integer x = stack.pop();

                if (x <= M && y <= M) {
                    stack.push(x - M + y); // fuse coins and continue with the while loop

                } else {
                    stack.push(x);
                    stack.push(M);
                    stack.push(y);
                    break; // out of the while loop
                }
            }
        }

        return toArray(stack); // copy into an array[], filling it out backwards
    }

    /**
     * just copy into an array[], filling it out backwards
     *
     * @param stack
     *
     * @return
     */
    int[] toArray(Deque<Integer> stack) {
        int[] fused = new int[stack.size()];
        int i = fused.length - 1;
        while (!stack.isEmpty()) {
            fused[i--] = stack.pop();
        }
        return fused;
    }

    //    @Override
    public boolean takeLeft(int[] coins, int l, int r) {
        int[] fused = fuse(coins, l, r);
        return fused[0] > fused[fused.length - 1];
    }

    public String getName() {
        return this.name;
    }

    public static void main(String[] args) {
        //		int[] coins = {9, 7, 8, 2, 1, 3, 20, 8, 19};
        int[] coins = { 20, 30, 2, 3, 4, 10, 234, 345, 12, 24, 45, 34, 1, 3, 6, 87, 26, 45, 89, 89, 23, 52, 63, 87, 80,
                43, 22, 12, 45, 12, 1, 3 };
        PotsOfGoldOrderN player = new PotsOfGoldOrderN("Alice");
        System.out.println(Arrays.toString(player.fuse(coins, 0, coins.length - 1)));

    }
}