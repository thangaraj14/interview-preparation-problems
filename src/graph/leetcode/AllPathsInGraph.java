package graph.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
 * graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */
public class AllPathsInGraph {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean[] visited=new boolean[graph.length];
        List<Integer> path=new ArrayList<Integer>();
        List<List<Integer>> ans=new  ArrayList<List<Integer>>();
        visited[0]=true;
        path.add(0);
        dfs(graph,visited,0,path, ans);
        return ans;
    }
    public void dfs(int[][] graph,boolean[] visited,int u,List<Integer> path,  List<List<Integer>> ans){
        if(u==graph.length-1){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<graph[u].length;i++){
            int v=graph[u][i];
            ///if(!visited[v]){
            visited[v]=true;
            path.add(v);
            dfs(graph,visited,v,path, ans);
            visited[v]=false;
            path.remove(path.size()-1);//}
        }
    }
}
