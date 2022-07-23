package graph.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points
 */
public class MinCostConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }

        edges.sort(Comparator.comparingInt(e -> e.dist));

        DSU dsu = new DSU(points.length);
        int minCost = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.p1, edge.p2)) {
                minCost += edge.dist;
            }
            if (dsu.componentSize(edge.p1) == points.length) {
                return minCost;
            }
        }

        return minCost;
    }

    static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
            rank = new int[N];
            Arrays.fill(rank, 1);
        }

        public int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int xp = find(x) ;
            int yp = find(y);

            if (xp == yp) return false;

            if (rank[xp] < rank[yp]) {
                int t = xp;
                xp = yp;
                yp = t;
            }

            parent[yp] = xp;
            rank[xp] += rank[yp];
            return true;
        }

        public int componentSize(int x) {
            return rank[find(x)];
        }
    }

    static class Edge {
        int p1;
        int p2;
        int dist;

        public Edge(int x, int y, int dist) {
            this.p1 = x;
            this.p2 = y;
            this.dist = dist;
        }
    }
}
