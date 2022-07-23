package graph.leetcode;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 */
public class GraphBiPartite {
    public boolean isBipartite(int[][] graph) {
        Integer[] color = new Integer[graph.length];

        for (int i = 0; i < graph.length; i++) {   //This graph might be a disconnected graph. So check each unvisited node.
            if (color[i] == null && !dfs(graph, color, 1, i)) {
                return false;
            }
        }

        return true;
    }

    public boolean dfs(int[][] graph, Integer[] colors, int curColor, int node) {
        colors[node] = curColor;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == null ){
                if(!dfs(graph, colors, -curColor, neighbor))  // If this node hasn't been colored, Color it with a different color
                    return false;
            } else if (colors[neighbor] == curColor) {
                return false;
            }
        }
        return true;
    }
}