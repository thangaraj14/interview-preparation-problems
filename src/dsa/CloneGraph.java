package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        return clone(node);
    }

    public Node clone(Node node) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }

        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(newNode.val, newNode);
        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(clone(neighbor));
        return newNode;
    }
}