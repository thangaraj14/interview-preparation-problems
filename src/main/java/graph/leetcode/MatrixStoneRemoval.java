package graph.leetcode;

/**
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column
 */
public class MatrixStoneRemoval {

    public int removeStones(int[][] stones) {

        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                // if any two points are on the same column or row, they are connected as a
                // edge.
                // find connected component, and remove all but one.
                // count the number of disjoint components.
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    uf.union(i, j);
                }
            }
        }

        /**
         * For each island, there will be only 1 stone left after removing the stones on the same island.
         * So the left stone after removing all stones is equal to the number of islands,
         * and the maximum removed stone number (Note: maximum) will be equal to #stone - #islands.
         *
         * Why it is the maximum? Considering an island with connecting stone a-b-c.
         * If you remove a and c, the maximum count you can remove is 2. However, if you remove 2 first, the remove count will be only 1.
         */
        return n - uf.components;
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
