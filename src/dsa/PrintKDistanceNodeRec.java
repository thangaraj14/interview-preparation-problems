package dsa;

class PrintKDistanceNodeRec {
    Node root;

    void printkdistanceNodeDown(Node node, int k) {

        if (node == null || k < 0) {
            return;
        }

        if (k == 0) {
            System.out.print(node.data + "down");
            System.out.println("");
            return;
        }

        printkdistanceNodeDown(node.left, k - 1);
        printkdistanceNodeDown(node.right, k - 1);
    }

    int printkdistanceNode(Node node, Node target, int k) {

        if (node == null) {
            return -1;
        }

        if (node == target) {
            printkdistanceNodeDown(node, k);
            return 0;
        }

        int dl = printkdistanceNode(node.left, target, k);

        if (dl != -1) {

            if (dl + 1 == k) {
                System.out.print(node.data);
                System.out.println("");
            } else {
                printkdistanceNodeDown(node.right, k - dl - 2);
            }

            return 1 + dl;
        }

        // MIRROR OF ABOVE CODE FOR RIGHT SUBTREE
        // Note that we reach here only when node was not found in left
        // subtree
        int dr = printkdistanceNode(node.right, target, k);
        if (dr != -1) {
            if (dr + 1 == k) {
                System.out.print(node.data);
                System.out.println("");
            } else {
                printkdistanceNodeDown(node.left, k - dr - 2);
            }
            return 1 + dr;
        }

        // If target was neither present in left nor in right subtree
        return -1;
    }

    public static void main(String args[]) {
        PrintKDistanceNodeRec tree = new PrintKDistanceNodeRec();

        tree.root = new Node(2);
        tree.root.left = new Node(7);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(22);
        tree.root.left.right = new Node(6);
        tree.root.left.right.left = new Node(5);
        tree.root.left.right.right = new Node(11);
        tree.root.right.right = new Node(9);
        tree.root.right.right.left = new Node(4);
        Node target = tree.root.left.right;
        tree.printkdistanceNode(tree.root, target, 1);
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