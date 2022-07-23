package graph.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FindTheCity {

    /**
     * This problem is path finding with positive weighted edges,
     * so Dijkstras immediately pops into mind. Dijkstras is a smallest path finding algorithm that works on graphs with positive weights.
     * Dijkstras is a SSSP (Single Source Shortest Path) algorithm,
     * so that means we will need to perform Dijkstras on every single vertex and treat it as the start because every city is a potential answer.
     *
     *
     Build the graph using the edges input
     Iterate over all the vertices and perform Dijkstras on that vertex as the start node
     Dijkstra will tell you how many cities are <= distanceThreshold
     Keep a count of the smallest count

     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, List<int[]>> g = buildGraph(n ,edges);
        int result = 0, reachableCities = n;
        for(int city = 0; city < n; city++){
            int[] costs = dijsktra(city, g, n);

            int count = 0;
            for(int cost : costs){
                if(cost <= distanceThreshold){
                    count++;
                }
            }
            if(count <= reachableCities){
                result = city;
                reachableCities = count;
            }
        }
        return result;
    }
    int[] dijsktra(int sourceCity, Map<Integer, List<int[]>> g, int n){
        int[] costs = new int[n];
        Arrays.fill(costs, 10001);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        costs[sourceCity] = 0;
        queue.add(new int[]{0,sourceCity});
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            int currentCity = temp[1];
            int currTime = temp[0];

            if(costs[currentCity]<currTime) continue;
            List<int[]> neighbours = g.get(currentCity);
            for(int[] c: neighbours){
                int toCity = c[0], distance = c[1];
                if(costs[toCity] > currTime + distance){
                    costs[toCity] = currTime + distance;
                    queue.add(new int[]{costs[toCity],toCity});
                }
            }
        }
        return costs;
    }

    Map<Integer, List<int[]>> buildGraph(int totalCities, int[][] edges){
        Map<Integer, List<int[]>> g = new HashMap<>();

        for(int city = 0; city < totalCities; city++){
            g.put(city, new ArrayList<>());
        }

        for(int[] e : edges){
            int cityA = e[0], cityB = e[1], distance = e[2];
            g.get(cityA).add(new int[]{cityB, distance});
            g.get(cityB).add(new int[]{cityA, distance});
        }
        return g;
    }

    public int findTheCityFloydWarshal(int n, int[][] edges, int distanceThreshold) {

        int[][] dis = new int[n][n];
        int res = 0, smallest = n;

        for (int[] row : dis)
            Arrays.fill(row, 10001);

        for (int[] e : edges){
            int cityA = e[0];
            int cityB = e[1];
            int cost = e[2];

            dis[cityA][cityB] = cost;
            dis[cityB][cityA] = cost;
        }


        for (int i = 0; i < n; ++i)
            dis[i][i] = 0;


        for (int k = 0; k < n; ++k)
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);


        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; ++j)
                if (dis[i][j] <= distanceThreshold)
                    ++count;
            if (count <= smallest) {
                res = i;
                smallest = count;
            }
        }
        return res;
    }

}
