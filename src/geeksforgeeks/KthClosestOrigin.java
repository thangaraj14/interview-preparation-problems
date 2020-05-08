package geeksforgeeks;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/solution/
 */
class KthClosestOrigin {

    static int[][] points = new int[3][2];

    // quick select
    public int[][] kClosest(int[][] points, int K) {
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
        int oi = i;
        int pivot = dist(i);
        i++;

        while (true) {
            while (i < j && dist(i) < pivot)
                i++;
            while (i <= j && dist(j) > pivot)
                j--;
            if (i >= j) {
                break;
            }
            swap(i, j);
        }
        swap(oi, j);
        return j;
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

    public int[][] kClosestOLogN(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = distOLogN(points[i]);

        Arrays.sort(dists);
        int distK = dists[K - 1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (distOLogN(points[i]) <= distK) {
                ans[t++] = points[i];
            }
        return ans;
    }

    public int distOLogN(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public static void main(String[] args) {
        KthClosestOrigin kth = new KthClosestOrigin();
        points[0][0] = 3;
        points[0][1] = 3;

        points[1][0] = 5;
        points[1][1] = -1;

        points[2][0] = 2;
        points[2][1] = 4;

        // System.out.println(Arrays.deepToString(kth.kClosestOLogN(points, 1)));
        System.out.println(Arrays.deepToString(kth.kClosest(points, 2)));
    }
}
