package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * tricky morris traversal
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * https://youtu.be/sWf7k1x9XR4
 * https://takeuforward.org/data-structure/flatten-binary-tree-to-linked-list/
 */
public class MorrisTraversalTreeToDLL {

    public void flattenIterativeExtraSpace(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // remember to push right first
            //the goal is to convert the given binary tree into a singly-linked list,
            // where the left pointer of each node is set to null, and the right pointer is set to the next node in the list.
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }

            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null;
        }
    }

    public void flattenMorris(TreeNode root) {
        // Handle the null scenario
        if (root == null) {
            return;
        }
        TreeNode node = root;
        while (node != null) {
            // If the node has a left child
            if (node.left != null) {
                // Find the predecessor node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                // this is the only change from typical morris traversal
                // there we assign rightmost.right = node and proceed to node.left
                // here we assign rightmost.right = node.right and proceed to node.right
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // move on to the right side of the tree
            node = node.right;
        }
    }
}
