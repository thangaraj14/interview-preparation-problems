package practiceproblems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Pair> minQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        for (int i = 0; i < matrix[0].length; i++) {
            minQueue.offer(new Pair(matrix[0][i], 0, i));
        }
        k--;
        while (k-- > 0 && !minQueue.isEmpty()) {
            Pair temp = minQueue.poll();
            if (temp.x + 1 >= matrix.length) continue;
            minQueue.offer(new Pair(matrix[temp.x + 1][temp.y], temp.x + 1, temp.y));
        }
        return minQueue.isEmpty() ? -1 : minQueue.peek().value;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 9}, {3, 11, 13}, {4, 13, 15}};
        int k = 4;

        System.out.println(kthSmallest(matrix, k));
    }

    static class Pair {
        int value;
        int x;
        int y;

        public Pair(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return this.value + " ";
        }
    }
}