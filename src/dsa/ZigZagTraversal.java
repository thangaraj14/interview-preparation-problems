package dsa;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class ZigZagTraversal {

    static class Node {
        Node left;
        Node right;
        int data;
    }

    static Node newNode(int data) {
        Node temp = new Node();
        temp.data = data;
        temp.left = null;
        temp.right = null;

        return temp;
    }

    static int heightOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        int lheight = heightOfTree(root.left);
        int rheight = heightOfTree(root.right);

        return Math.max(lheight + 1, rheight + 1);
    }

    static void printRightToLeft(Node root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printRightToLeft(root.right, level - 1);
            printRightToLeft(root.left, level - 1);
        }
    }

    static void printLeftToRight(Node root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printLeftToRight(root.left, level - 1);
            printLeftToRight(root.right, level - 1);
        }
    }

    static void printZigZag(Node root) {
        int flag = 0;

        int height = heightOfTree(root);

        for (int i = 1; i <= height; i++) {
            if (flag == 1) {
                printRightToLeft(root, i);
                flag = 0;
            } else if (flag == 0) {
                printLeftToRight(root, i);
                flag = 1;
            }
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(Node root) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new ArrayDeque<>();
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node temp = queue.poll();
                if (level % 2 != 0) {
                    tempList.add(temp.data);
                } else {
                    tempList.add(0, temp.data);
                }
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            level++;
            result.add(tempList);
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = newNode(7);
        root.left = newNode(4);
        root.right = newNode(5);
        root.left.left = newNode(9);
        root.right.right = newNode(10);
        root.left.left.left = newNode(6);
        root.left.left.right = newNode(11);

        printZigZag(root);
        System.out.println();
        zigzagLevelOrder(root);
    }
}