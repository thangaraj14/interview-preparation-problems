package graph.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MinCostConnectCities {
    public int minimumCost(int n, int[][] connections) {
        // Check edge case, we need at least n - 1 connections to
        // connect all cities
        if (connections.length < n - 1) {
            return -1;
        }
        // We need to first sort by the cost to ensure every time we join
        // we are picking the minimum cost (Greedy Approach)
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));

        int result = 0;

        UnionFind uf = new UnionFind(n);

        // Since the connections are sorted with cost from min to max
        // Every single time if we found we can union one connection,
        // we add that optimal cost to our totalCost
        // Whenever there is only one component left, a.k.a all cities are connected.
        // then we are done, just return the totalCost.
        for (int[] connection : connections) {
            // Remember to subtract 1 since the cities are 1-indexed while our
            // Uf class is 0-indexed.
            if (uf.union(connection[0]-1, connection[1]-1)) {
                result += connection[2];
            }

            if (uf.components == 1) return result;
        }

        return -1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int components;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.components = n ;
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public boolean union(int x, int y) {
            int xRank = find(x), yRank = find(y);
            if (xRank == yRank) {
                return false;
            } else if (rank[xRank] < rank[yRank]) {
                parent[xRank] = yRank;
            } else if (rank[xRank] > rank[yRank]) {
                parent[yRank] = xRank;
            } else {
                parent[yRank] = xRank;
                rank[xRank]++;
            }
            components--;
            return true;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}
