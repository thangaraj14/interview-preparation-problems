package graph.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO revise
 * Idea: An connection is critical if and only if it is not in a cycle.
 * In other words, a connection (u, v) (u is parent of v in the DFS path) is critical if there is no way to reach u from v or the descendants of v.
 *
 *     Use an array 'visitedAt[]' to store the timestamps when each node is visited.
 *     For each node, we will find the smallest 'visitedAt' of all its descendants.
 *     2.1. If the smallest 'visitedAt' < current node's 'visitedAt', the node must be in a cycle since the smallest 'visitedAt' must be the 'visitedAt' of an ancestor of the current node.
 *     2.2. If the smallest 'visitedAt' > the current node's 'visitedAt', the node must not be in a cycle, and the connection from its parent to it is critical.
 *     2.3. If the smallest 'visitedAt' == the current node's 'visitedAt' the node must be in a cycle. However,
 *     If the current node has a valid parent, the connection from its parent to it is critical. For example, node 3 in n = 6 and connections = [[0,1],[1,2],[2,0],[1,3],[3,4],[4,5],[5,3]].
 *     If the current node does not have a valid parent, it's not a node of a critical connection. For example, node 0 in n = 3 and connections = [[0,1], [1,2], [2,0]].
 *
 * Complexity:
 * Time = O(graph) + O(DFS) = O(|E| + |V|) + O(|E| + |V|) = O(|E| + |V|)
 * Space = O(graph) + O(visitedAt) + O(DFS) = O(|E| + |V|) + O(|V|) + O(|V|) = O(|V| + |E|)
 */
public class CriticalConnectionInGraph {
    int startTime=1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if (connections == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> criticalConns = new ArrayList<>();
        dfs(buildGraph(n, connections), new int[n], 0, -1, criticalConns); // -1 is a dummy parent of the server 0
        return criticalConns;
    }

    /**
     * The dfs method is basically trying to find the node with the minimum timestamp the current node is able to reach.
     * By calling the dfs method with all of the current node's neighbors,
     * we get a number of results representing the timestamps the current node can reach and we choose the minimal value from these results.
     * Our purpose is to find out the cycle and the minimal value is used to do so. Taking a step back, if there is a cycle existing in the graph,
     * we will eventually go back to a previous timestamp through the cycle; in another word, we will find a node that can reach to a previous timestamp.
     * When we find such node, we will return the previous timestamp as the result of the dfs method and the caller (the parent node)
     * who is calling the dfs method will get the previous timestamp as well.
     * The minimal value among the results of the dfs invocations for a node's all child nodes will be larger than the previous timestamp
     * until we get back to the beginning of the loop (technically there is no beginning of a loop but the beginning here is referring to the node that firstly detected the cycle).
     * All the connections within a loop are not critical connections; in another word, we just need to return all the connections outside of any loops.
     */
    private int dfs(List<List<Integer>> graph, int[] visitedAt, int server, int parent, List<List<Integer>> criticalConns) {
        if (visitedAt[server] > 0) { // return immediately if a node has been visited before
            return visitedAt[server];
        }

        visitedAt[server] = startTime++;

        int minVisitedAtOfAllNeighbors = Integer.MAX_VALUE;
        for (int neighbor : graph.get(server)) {
            if (neighbor == parent) { // skip parent as we only want to explore down, not up
                continue;
            }

            int neighborVisitedAt = dfs(graph, visitedAt, neighbor, server, criticalConns);
            minVisitedAtOfAllNeighbors = Math.min(minVisitedAtOfAllNeighbors, neighborVisitedAt);
        }

        if (visitedAt[server] <= minVisitedAtOfAllNeighbors && parent != -1) { // parent != 1 to avoid creating invalid critical connection, e.g., [-1, 0]
            criticalConns.add(Arrays.asList(parent, server));
        }

        return Math.min(visitedAt[server], minVisitedAtOfAllNeighbors);
    }

    private List<List<Integer>> buildGraph(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> conn : connections) {
            graph.get(conn.get(0)).add(conn.get(1));
            graph.get(conn.get(1)).add(conn.get(0));
        }

        return graph;
    }
}
