package dsa;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 * <p>
 * https://www.geeksforgeeks.org/snake-ladder-problem-2/
 */
public class SnakeAndLadder {

    static class Entry {
        int v;
        int dist;
    }

    static int getMinDiceThrows(int[] move, int n) {
        int[] visited = new int[n];
        Queue<Entry> q = new ArrayDeque<>();
        Entry entry = new Entry();
        entry.v = 0;
        entry.dist = 0;

        visited[0] = 1;
        q.add(entry);

        while (!q.isEmpty()) {
            entry = q.remove();
            int v = entry.v;

            if (v == n - 1) {
                break;
            }

            for (int j = v + 1; j <= (v + 6) && j < n; j++) {
                if (visited[j] == 0) {
                    Entry a = new Entry();
                    a.dist = (entry.dist + 1);
                    visited[j] = 1;

                    if (move[j] != -1) {
                        a.v = move[j];
                    } else {
                        a.v = j;
                    }
                    q.add(a);
                }
            }
        }
        return entry.dist;
    }

    public static void main(String[] args) {
        int N = 30;
        int[] moves = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println("Min Dice throws required is " + getMinDiceThrows(moves, N));
    }

    //leetcode
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int i = n - 1, j = 0, index = 0, inc = 1;
        while (index < n * n) {
            arr[index++] = board[i][j];
            if (inc == 1 && j == n - 1) {
                inc = -1;
                i--;
            } else if (inc == -1 && j == 0) {
                inc = 1;
                i--;
            } else {
                j += inc;
            }
        }
        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new ArrayDeque<>();
        int start = arr[0] > -1 ? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                if (cur == n * n - 1) {
                    return step;
                }
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
                    int dest = arr[next] > -1 ? arr[next] - 1 : next;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}