package trees.BST;

import trees.TreeNode;

// https://leetcode.com/problems/recover-binary-search-tree/
public class SwapRecoverBST {

    TreeNode firstElement = null;
    TreeNode secondElement = null;
    TreeNode prevElement = null;

    public void recoverTree(TreeNode root) {

        // In order traversal to find the two elements
        traverse(root);
        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);
        // Let's assume this is the original in-order traversal sequence of BST: 1 2 3 4 5
        // If 2 and 3 get swapped, it becomes 1 3 2 4 5 and 
        //there is only one time that you will have prev.val >= root.val
        // If 2 and 4 get swapped, it becomes 1 4 3 2 5 and 
        //there are two times that you will have prev.val >= root.val

        //When we find the first violation of the BST property (where a node's value is less than or equal to its predecessor),
        // we set firstElement to prev.
        // This is because in an inorder traversal of a BST, each node should be greater than the previous node
        // [1, 6, 3, 4, 5, 2, 7].
        //During inorder traversal:
        //When we reach 3, we find that 3 <= 6 is true. This is our first violation. We set firstElement = prev (which is 6).
        //We continue until we reach 2. We find that 2 <= 5 is true.
        // This is our second violation. We set secondElement = root (which is 2).
        if (prevElement != null) {
            if (firstElement == null && prevElement.val >= root.val) {
                firstElement = prevElement;
            }

            //When we find a second violation (or the continuation of the first violation), we set secondElement to the current root.
            // This is because this node is smaller than it should be in the BST order.
            if (firstElement != null && prevElement.val >= root.val) {
                secondElement = root;
            }
        }
        prevElement = root;
        traverse(root.right);
    }
}