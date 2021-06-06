package dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class PrintKNodesDistanceTree {

    Node root;

    public static void main(String[] args) {
        PrintKNodesDistanceTree tree = new PrintKNodesDistanceTree();

        tree.root = new Node(3);
        tree.root.left = new Node(5);
        tree.root.right = new Node(1);
        tree.root.left.left = new Node(6);
        tree.root.left.right = new Node(2);
        tree.root.right.left = new Node(0);
        tree.root.right.right = new Node(8);
        tree.root.left.right.left = new Node(7);
        tree.root.left.right.right = new Node(4);
        Node target = tree.root.left;
        List<Integer> list = tree.printKDistanceNode(tree.root, target, 2);
        list.forEach(System.out::println);
    }

    private List<Integer> printKDistanceNode(Node root, Node target, int K) {
        if (root.left == null && root.right == null) {
            return Collections.emptyList();
        }
        HashMap<Node, Node> cacheMap = new HashMap<>();
        populateCacheMap(cacheMap, root, null);
        Queue<Node> queue = new LinkedList<>();
        queue.add(target);
        Set<Integer> result = new HashSet<>();
        result.add(target.val);
        int currentLayer = 0;
        while (!queue.isEmpty()) {

            if (currentLayer == K) {
                break;
            }

            int layerSize = queue.size();
            for (int i = 0; i < layerSize; i++) {
                Node node = queue.remove();

                if (node.left != null && !result.contains(node.left.val)) {
                    queue.add(node.left);
                    result.add(node.left.val);
                }

                if (node.right != null && !result.contains(node.right.val)) {
                    queue.add(node.right);
                    result.add(node.right.val);
                }

                Node parent = cacheMap.get(node);
                if (parent != null && !result.contains(parent.val)) {
                    queue.add(parent);
                    result.add(parent.val);
                }
            }
            currentLayer++;
        }
        result = new HashSet<>();
        while (!queue.isEmpty())
            result.add(queue.poll().val);
        return new ArrayList<>(result);

    }

    private void populateCacheMap(HashMap<Node, Node> cache, Node root, Node parent) {
        if (root == null) {
            return;
        }

        cache.put(root, parent);

        populateCacheMap(cache, root.left, root);
        populateCacheMap(cache, root.right, root);
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }
}
