package dsa;

/**
 * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */

class MaximumPathSum {

    Node root;
    int maxValue;

    public int maxPathSum() {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(Node node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.data);
        return Math.max(left, right) + node.data;
    }

    public static void main(String args[]) {
        MaximumPathSum tree = new MaximumPathSum();
        tree.root = new Node(9);
        tree.root.left = new Node(2);
        tree.root.right = new Node(4);
        tree.root.left.left = new Node(-3);
        tree.root.left.right = new Node(-7);
        tree.root.right.right = new Node(-5);
        System.out.println("maximum path sum is : " + tree.maxPathSum());
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}