package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/complete-binary-tree-inserter/
 *
 * tricky
 */
public class CompleteBinaryTreeInserter {
    private final TreeNode root;

    private final Queue<TreeNode> queue;

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        this.queue = new LinkedList<>();
        queue.add(root);
        // iterate till we encounter first non-full node
        while (queue.peek()!=null && queue.peek().left != null && queue.peek().right != null) {
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        if (queue.peek()!=null && queue.peek().left != null) {
            queue.offer(queue.peek().left);
        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);
        TreeNode parent = queue.peek();
        if (parent.left == null) {
            parent.left = node;
        } else if (parent.right == null) {
            parent.right = node;
            queue.poll(); // remove parent from queue as it is full
        }
        queue.add(node);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
