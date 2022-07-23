package practiceproblems;

import trees.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialize and Deserialize Binary Tree - LeetCode:
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * The video to explain this code is here: https://www.youtube.com/watch?v=suj1ro8TIVY
 */

public class SerializeAndDeserialize {

    private static final String NULL_SYMBOL = "X";
    private static final String DELIMITER = ",";

    public String serialize(TreeNode root) {

        if (root == null) {
            return NULL_SYMBOL;
        }

        String left = serialize(root.left) + DELIMITER;
        String right = serialize(root.right);

        return root.val + DELIMITER + left + right;
    }

    public TreeNode deserialize(String data) {
        Queue<String> nodesLeftToMaterialize = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(nodesLeftToMaterialize);
    }

    public TreeNode deserializeHelper(Queue<String> nodesLeftToMaterialize) {

        String valueForNode = nodesLeftToMaterialize.poll();

        if (valueForNode == null || valueForNode.equals(NULL_SYMBOL) || valueForNode.trim().length() == 0) {
            return null;
        }

        TreeNode newNode = new TreeNode(Integer.parseInt(valueForNode));
        newNode.left = deserializeHelper(nodesLeftToMaterialize);
        newNode.right = deserializeHelper(nodesLeftToMaterialize);

        return newNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        SerializeAndDeserialize sede = new SerializeAndDeserialize();
        String serialize = sede.serialize(root);
        System.out.println(serialize);
        TreeNode deserialze = sede.deserialize(serialize);
        sede.printTree(deserialze);
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

