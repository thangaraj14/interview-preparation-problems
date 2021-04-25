package practiceproblems;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors
 *
 * For simplicity sake, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val = 1, the second node with val = 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 *
 * Adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1.
 * You must return the copy of the given node as a reference to the cloned graph.
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
        if(node==null) return node;
        
        Map<Node,Node> map= new HashMap<>();
        
        Queue<Node> queue= new ArrayDeque<>();
        queue.offer(node);
        map.put(node, new Node(node.val));
        
        while(!queue.isEmpty()){
            Node current= queue.poll();
            
            for(Node neighbors: current.neighbors){
                if(!map.containsKey(neighbors)){
                    Node neighborClone= new Node(neighbors.val);
                    map.put(neighbors,neighborClone);
                    queue.offer(neighbors);
                }

                map.get(current).neighbors.add(map.get(neighbors));
            }
        }
        
        return map.get(node);
       
    }
    

}