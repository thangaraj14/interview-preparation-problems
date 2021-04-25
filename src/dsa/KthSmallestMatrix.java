package dsa;

import java.util.PriorityQueue;

/*https://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/*/
public class KthSmallestMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Points> pq = new PriorityQueue<>();
        // add first row elements
        for (int j = 0; j <= n - 1; j++)
            pq.offer(new Points(0, j, matrix[0][j]));
        for (int i = 0; i < k - 1; i++) {
            Points t = pq.poll();
            System.out.println(t.x);
            if (t.x == n - 1) {
                continue;
            }
            pq.offer(new Points(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 9 }, { 3, 11, 13 }, { 4, 13, 15 } };
        int k = 4;

        System.out.println(kthSmallest(matrix, k));
    }
}

class Points implements Comparable<Points> {
    int x;
    int y;
    int val;

    public Points(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Points that) {
        return this.val - that.val;
    }

    public String toString() {
        return this.val + "";
    }
}