package graph.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/**
 * Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 */
class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> map = new HashMap<>(); // Courses that depend on the key
        int[] degrees = new int[numCourses]; //  # of prerequisites for course i
        Queue<Integer> queue = new ArrayDeque<>(); // Used to find dependants and decrease their outdegree

        for (int[] pre : prerequisites) {
            List<Integer> tempList = map.getOrDefault(pre[1], new ArrayList<>());
            tempList.add(pre[0]);
            degrees[pre[0]]++;
            map.put(pre[1], tempList);
        }

        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (degrees[temp] == 0) {
                count++; // if cond for duplicates
            }
            if (!map.containsKey(temp)) {
                continue;
            }
            for (int i : map.get(temp)) {
                if (--degrees[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        return count == numCourses;
    }

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // this method basically finds a back-edge between nodes
        // backedge is when doing a node(A)'s dfs, it puts A to a temp state
        // while traversing A's child, if any of child dosen't have anymore child it's marked as completed
        // if there are children it put's the current child to temp state and visits it's children
        // so when doing a dfs for a node if it encounters a temp state node rather than completed node
        // then that means there's a cycle we cannot complete the course
        //   (T)   A \
        //        /   / 
        //  (T)  B    /
        //      / \  /
      //  (Co) C    D (T)  while doing DFS for D's components we encounter A, but A is still in temp state
        //
		ArrayList<Integer>[] adjList= new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++){
            adjList[i]= new ArrayList<>();
        }
		for(int[] preReq:prerequisites){
			adjList[preReq[0]].add(preReq[1]);
		}

		int[] visited= new int[numCourses];
		for(int i=0;i<numCourses;i++){

			if(visited[i]==0 && !dfs(adjList, visited, i)){
				return false;
			}
		}
		return true;
	}

	public boolean dfs( ArrayList<Integer>[] adjList, int[] visited, int vertex){
		if(visited[vertex]==1) return false;
		visited[vertex]=1;
		for(int adj: adjList[vertex]){
			if(!dfs(adjList, visited, adj)) return false;
		}
		visited[vertex]=2;
		return true;
    }
    // this is to get the order of course as output
    public boolean dfs( List<Integer>[] adjList, int[] visited, List<Integer>result, int node){
        if(visited[node]==1) return false;
        if(visited[node]==2) return true;
        
        visited[node]=1;
        for(int adj: adjList[node]){
            if(!dfs(adjList, visited, result, adj)){
                return false;
            }
        }
        visited[node]=2;
        result.add(node); // this will keep track of which to fininsh first and last
        return true;
    } 
}