package graph.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {
    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> cloned = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        Node clonedNode = new Node(node.val);
        cloned.put(node, clonedNode);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node nb : curr.neighbors) {
                if (!cloned.containsKey(nb)) {
                    queue.offer(nb);
                    cloned.put(nb, new Node(nb.val));
                }
                cloned.get(curr).neighbors.add(cloned.get(nb));
            }
        }
        return clonedNode;

    }


}