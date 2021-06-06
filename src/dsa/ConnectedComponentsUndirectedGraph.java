package dsa;

class ConnectedComponentsUndirectedGraph {

    public static int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;
        for (int[] e : edges) {
            int p1 = findParent(parent, e[0]);
            int p2 = findParent(parent, e[1]);
            if (p1 != p2) {
                parent[p1] = p2; // Union 2 component
                components--;
            }
        }
        return components;
    }

    private static int findParent(int[] parent, int i) {
        if (i == parent[i]) {
            return i;
        }
        parent[i] = findParent(parent, parent[i]); // Path compression
        return parent[i];
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] arr = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        countComponents(n, arr);
    }
}