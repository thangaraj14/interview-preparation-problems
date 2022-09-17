package trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii
 */
public class ClosestValue {
    public int closestValue(TreeNode root, double target) {
        if (root == null) return 0;

        int val = root.val;
        double bestDiff = Math.abs(target - val);
        TreeNode node = root;
        while (node != null) {
            double diff = Math.abs(target - node.val);
            if (diff <= bestDiff) {
                bestDiff = diff;
                val = node.val;
            }
            node = target <= node.val ? node.left : node.right;
        }

        return val;
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> dq = new LinkedList<>();

        inorder(root, dq);
        while (dq.size() > k) {
            if (Math.abs(dq.peekFirst() - target) > Math.abs(dq.peekLast() - target))
                dq.pollFirst();
            else
                dq.pollLast();
        }

        return new ArrayList<>(dq);
    }

    public void inorder(TreeNode root, Deque<Integer> dq) {
        if (root == null) return;
        inorder(root.left, dq);
        dq.add(root.val);
        inorder(root.right, dq);
    }
}
