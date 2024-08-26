package trees;

// https://leetcode.com/problems/diameter-of-binary-tree/
public class DiameterTree {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return diameter;
    }

    public int calculateHeight(TreeNode root) {
        if (root == null) return 0;

        int left = calculateHeight(root.left);
        int right = calculateHeight(root.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }

}
