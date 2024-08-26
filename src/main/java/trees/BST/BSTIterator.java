package trees.BST;

import trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/binary-search-tree-iterator/
public class BSTIterator {
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        for (TreeNode node = root; node != null; node = node.left) {
            stack.push(node);
        }
    }

    public int next() {
        if (!hasNext()) return -1;
        TreeNode curr = stack.pop();
        TreeNode right = curr.right;
        while (right != null) {
            stack.push(right);
            right = right.left;
        }
        return curr.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
