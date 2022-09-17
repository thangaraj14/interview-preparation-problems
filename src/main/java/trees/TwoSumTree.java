package trees;

import java.util.ArrayList;
import java.util.List;

public class TwoSumTree {

    public boolean findTargetExtraSpace(TreeNode root, int k) {
        if (root == null) return false;
        List<Integer> in = new ArrayList<>();

        inorder(root, in);

        int i = 0;
        int j = in.size() - 1;

        while (i < j) {
            if (in.get(i) + in.get(j) == k)
                return true;
            if (in.get(i) + in.get(j) > k)
                j--;
            else i++;
        }

        return false;

    }

    private void inorder(TreeNode root, List<Integer> in) {
        if (root == null) return;
        inorder(root.left, in);
        in.add(root.val);
        inorder(root.right, in);
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        TreeNode start = root;
        TreeNode end = root;
        while (start.left != null) {
            start = start.left;
        }
        while (end.right != null) {
            end = end.right;
        }
        while (start != end) {
            int sum = start.val + end.val;
            if (sum > k) {
                end = findPredecessor(root, end);
            } else if (sum < k) {
                start = findSuccessor(root, start);
            } else {
                return true;
            }
        }
        return false;
    }

    private TreeNode findPredecessor(TreeNode root, TreeNode node) {
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < node.val) {
                pre = cur;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return pre;
    }

    private TreeNode findSuccessor(TreeNode root, TreeNode node) {
        TreeNode succ = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > node.val) {
                succ = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return succ;
    }
}
