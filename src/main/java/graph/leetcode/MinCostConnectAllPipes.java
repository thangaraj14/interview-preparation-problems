package graph.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinCostConnectAllPipes {
    List<int[]> graphEdges;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        graphEdges = new ArrayList<>();
        addEdges(n, wells, pipes);
        return minCostKruskals(n);
    }

    /// select edges based on Kruskals - with least edge node to be added first
    private int minCostKruskals(int n) {
        /// Sort edges in increasing order of edge value
        graphEdges.sort(Comparator.comparingInt(a -> a[2]));
        int minCost = 0, processedEdges = 0;

        DSU dsu = new DSU(n + 1);
        for (int[] edge : graphEdges) {
            /// check if this can be merged (in this case:`union`) to the existing graph component
            if (dsu.union(edge[0], edge[1])) {
                processedEdges++;
                minCost += edge[2];
                /// Break processing once all edges are processed
                if (processedEdges == n) break;
            }
        }
        return minCost;
    }

    private void addEdges(int n, int[] wells, int[][] pipes) {
        // build an imaginary edge between node 0 to all other houses so that
        // even the wells are converted into edges. Now it's a proper MST problem
        for (int i = 0; i < n; i++) {
            graphEdges.add(new int[]{0, i + 1, wells[i]});
        }

        Collections.addAll(graphEdges, pipes);
    }

    /// Disjoint Set Union Implementation
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
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