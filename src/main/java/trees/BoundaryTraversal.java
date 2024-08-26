package trees;

import java.util.*;

public class BoundaryTraversal {

    // the level order traversal is not needed here
    // this requires dfs approach
    // left side, right side view is totally different from boundary traversal
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();

        // root
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // left boundary
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            t = t.left == null ? t.right : t.left;
        }

        // leaves
        addLeaves(root, res);

        // right boundary(reverse order)
        Deque<Integer> s = new ArrayDeque<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.offer(t.val);
            }
            t = t.right == null ? t.left : t.right;
        }
        while (!s.isEmpty()) {
            res.add(s.pollLast());
        }

        // output
        return res;
    }

    private void addLeaves(TreeNode root, List<Integer> res) {
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        if (root.left != null) {
            addLeaves(root.left, res);
        }
        if (root.right != null) {
            addLeaves(root.right,res);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
