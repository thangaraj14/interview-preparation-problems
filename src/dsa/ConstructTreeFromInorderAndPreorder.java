package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
class ConstructTreeFromInorderAndPreorder {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        List<Integer> preorderList = Arrays.stream(preorder).boxed().collect(Collectors.toList());
        return buildTreeUtil(preorderList, 0, inorder.length - 1, inorderMap);
    }

    public static TreeNode buildTreeUtil(List<Integer> pre, int inorderStart, int inorderEnd,
            Map<Integer, Integer> inMap) {

        if (inorderStart > inorderEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre.get(0));
        int inRoot = inMap.get(root.val);
        pre.remove(0);

        root.left = buildTreeUtil(pre, inorderStart, inRoot - 1, inMap);
        root.right = buildTreeUtil(pre, inRoot + 1, inorderEnd, inMap);

        return root;
    }

    public static void main(String[] args) {

        TreeNode treeNode = buildTree(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 });
    }
}