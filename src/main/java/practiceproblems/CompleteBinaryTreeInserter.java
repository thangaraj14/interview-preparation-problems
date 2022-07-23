package practiceproblems;

import trees.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/complete-binary-tree-inserter/
 *
 * tricky
 */
public class CompleteBinaryTreeInserter {

    TreeNode root;
    Deque<TreeNode> queue;

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        this.queue = new LinkedList<>();
        Queue<TreeNode> tempQueue = new LinkedList<>();
        tempQueue.offer(root);
        while (!tempQueue.isEmpty()) {
            TreeNode temp = tempQueue.poll();

            /**
             * store only the nodes with incomplete children
             */
            if (temp.right == null || temp.left == null) {
                queue.offerLast(temp);
            }


            if (temp.left != null) tempQueue.offer(temp.left);
            if (temp.right != null) tempQueue.offer(temp.right);
        }
    }

    public int insert(int v) {

        TreeNode deepNode = queue.peekFirst();
        queue.offerLast(new TreeNode(v));
        if (deepNode.left == null) {
            deepNode.left = queue.peekLast();
        } else {
            deepNode.right = queue.peekLast();
            queue.pollFirst();
        }

        return deepNode.val;

    }

    public TreeNode get_root() {
        return root;

    }
}
