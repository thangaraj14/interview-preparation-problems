package geeksforgeeks;

import java.util.Queue;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakeAndLadder {

    static class QEntry {
        int vertex;
        int noOfMoves;
    }

    static int getMinDiceThrows(int board[], int n) {
        Queue<QEntry> queue = new LinkedList<>();
        QEntry qEntry = new QEntry();
        qEntry.vertex = 0;
        qEntry.noOfMoves = 0;

        board[0] = -21;
        queue.add(qEntry);

        while (!queue.isEmpty()) {
            qEntry = queue.remove();
            int v = qEntry.vertex;

            System.out.println(v);

            if (v == n - 1) {
                break;
            }

            for (int j = v + 1; j <= (v + 6) && j < n; j++) {
                if (board[j] != -21) {
                    QEntry entry = new QEntry();
                    entry.noOfMoves = (qEntry.noOfMoves + 1);

                    if (board[j] != -1) {
                        entry.vertex = board[j];
                    } else {
                        entry.vertex = j;
                    }
                    queue.add(entry);
                    board[j] = -21;
                }
            }
        }
        return qEntry.noOfMoves;
    }

    public static void main(String[] args) {

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
        moves[3] = 1;
        moves[23] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println("Min Dice throws required is " + getMinDiceThrows(moves, N));
    }
}