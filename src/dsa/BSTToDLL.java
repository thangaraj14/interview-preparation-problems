package dsa;

import java.util.LinkedList;
import java.util.Queue;

public class BSTToDLL {

    static Node root;
    static Node prev;
    static Node head;

    public static void main(String[] args) {

        root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(5);
        root.left.left = new Node(4);

        root.right.right = new Node(7);
        root.right.left = new Node(6);

        covertDLLBFS(root);
        print(head);
    }

    private static void print(Node head) {
        Node temp = head;
        while (null != temp) {
            System.out.println(temp.data);
            temp = temp.right;
        }
    }

    private static void convertToDLL(Node root) {
        if (null == root) {
            return;
        }
        convertToDLL(root.left);
        if (null == prev) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        convertToDLL(root.right);
    }

    private static void covertDLLBFS(Node root) {

        if (null == root) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (null != poll.left) {
                queue.add(poll.left);
            }
            if (null != poll.right) {
                queue.add(poll.right);
            }
            if (null == prev) {
                head = prev = poll;
            } else {
                prev.right = poll;
                poll.left = prev;
                prev = poll;
            }
        }
    }

    static class Node {
        int data;
        Node right;
        Node left;

        public Node(int i) {
            this.data = i;
        }
    }
}
