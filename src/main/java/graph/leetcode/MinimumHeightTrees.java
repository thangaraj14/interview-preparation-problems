package graph.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Basically my code starts from the leaf nodes.
 *
 * For leaf nodes, their degree = 1, which means each of them is only connected to one node.
 *
 * In our loop, each time we delete the leaf nodes from our graph(just by putting their degrees to 0),
 * and meanwhile we add the new leaf nodes after deleting them to the queue.
 *
 * So basically in the end, the nodes in the queue would be connected to no other nodes but each other. They should be the answer.
 */
public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n <= 0) return res;
        //this is needed...since when there is only 1 vertex... the indegree of it will be 0..this case is not included in the following discussion...
        if (n == 1) {
            res.add(0);
            return res;
        }
        List<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] indegree = new int[n];
        int cnt = n;
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            indegree[i] = graph[i].size();
            if (indegree[i] == 1) {
                leaves.add(i);
            }
        }
        while (cnt > 2) {
            int size = leaves.size();
            cnt -= size;
            for (int i = 0; i < size; i++) {
                int v = leaves.poll();
                for (int w : graph[v]) {
                    indegree[w]--;
                    if (indegree[w] == 1) {
                        leaves.add(w);
                    }
                }
            }
        }
        res.addAll(leaves);
        return res;
    }
}
