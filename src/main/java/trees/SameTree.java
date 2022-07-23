package trees;

import trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * Output: false
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode f = queue.poll();
            TreeNode s = queue.poll();
            if (f == null && s == null) {
                continue;
            } else if (f == null || s == null || f.val != s.val) {
                return false;
            }
            queue.add(f.left);
            queue.add(s.left);
            queue.add(f.right);
            queue.add(s.right);
        }
        return true;
    }

    public boolean isSameTreeRecur(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        if (p.val == q.val) return true;

        return isSameTreeRecur(p.left, q.left) && isSameTreeRecur(p.right, q.right);
    }
}