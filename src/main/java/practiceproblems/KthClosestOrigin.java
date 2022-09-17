package practiceproblems;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/solution/
 */
class KthClosestOrigin {

    public int[][] kClosestPQ(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }


    public int[][] kClosest(int[][] points, int k) {
        if (k == points.length) {
            return points;
        }

        int low = 0;
        int high = points.length - 1;
        int pivotIndex = partition(points, low, high);

        while (pivotIndex != k) {
            if (k < pivotIndex) {
                high = pivotIndex - 1;
            } else {
                low = pivotIndex + 1;
            }

            pivotIndex = partition(points, low, high);
        }

        return Arrays.copyOfRange(points, 0, k);
    }

    private int partition(final int[][] points, final int low, final int high) {
        if (low >= high) {
            return low;
        }

        swap(points, low + new Random().nextInt(high - low), high);

        int leftIndex = low;
        int rightIndex = high - 1;

        double pivotDistance = distance(points[high]);

        while (leftIndex <= rightIndex) {
            if (distance(points[leftIndex]) < pivotDistance) {
                leftIndex += 1;
            } else {
                swap(points, leftIndex, rightIndex);
                rightIndex -= 1;
            }
        }

        swap(points, leftIndex, high);

        return leftIndex;
    }

    private void swap(final int[][] points, final int index1, final int index2) {
        final int[] temp = points[index1];

        points[index1] = points[index2];
        points[index2] = temp;
    }

    private double distance(final int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);
    }
}
