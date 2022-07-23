package graph.leetcode;

public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int M = edges.length;
        for (int i = 0; i < M; i++) {
            int p = edges[i][0], q = edges[i][1];
            if (uf.find(p) == uf.find(q)) {
                return false;
            }
            uf.union(p, q);
        }

        return uf.count == 1;
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int n) {
            if (parent[n] == n) return n;
            parent[n] = find(parent[n]);
            return parent[n];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
