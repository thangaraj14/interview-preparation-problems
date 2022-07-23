package graph.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 */
public class CourseScheduleII {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> map = new HashMap<>(); // Courses that depend on the key
        int[] indegrees = new int[numCourses]; //  # of prerequisites for course i
        Queue<Integer> queue = new ArrayDeque<>(); // Used to find dependants and decrease their outdegree

        for (int[] pre : prerequisites) {
            map.getOrDefault(pre[1], new ArrayList<>()).add(pre[0]);
            indegrees[pre[0]]++;
        }

        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (indegrees[temp] == 0) {
                count++; // if cond for duplicates
            }
            if (!map.containsKey(temp)) {
                continue;
            }
            for (int i : map.get(temp)) {
                if (--indegrees[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        return count == numCourses;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int[] prerequisite : prerequisites) {
            int dest = prerequisite[0];
            int src = prerequisite[1];
            adjList.computeIfAbsent(src, x -> new ArrayList<>()).add(dest);
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        return i == numCourses ? topologicalOrder : new int[0];
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[] indegree = new int[numCourses];
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Map<Integer, Set<Integer>> prerequisitesMap = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            prerequisitesMap.put(i, new HashSet<>());
            adj.put(i, new HashSet<>());
        }

        for (int[] pre : prerequisites) {
            int dst = pre[0];
            int src = pre[1];

            indegree[dst]++;
            adj.get(src).add(dst);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            Set<Integer> adjset = adj.get(node);
            for (int depcrs : adjset) {
                prerequisitesMap.get(depcrs).add(node);
                prerequisitesMap.get(depcrs).addAll(prerequisitesMap.get(node));
                indegree[depcrs]--;
                if (indegree[depcrs] == 0) {
                    q.offer(depcrs);
                }
            }
        }

        List<Boolean> rslt = new ArrayList<>();
        for (int[] qry : queries) {
            Set<Integer> pset = prerequisitesMap.get(qry[0]);
            if (pset.contains(qry[1])) {
                rslt.add(true);
            } else {
                rslt.add(false);
            }
        }
        return rslt;
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

        ArrayList<Integer>[] adjList = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] ints : prerequisites) {
            adjList[ints[0]].add(ints[1]);
        }
        int[] color = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (color[i] != 2 && dfs(adjList, i, color)) return false;
        }
        return true;

    }

    public boolean dfs(ArrayList<Integer>[] al, int curr, int[] color) {
        if (color[curr] == 1) return true;
        color[curr] = 1;
        for (int x : al[curr]) if (color[x] != 2 && dfs(al, x, color)) return true;
        color[curr] = 2;
        return false;
    }


    // this is to get the order of course as output
    public boolean dfs(List<Integer>[] adjList, int[] visited, List<Integer> result, int node) {
        if (visited[node] == 1) return false;
        if (visited[node] == 2) return true;

        visited[node] = 1;
        for (int adj : adjList[node]) {
            if (!dfs(adjList, visited, result, adj)) {
                return false;
            }
        }
        visited[node] = 2;
        result.add(node); // this will keep track of which to fininsh first and last
        return true;
    }
}