package practiceproblems;


import trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class SerializeDeserializeBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helperFn(root, sb);
        return sb.toString();
    }

    public void helperFn(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val + ",");
        helperFn(root.left, sb);
        helperFn(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        Queue<Integer> treeVal = buildIntArr(data);

        return bulidTreeFromArray(treeVal);
    }

    public Queue<Integer> buildIntArr(String data) {

        String[] arr = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String se : arr) {
            queue.offer(Integer.parseInt(se));
        }
        return queue;
    }

    public TreeNode bulidTreeFromArray(Queue<Integer> treeValues) {
        if (treeValues.isEmpty()) return null;

        Integer rootVal = treeValues.poll();
        TreeNode root = new TreeNode(rootVal);

        Queue<Integer> smallerValues = new LinkedList<>();

        while (!treeValues.isEmpty() && treeValues.peek() < rootVal) {
            smallerValues.offer(treeValues.poll());
        }
        root.left = bulidTreeFromArray(smallerValues);
        root.right = bulidTreeFromArray(treeValues);
        return root;


    }

}