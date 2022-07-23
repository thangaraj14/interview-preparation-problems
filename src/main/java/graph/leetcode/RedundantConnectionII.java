package graph.leetcode;

/**
 *
 There are 3 cases for Redundant Connection:

 case 1: two-parent problem such that an error node is with two parents

 1
 / \
 v   v
 2-->3     remove the second parentEdge of the node with two parents

 case 2: cyclic problem such that there is a cycle in the graph

 1
 / ^
 v   \
 2-->3     remove the edge that forms the cycle

 case 3: two-parent and cyclic problem

 1
 / ^
 v   \
 2-->3 <- 4     remove [2, 3] (to explain)

 Explanation for case 3:
 We do union only if it is not the second parentEdge. Why?
 We assume we always remove the second parentEdge.
 If there is still cycle remained - that means we made the wrong choice, that is,
 we should remove the first parentEdge instead.

 If [[1, 2], [2, 3], [4, 3], [3, 1]], [2, 3] comes before [4, 3],
 we remove [4,3], then we union [1, 2], [2, 3], [3, 1], there is still cycle -- so we should remove [2, 3].

 If [[1, 2], [4, 3], [2, 3], [3, 1]], [4, 3] comes before [2, 3],
 we remove [2, 3], then we union [1, 2], [4, 3], [3, 1], there is not cycle -- so we should remove [2, 3].


 */
public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int numNodes = edges.length, edgeHasMultipleParent = -1, edgeMakesCycle = -1;
        int[] parent = new int[numNodes + 1];

        for (int i = 0; i < numNodes; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parent[v] != 0) {

                /* Assume we removed the second edge. */
                edgeHasMultipleParent = i;
                break;
            } else
                parent[v] = u;
        }

        UnionFind uf = new UnionFind(numNodes + 1);
        for (int i = 0; i < numNodes; i++) {
            if (i == edgeHasMultipleParent) // without this condition it might give a cyclic condition
                continue;
            int u = edges[i][0];
            int v = edges[i][1];
            if (!uf.union(u, v)) {
                edgeMakesCycle = i;
                break;
            }
        }

        /* Handle with the cyclic problem only. */
        if (edgeHasMultipleParent == -1) {
            return edges[edgeMakesCycle];
        }

        /* Handle with the cyclic problem when we remove the wrong edge. */
        if (edgeMakesCycle != -1) {
            int v = edges[edgeHasMultipleParent][1];
            int u = parent[v];
            return new int[]{u, v};
        }

        /* CHandle with the cyclic problem when we remove the right edge. */
        return edges[edgeHasMultipleParent];
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];

            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public boolean union(int x, int y) {

            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY) return false;

            if (rank[parentX] > rank[parentY]) {
                parent[parentY] = parentX;
            } else if (rank[parentY] > rank[parentX]) {
                parent[parentX] = parentY;
            } else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }

            return true;
        }

        public int find(int x) {
            if (parent[x] == x) return x;

            parent[x] = find(parent[x]);
            return parent[x];
        }
    }
}
