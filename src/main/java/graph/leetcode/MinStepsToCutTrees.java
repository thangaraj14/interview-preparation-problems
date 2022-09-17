package graph.leetcode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/cut-off-trees-for-golf-event
 */
public class MinStepsToCutTrees {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        if (m == 0) return 0;
        int n = forest.get(0).size();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int h = forest.get(i).get(j);
                if (h > 1) {
                    pq.offer(new Node(i, j, h));
                }
            }
        }

        Node source = new Node(0, 0, 0);
        Node target = null;
        int res = 0;
        while (!pq.isEmpty()) {
            target = pq.poll();
            int distance = getMinDistance(forest, source, target);
            if (distance == -1) return -1;
            else res += distance;
            source = target;
        }
        return res;
    }

    int getMinDistance(List<List<Integer>> forest, Node source, Node target) {
        int m = forest.size(), n = forest.get(0).size();
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        q.offer(source);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (node.equals(target)) {
                    return step;
                }
                for (int[] row : dir) {
                    int x = node.x + row[0];
                    int y = node.y + row[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || forest.get(x).get(y) == 0) {
                        continue;
                    }
                    Node newNode = new Node(x, y, forest.get(x).get(y));
                    if (!visited.contains(newNode)) {
                        visited.add(newNode);
                        q.offer(newNode);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    class Node {
        int x, y;
        int height;

        Node(int xx, int yy, int hh) {
            x = xx;
            y = yy;
            height = hh;
        }

        public int hashCode() {
            return x * 31 + y;
        }

        public boolean equals(Object obj) {
            Node other = (Node) obj;
            return (other.x == x) && (other.y == y);
        }
    }
}
