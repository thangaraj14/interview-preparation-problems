package geeksforgeeks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
  Serialize and Deserialize Binary Tree - LeetCode:
  https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

  This code passes all Leetcode test cases as of April 26 2019
  Runtime: 12 ms, faster than 62.25% of Java online submissions for Serialize and Deserialize Binary Tree.
  Memory Usage: 39.4 MB, less than 71.37% of Java online submissions for Serialize and Deserialize Binary Tree.

  The video to explain this code is here: https://www.youtube.com/watch?v=suj1ro8TIVY
*/

public class SerializeAndDeserialize {

    private static final String NULL_SYMBOL = "X";
    private static final String DELIMITER = ",";

    public String serialize(TreeNode root) {

        if (root == null) {
            return NULL_SYMBOL + DELIMITER;
        }

        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);

        return root.val + DELIMITER + leftSerialized + rightSerialized;
    }

    public TreeNode deserialize(String data) {
        Queue<String> nodesLeftToMaterialize = new LinkedList<>();
        nodesLeftToMaterialize.addAll(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(nodesLeftToMaterialize);
    }

    public TreeNode deserializeHelper(Queue<String> nodesLeftToMaterialize) {

        String valueForNode = nodesLeftToMaterialize.poll();

        if (valueForNode.equals(NULL_SYMBOL)) {
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

