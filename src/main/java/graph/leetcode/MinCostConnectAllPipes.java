package graph.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinCostConnectAllPipes {

    /**
     * I take it this way:
     * We cannot build any well.
     * There is one and only one hidding well in my house (house 0).
     * The cost to lay pipe between house[i] and my house is wells[i].
     * In order to supply water to the whole village,
     * we need to make the village a connected graph.
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        UnionFind uf = new UnionFind(n + 1);

        List<int[]> edges = new ArrayList<>();
        // build an imaginary edge between node 0 to all other houses so that
        // even the wells are converted into edges. Now it's a proper MST problem
        for (int i = 0; i < n; i++) {
            edges.add(new int[]{0, i + 1, wells[i]});
        }

        Collections.addAll(edges, pipes);

        /// Sort edges in increasing order of edge value
        edges.sort(Comparator.comparingInt(a -> a[2]));

        int res = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (uf.find(x) == uf.find(y)) {
                continue;
            }
            uf.union(x, y);
            res += edge[2];
        }

        return res;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int n) {
            if (parent[n] == n) return n;
            parent[n] = find(parent[n]);
            return parent[n];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            /// same root - no need to merge (since both are part of same tree)
            if (rootX == rootY) return false;

            /// merge if they have different root
            if (parent[rootX] > parent[rootY]) {
                parent[rootY] = rootX;
            } else if (parent[rootX] < parent[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
}