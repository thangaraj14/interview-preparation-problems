package graph.leetcode;

import javafx.util.Pair;

import java.util.*;
/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];

            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new Pair<>(travelTime, dest));
        }


        int[] signalReceivedAt = new int[N + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);
        //distance, node into pq
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] - b[0]));

        pq.add(new int[]{0, K});

        signalReceivedAt[K] = 0;
        Set<Integer> visited = new HashSet<>();
        /**
         *
         Pop the top node currNode from the priority queue.
         Traverse all outgoing edges connected to currNode.
         Add the adjacent node neighborNode to the priority queue
         only if the current path takes less time than the value at signalReceivedAt[neighborNode].
         Update the time at signalReceivedAt[neighborNode] to current path time.
         */
        while(!pq.isEmpty()){
            int[] cur = pq.remove();

            int currNode = cur[1];
            int currNodeTime = cur[0];

            if (visited.contains(currNode)) { continue; }
            if (currNodeTime > signalReceivedAt[currNode]) {
                continue;
            }
            visited.add(currNode);
            if(adj.containsKey(currNode)){
                for(Pair<Integer,Integer> next : adj.get(currNode)){
                    int time = next.getKey();
                    int neighborNode = next.getValue();
                    if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                        signalReceivedAt[neighborNode] = currNodeTime + time;
                        pq.add(new int[]{signalReceivedAt[neighborNode], neighborNode});
                    }
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public int networkDelayTime_BF(int[][] times, int N, int K) {
        double[] disTo = new double[N+1];
        Arrays.fill(disTo, Double.POSITIVE_INFINITY);
        disTo[K - 1] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                disTo[v] = Math.min(disTo[v], disTo[u] + w);
            }
        }
        double res = Double.MIN_VALUE;
        for (double i: disTo) {
            res = Math.max(i, res);
        }
        return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
    }

}

