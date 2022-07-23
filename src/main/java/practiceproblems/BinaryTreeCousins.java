package practiceproblems;

import trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * https://leetcode.com/problems/cousins-in-binary-tree
 */
public class BinaryTreeCousins {

    public boolean isCousins(TreeNode root, int x, int y) {

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            boolean xFound = false;
            boolean yFound = false;

            for (int i = 0; i < n; i++) {
                TreeNode temp = queue.poll();

                if (temp.left != null && temp.right != null) {
                    if (temp.left.val == x && temp.right.val == y) return false;
                    if (temp.left.val == y && temp.right.val == x) return false;
                }

                if (temp.val == x) xFound = true;
                if (temp.val == y) yFound = true;

                if (temp.left != null)
                    queue.addLast(temp.left);
                if (temp.right != null)
                    queue.addLast(temp.right);
            }

            if (xFound && yFound) return true;
            if (yFound || xFound) return false;
        }

        return true;
    }
}