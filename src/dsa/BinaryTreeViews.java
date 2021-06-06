package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BinaryTreeViews {
    static Node root;

    class NodeObj {
        Node node;
        int level;

        public NodeObj(int level, Node node) {
            this.level = level;
            this.node = node;
        }
    }

    /**
     * first occurrence of the key in hash map
     */
    private void topView(Node root) {
        if (root == null) {
            return;
        }

        Queue<NodeObj> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        int hd = 0;
        queue.add(new NodeObj(hd, root));
        while (!queue.isEmpty()) {
            NodeObj removed = queue.remove();

            if (!map.containsKey(removed.level)) {
                map.put(removed.level, removed.node);
            }

            if (removed.node.left != null) {
                queue.add(new NodeObj(removed.level - 1, removed.node.left));
            }

            if (removed.node.right != null) {
                queue.add(new NodeObj(removed.level + 1, removed.node.right));
            }
        }

        for (Map.Entry<Integer, Node> mapEntry : map.entrySet()) {
            System.out.print(mapEntry.getValue().data + " - ");
        }

        map.entrySet().stream().forEach(e -> System.out.println(e.getValue()));
    }

    /**
     * first occurrence of level order traversal
     */
    private void leftView(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 1; i <= n; i++) {
                Node node = queue.remove();
                if (i == 1) {
                    System.out.print(node.data + " ");
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    /**
     * last occurrence of level order traversal
     */
    private void rightView(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 1; i <= n; i++) {
                Node removed = queue.remove();

                if (i == n) {
                    System.out.print(removed.data + " ");
                }

                if (removed.left != null) {
                    queue.add(removed.left);
                }
                if (removed.right != null) {
                    queue.add(removed.right);
                }
            }
        }
    }

    /**
     * last occurrence of the value for each key
     */
    private void bottomView(Node root) {
        if (root == null) {
            return;
        }

        Queue<NodeObj> queue = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();
        int hd = 0;
        queue.add(new NodeObj(hd, root));
        while (!queue.isEmpty()) {
            NodeObj removed = queue.remove();
            map.put(removed.level, removed.node);

            if (removed.node.left != null) {
                queue.add(new NodeObj(removed.level - 1, removed.node.left));
            }

            if (removed.node.right != null) {
                queue.add(new NodeObj(removed.level + 1, removed.node.right));
            }
        }

        for (Map.Entry<Integer, Node> mapEntrySet : map.entrySet())
            System.out.print(mapEntrySet.getValue().data + " ");
    }

    /**
     * @param root https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
     */
    void diagonalView(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node temp = q.remove();
            if (temp == null) {
                if (!q.isEmpty()) {
                    q.add(null);
                }
                System.out.println();
            } else {
                while (temp != null) {
                    System.out.print(temp.data + " ");
                    if (temp.left != null) {
                        q.add(temp.left);
                    }
                    temp = temp.right;
                }
            }
        }
    }

    // Recursive function to do a pre-order traversal of the tree and
    // fill the map with diagonal elements
    public static void printDiagonal(Node node, int diagonal, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }

        if (!map.containsKey(diagonal)) {
            map.put(diagonal, new ArrayList<>());
        }
        map.get(diagonal).add(node.data);

        printDiagonal(node.left, diagonal + 1, map);
        printDiagonal(node.right, diagonal, map);
    }

    public static void printDiagonal(Node root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        printDiagonal(root, 0, map);
        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
        }
    }

    public static void main(String[] args) {
        BinaryTreeViews tree = new BinaryTreeViews();
        root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);

        System.out.println("The top view of binary tree : ");
        tree.topView(root);
        System.out.println("\nThe left view of binary tree : ");
        tree.leftView(root);
        System.out.println("\nThe right view of binary tree : ");
        tree.rightView(root);
        System.out.println("\nThe bottom view of binary tree : ");
        tree.bottomView(root);
        System.out.println("\nThe diagonal view of binary tree : ");
        tree.diagonalView(root);
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int val) {
            this.data = val;
        }
    }
}
