package dsa;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
class DiameterOfTree {

    int diameter;

    public int getDiameter(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getDiameter(root.left);
        int rightHeight = getDiameter(root.right);

        // calculate diameter "through" the current node
        int maxHeight = leftHeight + rightHeight;

        // Update Maximum Diameter (Note that diameter "excluding" the current
        // node in subtree rooted at current node is already updated as we're
        // doing postorder traversal)
        diameter = Math.max(diameter, maxHeight);

        // important - return height of subtree rooted at current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        //        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.right.right = new Node(9);
        root.right.right.right.right = new Node(11);
        root.right.left.left = new Node(7);
        root.right.left.left.left = new Node(13);
        root.right.left.right = new Node(8);

        DiameterOfTree dia = new DiameterOfTree();
        System.out.println(dia.getDiameter(root));

        System.out.println(dia.diameter);
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + '}';
        }
    }
}
