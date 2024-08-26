package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://youtu.be/80Zug6D1_r4
// https://takeuforward.org/data-structure/morris-inorder-traversal-of-a-binary-tree/
public class MorrisTraversal {

    // Morris Traversal is a way to traverse the tree without using any extra space.
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();

        TreeNode current = root;
        while (current != null) {
            // Case 1: No Left Child
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
                continue;
            }
            // Case 2: Left Child Exists (Find Predecessor)
            TreeNode prev = current.left;
            while (prev.right != null && prev.right != current) {
                prev = prev.right;
            }
            // If a left child exists, find the predecessor and decide whether to create or remove a thread.
            if (prev.right == null) {
                prev.right = current;
                current = current.left;
            } else {
                prev.right = null;
                result.add(current.val);
                current = current.right;
            }

        }

        return result;
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();

        TreeNode current = root;
        while (current != null) {
            // Case 1: No Left Child
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
                continue;
            }
            // Case 2: Left Child Exists (Find Predecessor)
            TreeNode prev = current.left;
            while (prev.right != null && prev.right != current) {
                prev = prev.right;
            }
            // If a left child exists, find the predecessor and decide whether to create or remove a thread.
            if (prev.right == null) {
                prev.right = current;
                result.add(current.val);
                current = current.left;
            } else {
                prev.right = null;

                current = current.right;
            }

        }
        return result;
    }


    public List<Integer> postOrderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();

        TreeNode current = root;
        while (current != null) {
            if (current.right == null) {
                result.add(0, current.val); // Add the current node's value to the beginning of the result list
                current = current.left;
                continue;
            }
            TreeNode prev = current.right;
            while (prev.left != null && prev.left != current) {
                prev = prev.left;
            }
            if (prev.left == null) {
                prev.left = current;
                result.add(0, current.val); // Add the current node's value to the beginning of the result list
                current = current.right;
            } else {
                prev.left = null;
                current = current.left;
            }

        }
        return result;
    }
}
