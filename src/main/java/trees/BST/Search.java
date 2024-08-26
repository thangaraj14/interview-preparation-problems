package trees.BST;

import trees.TreeNode;

// https://leetcode.com/problems/search-in-a-binary-search-tree/
public class Search {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;

        if (root.val == val) return root;

        TreeNode result = null;
        if (root.val > val) {
            result = searchBST(root.left, val);
        }

        if (root.val < val) {
            result = searchBST(root.right, val);
        }

        return result;
    }
}
