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

    public TreeNode bulidTreeFromArray(Queue<Integer> treeVals) {
        if (treeVals.isEmpty()) return null;

        Integer rootVal = treeVals.poll();
        TreeNode root = new TreeNode(rootVal);

        Queue<Integer> smallerVals = new LinkedList<>();

        while (!treeVals.isEmpty() && treeVals.peek() < rootVal) {
            smallerVals.offer(treeVals.poll());
        }
        root.left = bulidTreeFromArray(smallerVals);
        root.right = bulidTreeFromArray(treeVals);
        return root;


    }

}