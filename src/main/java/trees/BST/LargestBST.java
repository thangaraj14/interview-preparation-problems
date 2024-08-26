package trees.BST;

import trees.TreeNode;

/**
 * tricky BST
 */
public class LargestBST {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        NodeValue result = largestBSTSubtreeHelper(root);
        return result.maxSize;

    }


    //     Idea for a valid BST is if a node root has
//
//                     root
//                  /       \
//                /          \
//        largest in left    smallest in right
//
//        if root > 'Max left' then it is greater than all the nodes in the left subtree
//        if root < 'Min right' then it is smaller than the all the nodes in the right subtree
//
//        in case of non bst then pass +INF as left max and -INF as right min which will break the result and then keep the size of the previous result
//
//        if root == null pass leftmax as -INF and rightMin as +INF*/

    //       10
    //     /    \
    //    5      15
    //   / \    /  \
    //  1   8  12   20
    //     /
    //    7
    //We start at the root (10). We recursively call the function on the left and right subtrees.
    //Let's follow the left subtree (5) first:
    //
    //For node 1: It's a leaf, so we return (1, 1, 1) (minNode, maxNode, size)
    //For node 7: It's a leaf, so we return (7, 7, 1)
    //For node 8:
    //Left child (7) < 8 < Right child (null), so it's a BST
    //We return (7, 8, 2)
    //For node 5:
    //1 < 5 < 8, so this subtree is a BST
    //We return (1, 8, 4)
    //
    //
    //Now the right subtree (15):
    //
    //For nodes 12 and 20: They're leaves, so we return (12, 12, 1) and (20, 20, 1)
    //For node 15:
    //12 < 15 < 20, so this subtree is a BST
    //We return (12, 20, 3)
    //
    //
    //Back to the root (10):
    //
    //Left subtree: (1, 8, 4)
    //Right subtree: (12, 20, 3)
    //8 < 10 < 12, so the entire tree is a BST
    //We return (1, 20, 8) - This is the final result
    public NodeValue largestBSTSubtreeHelper(TreeNode root) {
        // The reason for this seemingly counterintuitive choice is to ensure
        // that these null node values don't interfere with the BST property checks in the parent nodes
        //For a null left child, left.maxNode is MIN_VALUE, so root.val is always greater.
        //For a null right child, right.minNode is MAX_VALUE, so root.val is always less.
        if (root == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }


        //The `NodeValue` information fo the current node is updated
        //based on the information from the left and right subtree properties
        //ie. the left subtree’s maximum node is less than the current node
        //and the right subtree’s minimum node is greater than the current node.
        NodeValue left = largestBSTSubtreeHelper(root.left);
        NodeValue right = largestBSTSubtreeHelper(root.right);

        int size = Math.max(left.maxSize, right.maxSize);

        if (left.maxNode < root.val && root.val < right.minNode) {
            return new NodeValue(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode), left.maxSize + right.maxSize + 1);
        }
        //non bst is found, then pass +INF as left max and -INF as right min which will break the result and then keep the size of the previous result
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, size);


    }


    static class NodeValue {
        int maxSize;
        int maxNode;
        int minNode;

        public NodeValue(int minNode, int maxNode, int maxSize) {
            this.minNode = minNode;
            this.maxNode = maxNode;
            this.maxSize = maxSize;
        }
    }
}
