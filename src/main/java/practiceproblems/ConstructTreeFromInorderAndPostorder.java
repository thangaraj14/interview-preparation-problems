package practiceproblems;

import trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConstructTreeFromInorderAndPostorder {
    int postorderIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        postorderIndex = postorder.length - 1;
        // build a hashmap to store value -> its index relations
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(postorder, 0, postorder.length - 1, inorderIndexMap);
    }

    private TreeNode arrayToTree(int[] postorder, int left, int right, Map<Integer, Integer> inorderIndexMap) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = postorder[postorderIndex];
        postorderIndex--;
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        // right has to be built first
        /**
         * The intuition behind it is that since postorder: LEFT → RIGHT → ROOT,
         * so when we go in reverse order, we must construct the tree in the order of: ROOT → RIGHT → LEFT
         */
        root.right = arrayToTree(postorder, inorderIndexMap.get(rootValue) + 1, right, inorderIndexMap);
        root.left = arrayToTree(postorder, left, inorderIndexMap.get(rootValue) - 1, inorderIndexMap);
        return root;
    }

    public TreeNode buildTreeIterative(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        int j = postorder.length - 1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(postorder[j--]);
        stack.push(root);
        TreeNode node = null;
        for (int i = inorder.length - 1; j >= 0; j--) {
            TreeNode cur = new TreeNode(postorder[j]);
            while (!stack.isEmpty() && stack.peek().val == inorder[i]) {
                node = stack.pop();
                i--;
            }
            if (node != null) {
                node.left = cur;
                node = null;
            } else {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        return root;
    }
}