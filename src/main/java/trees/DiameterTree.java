package trees;

public class DiameterTree {

    public int height(TreeNode root, int[] ans) {
        if (root != null) {
            int left = height(root.left, ans);
            int right = height(root.right, ans);
            ans[0] = Math.max(ans[0], 1 + left + right);
            return 1 + Math.max(left, right);
        }
        return 0;
    }

    int diameterOfBinaryTree(TreeNode root) {
        int[] ans = new int[1];
        height(root, ans);
        return ans[0] - 1;
    }

}
