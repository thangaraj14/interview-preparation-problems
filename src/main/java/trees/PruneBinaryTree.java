package trees;

/**
 * https://leetcode.com/problems/binary-tree-pruning/
 * <p>
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * <p>
 * A subtree of a node is node plus every node that is a descendant of node.
 */
public class PruneBinaryTree {


    public TreeNode pruneTreeEffective(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTreeEffective(root.left);
        root.right = pruneTreeEffective(root.right);

        if (root.val == 0 && root.left == null && root.right == null) {
            root = null;
        }

        return root;
    }
}



