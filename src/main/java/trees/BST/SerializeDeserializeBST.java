package trees.BST;


import trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class SerializeDeserializeBST {
    private static final String NULL_SYMBOL = "X";
    private static final String DELIMITER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL_SYMBOL;
        }

        StringBuilder sb = new StringBuilder();
        String left = serialize(root.left);
        String right = serialize(root.right);
        // root, left, right pre-order traversal
        sb.append(root.val).append(DELIMITER).append(left).append(DELIMITER).append(right);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Queue<Integer> nodes = new LinkedList<>();
        for (String s : data.split(DELIMITER)) {
            if (s.equals(NULL_SYMBOL))
                continue;
            nodes.offer(Integer.parseInt(s));
        }
        return deserializeHelper(nodes);
    }

    public TreeNode deserializeHelper(Queue<Integer> treeValues) {
        if (treeValues.isEmpty()) return null;

        Integer rootVal = treeValues.poll();
        TreeNode root = new TreeNode(rootVal);

        Queue<Integer> smallerValues = new LinkedList<>();

        while (!treeValues.isEmpty() && treeValues.peek() < rootVal) {
            smallerValues.offer(treeValues.poll());
        }
        root.left = deserializeHelper(smallerValues);
        root.right = deserializeHelper(treeValues);
        return root;


    }

}