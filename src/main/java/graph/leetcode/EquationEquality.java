package graph.leetcode;

/**
 * https://leetcode.com/problems/satisfiability-of-equality-equations/
 * <p>
 * Intuition:
 * We have 26 nodes in the graph.
 * All "==" equations actually represent the connection in the graph.
 * The connected nodes should be in the same color/union/set.
 * <p>
 * Then we check all inequations.
 * Two inequal nodes should be in the different color/union/set.
 * <p>
 * Explanation
 * We can solve this problem by DFS or Union Find.
 * <p>
 * Firt pass all "==" equations.
 * Union equal letters together
 * Now we know which letters must equal to the others.
 * <p>
 * Second pass all "!=" inequations,
 * Check if there are any contradict happens.
 */
public class EquationEquality {

    public boolean equationsPossible(String[] equations) {

        UnionFind uf = new UnionFind(26);

        for (String equation : equations) {
            if (equation.charAt(1) == '=' && equation.charAt(2) == '=') {
                uf.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (uf.isSameComponent(equation.charAt(0) - 'a', equation.charAt(3) - 'a')) {
                    return false;
                }
            }
        }

        return true;
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

        public boolean isSameComponent(int p, int q) {
            return find(p) == find(q);
        }
    }
}