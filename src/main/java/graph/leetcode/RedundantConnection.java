package graph.leetcode;

/**
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 * 1
 * / \
 * 2 - 3
 * <p>
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {

        UnionFind uf = new UnionFind(edges.length + 1);

        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[]{-1, -1};
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
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
            return true;
        }
    }
}


