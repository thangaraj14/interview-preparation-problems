package practiceproblems;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/solution/
 */
class KthClosestOrigin {
    int[][] points = new int[3][2];

    // quick select
    public int[][] kClosest(int[][] points, int K) {
        this.points= points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if (i >= j) {
            return;
        }
        int k = new Random().nextInt(j - i + 1) + i;
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength) {
            sort(i, mid - 1, K);
        } else if (K > leftLength) {
            sort(mid + 1, j, K - leftLength);
        }
    }

    public int partition(int i, int j) {
        int pivot = dist(i);
        swap(i, j);
        int iLow = i;
        for (int i1 = i; i1 < j; i1++) {
            if (dist(i1) <= pivot) {
                swap(i1, iLow);
                iLow++;
            }
        }
        swap(iLow, j);
        return iLow;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
    public static void main(String[] args) {
//        KthClosestOrigin kth = new KthClosestOrigin();
//        points[0][0] = 3;
//        points[0][1] = 3;
//
//        points[1][0] = 5;
//        points[1][1] = -1;
//
//        points[2][0] = 2;
//        points[2][1] = 4;
//
//        // System.out.println(Arrays.deepToString(kth.kClosestOLogN(points, 1)));
//        System.out.println(Arrays.deepToString(kth.kClosest(points, 2)));
    }
}
