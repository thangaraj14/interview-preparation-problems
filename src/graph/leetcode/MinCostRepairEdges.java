package graph.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.
 * <p>
 * Input:
 * <p>
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes connected by an edge.
 * edgesToRepair, a list where each element is a triplet representing the pair of nodes
 * between which an edge is currently broken and the cost of repearing that edge,
 * respectively (e.g. [1, 2, 12] means to repair an edge between nodes 1 and 2, the cost would be 12).
 */
public class MinCostRepairEdges {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        int[][] edgesToRepair = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
        System.out.println(new MinCostRepairEdges().minCostToRepair(n, edges, edgesToRepair));
    }


    public int minCostToRepair(int n, int[][] edges, int[][] edgesToRepair) {
        Set<String> hset = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int[] e : edgesToRepair) {
            pq.add(e);
            hset.add(e[0] + " " + e[1]);
        }

        for (int[] e : edges) {
            String s = e[0] + " " + e[1];
            if (hset.contains(s)) continue;
            int[] ne = new int[]{e[0], e[1], 0};
            pq.add(ne);
        }

        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int sum = 0;
        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int u = e[0], v = e[1], w = e[2];
            int up = find(u, parent), vp = find(v, parent);
            if (up != vp) {
                union(up, vp, parent);
                sum += w;
            }
        }

        System.out.println(sum + " result");
        return sum;
    }

    public static void union(int x, int y, int[] parent) {
        int xroot = find(x, parent);
        int yroot = find(y, parent);
        parent[yroot] = xroot;
    }

    public static int find(int x, int[] parent) {
        if (parent[x] < 0) {
            return x;
        }
        int p = find(parent[x], parent);
        parent[x] = p;
        return p;
    }
}


