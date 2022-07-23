package trees;

import trees.TreeNode;

public class SwapRecoverBST {

    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

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
        
        // If during the first time when you find prev.val >= root.val, 
        //the previous node "prev" MUST be one of two nodes that get swapped. 
        //However, the current node MAY OR MAY NOT be another node that gets swapped, 
        //which will depend on whether later during in-order traversal, there is another prev.val >= root.val or not.
        // If there is, then the current node "root" during the 2nd time of prev.val >= root.val will be the other node that gets swapped
        // Start of "do some business", 
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        // End of "do some business"

        traverse(root.right);
    }
}