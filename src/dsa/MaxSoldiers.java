package dsa;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 */
public class MaxSoldiers {

    class Pair {
        int row;
        int soldiers;

        Pair(int row, int soldiers) {
            this.row = row;
            this.soldiers = soldiers;
        }
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[k];
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> a.soldiers == b.soldiers ?
                Integer.compare(a.row, b.row) :
                Integer.compare(a.soldiers, b.soldiers));
        int i = 0;
        for (int[] rows : mat) {
            int temp = binarySearchUtil(rows);
            queue.offer(new Pair(i, temp));
            i++;
        }
        int ind = 0;
        while (ind < k) {
            result[ind++] = queue.poll().row;
        }
        return result;

    }

    public int binarySearchUtil(int[] row) {
        int lo = 0;
        int hi = row.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (row[mid] == 1) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
