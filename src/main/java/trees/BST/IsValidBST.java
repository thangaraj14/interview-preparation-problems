package trees.BST;

import trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    public boolean isValidBSTRecursive(TreeNode root) {
        if (root == null) return true;
        if (root.right == null && root.left == null) return true;
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTHelper(TreeNode root, long lowerBound, long upperBound) {
        if (root == null) return true;
        if (root.val <= lowerBound || root.val >= upperBound) return false;
        return isValidBSTHelper(root.left, lowerBound, root.val) && isValidBSTHelper(root.right, root.val, upperBound);
    }
}
