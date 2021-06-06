package dsa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversals {

    Node root;

    public static void main(String[] args) {
        TreeTraversals tree = new TreeTraversals();

        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(7);
        tree.root.left.left = new Node(2);
        // tree.root.left.left.right = new Node(21);
        /*
         * tree.root.left.left.right.left = new Node(211);
         * tree.root.left.left.right.right = new Node(221);
         */
        tree.root.left.right = new Node(4);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(8);

        tree.preOrderTraversal(tree.root);
        System.out.println();
        tree.preOrderTraversalIterative(tree.root);
        System.out.println();
        tree.inOrderTraversal(tree.root);
        System.out.println();
        tree.inOrderTraversalIterative(tree.root);
        System.out.println();
        tree.postOrderTraversal(tree.root);
        System.out.println();
        tree.postOrderTraversalIterative(tree.root);
        System.out.println();
        tree.postOrderIterative(tree.root);
        System.out.println();
        tree.levelOrderTraversal(tree.root);
    }

    private void postOrderIterative(Node root) {
        
        Stack<Node> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                stack.push(root);
                root = root.left;
            }

            if (stack.empty()) {
                return;
            }
            root = stack.pop();

            if (!stack.empty() && stack.peek() == root) {
                root = root.right;
            } else {
                System.out.print(root.data + " ");
                root = null;
            }
        }
    }

    private void postOrderTraversalIterative(Node root) {
        if (root == null) {
            return;
        }
        Deque<Node> deque = new ArrayDeque<>();
        Deque<Node> resultDeque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            Node node = deque.pop();
            resultDeque.push(node);

            if (node.left != null) {
                deque.push(node.left);
            }

            if (node.right != null) {
                deque.push(node.right);
            }
        }

        while (!resultDeque.isEmpty()) {
            System.out.print(resultDeque.pop().data + " ");
        }
    }

    private void inOrderTraversalIterative(Node node) {
        if (root == null) {
            return;
        }

        Deque<Node> arrayDeque = new ArrayDeque<>();
        while (true) {

            while (node != null) {
                arrayDeque.push(node);
                node = node.left;
            }

            if (arrayDeque.isEmpty()) {
                break;
            }

            node = arrayDeque.pop();
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    private void preOrderTraversalIterative(Node root) {
        if (root == null) {
            return;
        }

        Deque<Node> arrayDeque = new ArrayDeque<>();
        arrayDeque.push(root);

        while (!arrayDeque.isEmpty()) {
            Node node = arrayDeque.pop();
            System.out.print(node.data + " ");
            if (node.right != null) {
                arrayDeque.push(node.right);
            }

            if (node.left != null) {
                arrayDeque.push(node.left);
            }
        }
    }

    private void levelOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    private void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    private void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
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
