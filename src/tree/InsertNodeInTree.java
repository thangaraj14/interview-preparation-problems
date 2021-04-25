package tree;

import java.util.Stack;

public class InsertNodeInTree {
    static Node root;

    public static void main(String args[]) {
        int[] val = { 3, 2, 1, 5, 4, 6 };
        for (int j = 0; j <= 5; j++) {
            if (insertNodeInTree(val[j]) == "No") {
                System.out.println("No");
                break;
            }
        }

    }

    private static String insertNodeInTree(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            Stack<Node> stack = new Stack<>();
            Node node = root;
            stack.push(node);
            Node newNode = new Node(value);
            while (!stack.isEmpty()) {

                node = stack.pop();

                if (value < node.data) {
                    if (node.left == null) {
                        node.setLeft(newNode);
                    } else {
                        stack.push(node.getLeft());
                    }
                } else if (value > node.data) {
                    if (node.right == null) {
                        node.setRight(newNode);
                    } else if (value > node.data) {
                        stack.push(node.getRight());
                    }
                } else {
                    return "No";
                }
            }
        }
        return "Yes";
    }

    static class Node {
        int data;
        Node left;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        Node right;

        public Node(int val) {
            this.data = val;
        }
    }
}