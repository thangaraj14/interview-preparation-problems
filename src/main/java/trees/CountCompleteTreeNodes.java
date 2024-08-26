package trees;

// https://leetcode.com/problems/count-complete-tree-nodes/
// idea is to design algorithm that runs in O(log n * log n) time
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = findLeftHeight(root);
        int rightHeight = findRightHeight(root);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int findLeftHeight(TreeNode root) {
        if (root == null) return 0;
        int height = 0;

        while (root != null) {
            height++;
            root = root.left;
        }

        return height;
    }

    public int findRightHeight(TreeNode root) {
        if (root == null) return 0;
        int height = 0;

        while (root != null) {
            height++;
            root = root.right;
        }

        return height;
    }
}
