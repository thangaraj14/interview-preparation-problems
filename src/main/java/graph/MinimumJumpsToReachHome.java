package graph;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/minimum-jumps-to-reach-home/description/
public class MinimumJumpsToReachHome {
    static class Pair {
        int pos;
        boolean dir;
        public Pair(int pos, boolean dir) {
            this.pos = pos;
            this.dir = dir;
        }
    }
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int limit = 2000 + 2 * b + 1; // could hardcode a value grater than given in the limit of the problem
        boolean[] visited = new boolean[limit];
        for (int num: forbidden) {
            visited[num] = true;
        }
        int step = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, false));
        visited[0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                int pos = p.pos;
                boolean dir = p.dir;

                if (pos == x) return step;

                if (!dir) {
                    int backward = pos - b;

                    if (backward > 0 && !visited[backward]) {
                        q.offer(new Pair(backward, true));
                        visited[backward] = true;
                    }
                }

                int forward = pos + a;

                if (forward < limit && !visited[forward]) {
                    q.offer(new Pair(forward, false));
                    visited[forward] = true;
                }


            }
            step++;
        }
        return -1;
    }
}
