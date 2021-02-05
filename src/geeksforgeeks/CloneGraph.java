package geeksforgeeks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/clone-graph/
 */
/*
// Definition for a Node.
class Node {
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
*/
public class CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        map.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node neighbors : current.neighbors) {
                if (!map.containsKey(neighbors)) {
                    Node neighborClone = new Node(neighbors.val);
                    map.put(neighbors, neighborClone);
                    queue.offer(neighbors);
                }
                map.get(current).neighbors.add(map.get(neighbors));
            }
        }
        return map.get(node);
    }
}