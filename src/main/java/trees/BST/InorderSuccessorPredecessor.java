package trees.BST;

import trees.TreeNode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorPredecessor {
    TreeNode result = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        helperFn(root, p);
        return result;
    }

    public void helperFn(TreeNode root, TreeNode p) {
        if (root == null) return;

        if (root.val > p.val) {
            result = root;
            helperFn(root.left, p);
        } else {
            helperFn(root.right, p);
        }

    }

    // tricky tree traversal
    // predecessor go to left first and then go right till end
    // so place the result assignment (pre = cur) in the go right block
    private TreeNode findPredecessor(TreeNode root, TreeNode p) {
        TreeNode predecessor = null;
        while (root != null) {
            if (root.val >= p.val) {
                root = root.left;
            } else {
                predecessor = root;
                root = root.right;
            }
        }
        return predecessor;
    }

    // successor go to right first and then go left till end
    // so place the result assignment (pre = cur) in the go left block
    private TreeNode findSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}

