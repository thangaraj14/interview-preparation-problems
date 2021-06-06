package dsa;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/prison-cells-after-n-days/
 */

class PrisonAfterNDays {

    public static int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0 || N <= 0) {
            return cells;
        }
        boolean hasCycle = false;
        int cycle = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int[] next = nextDay(cells);
            String key = Arrays.toString(next);
            if (!set.contains(key)) { //store cell state
                set.add(key);
                cycle++;
            } else { //hit a cycle
                hasCycle = true;
                break;
            }
            cells = next;
        }
        if (hasCycle) {
            // if no.of.days is 10 and if cycle is there at 3rd loop, then we have to execute the loop for remaining
            // one which is 3+3+3 +remaining
            N %= cycle;
            for (int i = 0; i < N; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    private static int[] nextDay(int[] cells) {
        int[] tmp = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            tmp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return tmp;
    }

    public static void main(String[] args) {
        int[] cells = { 0, 1, 0, 1, 1, 0, 0, 1 };
        int N = 50;
        System.out.println(Arrays.toString(prisonAfterNDays(cells, N)));
    }
}