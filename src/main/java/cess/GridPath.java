package cess;

public class GridPath {

    public static boolean[][] onPath = new boolean[9][9];
    public static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[] p = new int[48];

    /**
     * Basic Algorithm

     * The first version of the algorithm does not contain any optimizations. We simply
     * use backtracking to generate all possible paths from the upper-left corner to
     * the lower-right corner and count the number of such paths.

     * Running time: 483 seconds
     * Number of recursive calls: 76 billion
     *
     *
     * Optimization 1
     *
     * If the path reaches the lower-right square before it has visited all other
     * squares of the grid, it is clear that it will not be possible to complete the
     * solution. Using this observation, we can terminate the search immediately if we
     * reach the lower-right square too early.
     *
     * Running time: 119 seconds
     * Number of recursive calls: 20 billion
     *
     *
     * If the path touches a wall and can turn either left or right, the grid splits
     * into two parts that contain unvisited squares. In this case, we cannot visit all
     * squares anymore, so we can terminate the search.
     *
     * Running time: 1.8 seconds
     * Number of recursive calls: 221 million
     *
     *
     * The idea of Optimization 2 can be generalized: if the path cannot continue
     * forward but can turn either left or right, the grid splits into two parts that
     * both contain unvisited squares. It is clear that we cannot visit all squares
     * anymore, so we can terminate the search.
     *
     * Running time: 0.6 seconds
     * Number of recursive calls: 69 million
     *
     *
     * Now is a good moment to stop optimizing the algorithm and see what we have
     * achieved. The running time of the original algorithm was $483$ seconds, and now
     * after the optimizations, the running time is only $0.6$ seconds. Thus, the
     * algorithm became nearly $1000$ times faster after the optimizations.
     *
     * Optimization 4
     *
     * If the path creates a dead end that is not the bottom left corner, either the
     * path will fail to visit all squares (the path may stop at the dead end or pass
     * over it, sealing a square off) or the path will end in the wrong location. Thus,
     * we want to avoid creating dead ends. For example, if the square to the left of
     * our current location is blocked on three sides (including our current location),
     * then the next step must be to the left in order to avoid creating a dead end.
     * After this optimization, the program runs in under $1$ second.
     */
    public int calculateGridPaths(String line) {
        if (line.length() != 48) return -1;

        for (int i = 0; i < p.length; i++) {
            char cur = line.charAt(i);

            if (cur == 'U') p[i] = 0;
            else if (cur == 'R') p[i] = 1;
            else if (cur == 'D') p[i] = 2;
            else if (cur == 'L') p[i] = 3;
            else p[i] = 4;  // cur == '?'
        }

        // set borders of grid
        for (int i = 0; i < 9; i++) {
            onPath[0][i] = true;
            onPath[8][i] = true;
            onPath[i][0] = true;
            onPath[i][8] = true;
        }

        return tryPath(0, 1, 1);
    }

    public static int tryPath(int pathIdx, int curR, int curC) {
        // Optimization 3
        if ((onPath[curR][curC - 1] && onPath[curR][curC + 1]) &&
                (!onPath[curR - 1][curC] && !onPath[curR + 1][curC]))
            return 0;
        if ((onPath[curR - 1][curC] && onPath[curR + 1][curC]) &&
                (!onPath[curR][curC - 1] && !onPath[curR][curC + 1]))
            return 0;

        // Optimization 1
        if (curR == 7 && curC == 1) {           // reached endpoint
            if (pathIdx == p.length) return 1;  // visited every cell -> valid!
            return 0;  // didn't visit every cell (path length is too short)
        }
        // visited all cells, but didn't end up in the correct location
        if (pathIdx == p.length) return 0;

        int ret = 0;  // cumulative count for this "starting position"
        onPath[curR][curC] = true;

        // turn already determined, try going in that direction
        if (p[pathIdx] < 4) {
            int nxtR = curR + dirs[p[pathIdx]][0];
            int nxtC = curC + dirs[p[pathIdx]][1];
            if (!onPath[nxtR][nxtC]) {
                ret += tryPath(pathIdx + 1, nxtR, nxtC);
            }
        }
//        // now search for dead ends (Optimization 4)
//        else if ((curC > 2) && onPath[curR][curC - 2] &&
//                (onPath[curR - 1][curC - 1] || onPath[curR + 1][curC - 1]) &&
//                (!onPath[curR][curC - 1])) {
//            // potential dead end on the left:
//            int nxtR = curR;
//            int nxtC = curC - 1;
//            ret += tryPath(pathIdx + 1, nxtR, nxtC);
//        } else if ((curC < 6) && onPath[curR][curC + 2] &&
//                (onPath[curR - 1][curC + 1] || onPath[curR + 1][curC + 1]) &&
//                (!onPath[curR][curC + 1])) {
//            // potential dead end on the right:
//            int nxtR = curR;
//            int nxtC = curC + 1;
//            ret += tryPath(pathIdx + 1, nxtR, nxtC);
//        } else if ((curR > 2) && onPath[curR - 2][curC] &&
//                onPath[curR - 1][curC - 1] && (!onPath[curR - 1][curC])) {
//            // potential dead end upwards
//            // note: I didn't include all possible scenarios because
//            // it wasn't necessary in order for the program to run in time
//            int nxtR = curR - 1;
//            int nxtC = curC;
//            ret += tryPath(pathIdx + 1, nxtR, nxtC);
//        }
        // iterate through all four possible turns
        else {
            for (int i = 0; i < 4; i++) {
                int nxtR = curR + dirs[i][0];
                int nxtC = curC + dirs[i][1];
                if (onPath[nxtR][nxtC]) continue;
                ret += tryPath(pathIdx + 1, nxtR, nxtC);
            }
        }

        // reset and return
        onPath[curR][curC] = false;
        return ret;
    }
}
