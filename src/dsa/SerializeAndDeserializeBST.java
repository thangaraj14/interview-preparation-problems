package dsa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-bst/
 */
public class SerializeAndDeserializeBST {

    private static final String DELIMITER = ",";
    private static final String NULL = "null";

    // Encodes a tree to a single string by using preorder.
    public String serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return null;
        }

        sb.append(root.val + DELIMITER);
        serialize(root.left, sb);
        serialize(root.right, sb);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(DELIMITER)));
        return getNode(queue);
    }

    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<String> q) { //q: 5,3,2,6,7
        if (q.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(q.poll()));//root (5)
        Queue<String> smallerQueue = new LinkedList<>();
        while (!q.isEmpty() && Integer.parseInt(q.peek()) < root.val) {
            smallerQueue.offer(q.poll());
        }
        root.left = getNode(smallerQueue);
        root.right = getNode(q);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.right.right = new TreeNode(7);
        SerializeAndDeserializeBST bst = new SerializeAndDeserializeBST();
        StringBuilder sb = new StringBuilder();
        String serialize = bst.serialize(root, sb);
        System.out.println(serialize);
        TreeNode deserialize = bst.deserialize(serialize);
        bst.printTree(deserialize);
    }

    public void printTree(TreeNode node) {
        if (node == null) {
            System.out.print("X,");
            return;
        }
        System.out.print(node.val + ",");
        printTree(node.left);
        printTree(node.right);
    }
}