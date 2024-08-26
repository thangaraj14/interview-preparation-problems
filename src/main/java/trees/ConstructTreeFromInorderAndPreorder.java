package trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
class ConstructTreeFromInorderAndPreorder {

    // The basic idea is here:
    // Say we have 2 arrays, PRE and IN.
    // Preorder traversing implies that PRE[0] is the root node.
    // Then we can find this PRE[0] in IN, say it's IN[5].
    // Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left
    // side, IN[6] to the end is on the right side.
    // Recursively doing this on sub arrays, we can build a tree out of it :)
    int index = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> hm = new HashMap<>();

        if (preorder == null || preorder.length == 0) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, inorder.length - 1, hm);
    }

    public TreeNode buildTreeHelper(int[] preorder, int left, int right, Map<Integer, Integer> hm) {
        if (right < left) {
            return null;
        }
        if (index >= preorder.length) return null;
        int currValue = preorder[index++];
        TreeNode node = new TreeNode(currValue);
        node.left = buildTreeHelper(preorder, left, hm.get(currValue) - 1, hm);
        node.right = buildTreeHelper(preorder, hm.get(currValue) + 1, right, hm);

        /**
         * https://takeuforward.org/data-structure/construct-a-binary-tree-from-inorder-and-preorder-traversal/
         * // Create a new TreeNode with value
         *         // at the current preorder index
         *         TreeNode root = new TreeNode(preorder.get(preStart));
         *
         *         // Find the index of the current root
         *         // value in the inorder traversal
         *         int inRoot = inMap.get(root.val);
         *
         *         // Calculate the number of
         *         // elements in the left subtree
         *         int numsLeft = inRoot - inStart;
         *
         *         // Recursively build the left subtree
         *         root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
         *                 inorder, inStart, inRoot - 1, inMap);
         *
         *         // Recursively build the right subtree
         *         root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
         *                 inorder, inRoot + 1, inEnd, inMap);
         */
        return node;
    }

    public TreeNode buildTreeAlter(int[] preorder, int[] inorder) {

        if(preorder.length == 0 || inorder.length == 0)
            return null;

        TreeNode root = new TreeNode(preorder[0]);

        int mid = 0;
        int len = inorder.length;
        for(int i = 0; i < len; i++)
        {
            if(inorder[i] == root.val)
            {
                mid = i;
                break;
            }
        }
        //can do copyRange in different lines
        root.left = buildTreeAlter(Arrays.copyOfRange(preorder, 1, mid+1), Arrays.copyOfRange(inorder, 0, mid));
        root.right = buildTreeAlter(Arrays.copyOfRange(preorder, mid+1, len), Arrays.copyOfRange(inorder, mid+1,len));

        return root;
    }

}