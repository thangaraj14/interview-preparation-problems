package graph.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts
 * highways[i] = [city1i, city2i, tolli]
 *
 * Input: n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
 * Output: 9
 * Explanation:
 * Go from 0 to 1 for a cost of 4.
 * Go from 1 to 4 and use a discount for a cost of 11 / 2 = 5.
 * The minimum cost to go from 0 to 4 is 4 + 5 = 9.
 */
public class ConnectCitiesWithDiscount {

    /**
     * Dijkstra is used to solve single source, positive weight and minimum path questions like this.
     * The only trick here is to use 2D visited array to check and do the pruning work when the next city is put into the PriorityQueue,
     * one for city and one for the amount of discounts used.
     * The principle of Dijkstra Algorithm determines the fact that the first time the destination is reached, it will be the optimal solution.
     */
    public int minimumCost(int n, int[][] highways, int discounts) {

        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] x : highways) {
            int cityA = x[0], cityB = x[1], cost = x[2];
            graph[cityA].add(new int[]{cityB, cost});
            graph[cityB].add(new int[]{cityA, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[]{0, 0, 0}); // {cost, node, discount}
        boolean[] visited = new boolean[n];

        /**
         *     # Because of how djikstra works, when we reach this node the first time,
         * 		# we will reach there with the lowest cost.  However, we may reach this node
         * 		# again with a highest cost, but more discount tickets, which can lead to a
         * 		# more optimal soln at the end.  If we ever come back to this node with the same or
         * 		# fewer discounts, the soln is not optimal.
         */
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], city = cur[1], dis = cur[2];

		   /* We dont have to push it back into the heap, we have already found the shortest path to this city.
		    this holds true as we storing the cost from source in the minHeap, this would not be valid if we are  just storing the curr cost
		   */
            visited[city] = true;

            if (city == n - 1) return cost;

            for (int[] x : graph[city]) {
                int next = x[0], weight = x[1];
                if (!visited[next]) {
                    pq.offer(new int[]{cost + weight, next, dis});
                    if (dis < discounts) {
                        pq.offer(new int[]{cost + weight / 2, next, dis + 1});
                    }
                }
            }
        }
        return -1;
    }
}
