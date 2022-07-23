package trees;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree
 */
public class SecondMinValueInSpecialTree {

    public int findSecondMinimumValue(TreeNode root) {
        // tree is not empty
        // root != null

        // each node in this tree has exactly two or zero sub-node
        if (root.left == null) {
            return -1;
        }

        int left = -1;
        if (root.val != root.left.val) {
            // find a value bigger than root.val
            left = root.left.val;
        } else {
            left = findSecondMinimumValue(root.left);
        }

        int right = -1;
        if (root.val != root.right.val) {
            right = root.right.val;
        } else {
            right = findSecondMinimumValue(root.right);
        }

        if (left == -1 || right == -1) {
            // the bigger value is left or right, or it not exist
            return Math.max(left, right);
        } else {
            // the min of left and right is what we want
            return Math.min(left, right);
        }
    }
}
