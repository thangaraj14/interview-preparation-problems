package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class DepthOfTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int minDepth(TreeNode root) {

        Deque<TreeNode> deque = new ArrayDeque<>();
        int result = 0;
        deque.offer(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.poll();

                if (temp.left == null && temp.right == null)
                    return result;
                if (temp.left != null) {
                    deque.offer(temp.left);
                }
                if (temp.right != null) {
                    deque.offer(temp.right);
                }
            }
            result++;
        }
        return result;
    }

    public int minDepthDFS(TreeNode root) {
        if (root == null) return 0;
        int left = minDepthDFS(root.left);
        int right = minDepthDFS(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;

    }
}
