package trees.BST;

import trees.TreeNode;

// tricky
public class InsertBST {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    // the solution is simple, just traverse the tree and find the correct place to insert the node
    // for example
    //      4
    //     / \
    //    2   6
    //   / \
    //  1   3
    // Inserting 5:
    //
    //Start at root (4)
    //5 > 4, go right to 6
    //5 < 6, try to go left but left is null
    //Insert 5 as left child of 6
    public TreeNode insertIntoBSTIterative(TreeNode curr, int val) {
        if (curr == null) return new TreeNode(val);
        TreeNode root = curr;
        while (true) {
            // this is a lazy update, the target node is found in one iteration
            // and the new node is inserted in the next iteration
            if (val < root.val) {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                }
                root = root.right;
            }
        }
        return curr;
    }
}
