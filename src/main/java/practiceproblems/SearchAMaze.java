package practiceproblems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.techiedelight.com/lee-algorithm-shortest-path-in-a-maze/
 */
public class SearchAMaze {

    private static final int M = 10;
    private static final int N = 10;

    private static final int row[] = {-1, 0, 0, 1};
    private static final int col[] = {0, -1, 1, 0};

    private static boolean isValid(int mat[][], int row, int col) {
        return (row >= 0) && (row < M) && (col >= 0) && (col < N) && mat[row][col] == 1;
    }

    private static void BFS(int mat[][], int srcX, int srcY, int destX, int destY) {

        Queue<MazeNode> q = new LinkedList<>();

        q.add(new MazeNode(srcX, srcY, 0));

        int result = 0;
        while (!q.isEmpty()) {

            int size = q.size();
            for (int i = 0; i < size; i++) {

                MazeNode node = q.poll();

                srcX = node.x;
                srcY = node.y;
                int dist = node.dist;

                if (srcX == destX && srcY == destY) {
                    System.out.print("The shortest path from source to destination " + "has length " + result);
                    return;
                }

                for (int k = 0; k < 4; k++) {
                    if (isValid(mat, srcX + row[k], srcY + col[k])) {
                        mat[srcX][srcY] = 0;
                        MazeNode e = new MazeNode(srcX + row[k], srcY + col[k], dist + 1);
                        q.add(e);
                        System.out.println("Adding to the queue :" + e);
                    }
                }
            }
            result++;
        }

        if (result != Integer.MAX_VALUE) {
            System.out.print("The shortest path from source to destination " + "has length " + result);
        } else {
            System.out.print("Destination can't be reached from source");
        }
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 1, 0, 0, 1}, {1, 0, 1, 1, 1, 0, 1, 1, 0, 1}, {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 1, 0, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 1, 0, 0, 1}};

        BFS(mat, 8, 0, 0, 9);
    }

    static class MazeNode {
        int x, y, dist;

        MazeNode(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public String toString() {
            return "(" + x + "," + y + ") -> " + dist;
        }
    }
}

