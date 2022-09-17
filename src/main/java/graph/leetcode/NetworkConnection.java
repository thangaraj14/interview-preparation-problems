package graph.leetcode;

/**
 * https://leetcode.com/problems/number-of-operations-to-make-network-connected
 *
 *
 Perform union find during the traversal of the connections; deduct the number of components when merging any two components;
 If there are less than n - 1 connections, no way to make the network connected;
 Otherwise need number of components - 1 operations for the network to be fully connected.

 */
public class NetworkConnection {

    public int makeConnected(int n, int[][] connections) {

        UnionFind uf = new UnionFind(n);

        for (int[] connection : connections) {
            uf.union(connection[0], connection[1]);
        }

        return connections.length < n - 1 ? -1 : uf.components - 1;

    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int components = 0;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
            this.components = size;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
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
    }
}
