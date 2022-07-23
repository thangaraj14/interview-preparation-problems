package graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/867806/q3-online-microsoft-interview-finding-rome
 */
public class RoadsToRome {

    public static int findRome(int[] A, int[] B) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int indegree[] = new int[A.length + 1];
        Queue<Integer> queue = new LinkedList<>();

        int outdegree[] = new int[A.length + 1];

        for (int i = 0; i < A.length; i++) {
            List<Integer> list = adjList.getOrDefault(A[i], new ArrayList<>());
            list.add(B[i]);
            adjList.put(A[i], list);
            indegree[B[i]]++;
            outdegree[A[i]]++;
        }

        for (int i = 0; i < A.length + 1; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }
        int last = -1;
        int secondLast = -1;
        while (!queue.isEmpty()) {
            int city = queue.remove();
            if (last == -1)
                last = city;
            else {
                secondLast = last;
                last = city;
            }
            for (int neighbour : adjList.getOrDefault(city, new ArrayList<>())) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0)
                    queue.add(neighbour);
            }

        }
        if (outdegree[secondLast] == 0 && outdegree[last] == 0)
            return -1;
        return last;
    }

    public static void main(String[] args) {
        findRome(new int[]{1,2,3},new int[]{0,0,0});
    }
}
