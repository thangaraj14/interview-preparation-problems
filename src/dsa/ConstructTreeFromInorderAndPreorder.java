package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
class ConstructTreeFromInorderAndPreorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        ArrayList<Integer> preorderList = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            preorderList.add(preorder[i]);
        }
        return buildTree(preorderList, 0, inorder.length - 1, inorderMap);
    }

    public TreeNode buildTree(List<Integer> pre, int inorderStart, int inorderEnd, Map<Integer, Integer> inMap) {

        if (inorderStart > inorderEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre.get(0));
        int inRoot = inMap.get(root.val);
        pre.remove(0);

        root.left = buildTree(pre, inorderStart, inRoot - 1, inMap);
        root.right = buildTree(pre, inRoot + 1, inorderEnd, inMap);

        return root;
    }
}