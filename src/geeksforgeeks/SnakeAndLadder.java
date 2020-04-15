package geeksforgeeks;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 * <p>
 * https://www.geeksforgeeks.org/snake-ladder-problem-2/
 */
public class SnakeAndLadder {

    static class Entry {
        int v;// Vertex number
        int dist;// Distance of this vertex from source
    }

    // This function returns minimum number of dice
    // throws required to Reach last cell from 0'th cell
    // in a snake and ladder game. move[] is an array of
    // size N where N is no. of cells on board If there
    // is no snake or ladder from cell i, then move[i]
    // is -1 Otherwise move[i] contains cell to which
    // snake or ladder at i takes to.
    static int getMinDiceThrows(int move[], int n) {
        int visited[] = new int[n];
        Queue<Entry> q = new ArrayDeque<>();
        Entry entry = new Entry();
        entry.v = 0;
        entry.dist = 0;

        // Mark the node 0 as visited and enqueue it.
        visited[0] = 1;
        q.add(entry);

        // Do a BFS starting from vertex at index 0
        while (!q.isEmpty()) {
            entry = q.remove();
            int v = entry.v;

            // If front vertex is the destination
            // vertex, we are done
            if (v == n - 1) {
                break;
            }

            // Otherwise dequeue the front vertex and
            // enqueue its adjacent vertices (or cell
            // numbers reachable through a dice throw)
            for (int j = v + 1; j <= (v + 6) && j < n; ++j) {
                // If this cell is already visited, then ignore
                if (visited[j] == 0) {
                    // Otherwise calculate its distance and
                    // mark it as visited
                    Entry a = new Entry();
                    a.dist = (entry.dist + 1);
                    visited[j] = 1;

                    // Check if there a snake or ladder at 'j'
                    // then tail of snake or top of ladder
                    // become the adjacent of 'i'
                    if (move[j] != -1) {
                        a.v = move[j];
                    } else {
                        a.v = j;
                    }
                    q.add(a);
                }
            }
        }

        // We reach here when 'entry' has last vertex
        // return the distance of vertex in 'entry'
        return entry.dist;
    }

    public static void main(String[] args) {
        // Let us construct the board given in above diagram
        int N = 30;
        int moves[] = new int[N];
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