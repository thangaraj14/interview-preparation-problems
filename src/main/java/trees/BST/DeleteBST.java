package trees.BST;

import trees.TreeNode;

/**
 * tricky
 * https://leetcode.com/problems/delete-node-in-a-bst/
 *
 * if the node has no children, simply remove it by returning null
 * if the node has one child, return the child
 * if the node has two children, find the inorder successor of the node.
 * Copy the value of the inorder successor to the node and then delete the inorder successor by calling step 1.
 */
public class DeleteBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) return null;

            else if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                root.val = getRightMin(root.right);
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;
    }

    public int getRightMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}
