package geeksforgeeks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
class ConstructTreeFromInorderAndPreorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        List<Integer> pre = new LinkedList<>();
        for (int i = 0; i < preorder.length; i++) {
            pre.add(preorder[i]);
        }
        TreeNode root = buildTree(pre, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree(ArrayList<Integer> pre, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre.get(0));
        int inRoot = inMap.get(root.val);
        pre.remove(0);

        root.left = buildTree(pre, inStart, inRoot - 1, inMap);
        root.right = buildTree(pre, inRoot + 1, inEnd, inMap);

        return root;
    }
}