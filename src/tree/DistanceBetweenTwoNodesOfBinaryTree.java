package src.problems;

public class DistanceBetweenTwoNodesOfBinaryTree {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node LCA(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }
        if (root.value == n1 || root.value == n2) {
            return root;
        }

        Node left = LCA(root.left, n1, n2);
        Node right = LCA(root.right, n1, n2);

        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return LCA(root.left, n1, n2);
        } else {
            return LCA(root.right, n1, n2);
        }
    }

    // Returns length of key k if it is present in
    // tree, otherwise returns -1
    public static int findLength(Node root, int a, int length) {
        if (root == null) {
            return -1;
        }
        if (root.value == a) {
            return length;
        }
        int left = findLength(root.left, a, length + 1);
        if (left == -1) {
            return findLength(root.right, a, length + 1);
        }
        return left;
    }

    public static int findDistance(Node root, int a, int b) {
        Node lca = LCA(root, a, b);

        int d1 = findLength(lca, a, 0);
        int d2 = findLength(lca, b, 0);

        return d1 + d2;
    }

    public static void main(String[] args) {

        // Let us create binary tree given in
        // the above example
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        System.out.println("Dist(4, 5) = " + findDistance(root, 4, 6));
        /*
         * System.out.println("Dist(4, 6) = " + findDistance(root, 4, 6));
         * System.out.println("Dist(3, 4) = " + findDistance(root, 3, 4));
         * System.out.println("Dist(2, 4) = " + findDistance(root, 2, 4));
         * System.out.println("Dist(8, 5) = " + findDistance(root, 8, 5));
         */

    }
}
