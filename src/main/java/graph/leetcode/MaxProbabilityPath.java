package graph.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/path-with-maximum-probability
 */
public class MaxProbabilityPath {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Create the weighted undirected graph (adjacency list).
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();  // It's better to create a separate class instead of using Pair<> but this is leetcode.
        for (int i = 0; i < n; ++i) graph.put(i, new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            graph.get(edges[i][0]).add(new Pair<>(edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Pair<>(edges[i][0], succProb[i]));
        }
        // Dijkstra to find max probability path from start to end node.
        Double[] costs = new Double[n];
        // Order from greatest to lowest probability.
        PriorityQueue<Pair<Integer, Double>> queue = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));
        queue.add(new Pair<>(start, 1.0));    // Initially we are at the start node with a probability of 1.
        while (!queue.isEmpty()) {
            Pair<Integer, Double> temp = queue.remove();
            int currNode = temp.getKey();
            double currProb = temp.getValue();

            if (costs[currNode] != null) continue;
            costs[currNode] = currProb;
            if (currNode == end) break;

            for (Pair<Integer, Double> adjacentNode : graph.get(currNode)) {
                int adjNode = adjacentNode.getKey();
                double adjProb = adjacentNode.getValue();
                if (costs[adjNode] == null) {
                    queue.add(new Pair<>(adjNode, currProb * adjProb));
                }
            }
        }
        return costs[end] == null ? 0 : costs[end];
    }
}
