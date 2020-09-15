package graph.leetcode;

import java.util.*;
/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {

        Map<Integer, List<int[]>> adjacencyMap = new HashMap<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int[] row : times) {
            // creating adjacency list where row[0] is start node, row[1] is destNode and row[2] is distance
            List<int[]> adjList = adjacencyMap.getOrDefault(row[0], new ArrayList<>());

            adjList.add(new int[]{row[1], row[2]});

            adjacencyMap.put(row[0], adjList);
        }
        // general set to keep track of visited nodes
        Set<Integer> visited = new HashSet<>();

        // K is the node where we have to begin
        pq.offer(new int[]{K, 0});

        // this will track min distance to reach for current node
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // since we are starting at K, marking it's distance as 0
        distance[K] = 0;
        distance[0] = 0;
        int max = 0;
        while (!pq.isEmpty()) {
            int[] value = pq.poll();

            int node = value[0];
            int distStartNode = value[1];

            if (!visited.add(node)) continue;

            max = distStartNode;
            N--;
            if (!adjacencyMap.containsKey(node)) continue;
            // going to traverse it's adjacent nodes
            for (int[] adjList : adjacencyMap.get(node)) {

                int destNode = adjList[0];
                int distanceToDestination = adjList[1];

                int newDist = Math.min(distanceToDestination + distStartNode, distance[destNode]);
                distance[destNode] = newDist;

                if (!visited.contains(destNode)) {
                    pq.offer(new int[]{adjList[0], newDist});
                }
            }
        }

        return N == 0 ? max : -1;
    }

}

