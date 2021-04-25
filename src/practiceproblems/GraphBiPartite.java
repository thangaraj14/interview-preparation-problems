package practiceproblems;

/**
 * Recall that a graph is bipartite if we can split it's set of nodes into 
 * two independent subsets A and B such that every edge in the graph has one node in A and 
 * another node in B.
 * Input: [[1,3], [0,2], [1,3], [0,2]] => 0th node connects to 1,3 ..
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 */
public class GraphBiPartite {
    public boolean isBipartite(int[][] graph) {
        int[] color= new int[graph.length];
       
        for(int i=0;i<graph.length;i++){   //This graph might be a disconnected graph. So check each unvisited node.
            
            if(color[i]==0 && !dfs(graph, color, 1, i)){
                return false;
            }
        }
        return true;
    }
    
    public boolean dfs(int[][] graph, int[] color, int curColor, int node){
        if(color[node]!=0){
            return color[node]==curColor;// If it is colored and its color is different, return false;
        }
        color[node]=curColor; 
        
        for(int neighbor: graph[node]){
            if(!dfs(graph,color,-curColor, neighbor)){  // If this node hasn't been colored, Color it with a different color;
                return false;
            }
        }
        return true;
    }
}