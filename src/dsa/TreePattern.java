package dsa;

/**
 * https://leetcode.com/discuss/general-discussion/605740/day-30-check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree
 * <p>
 * Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
 * <p>
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
 * Output: true
 * Explanation:
 * The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
 * Other valid sequences are:
 * 0 -> 1 -> 1 -> 0
 * 0 -> 0 -> 0
 * <p>
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
 * Output: false
 * Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
 * <p>
 * <p>
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
 * Output: false
 * Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
 * Unresolved
 */
public class TreePattern {

    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || arr == null || arr.length == 0) {
            return false;
        }

        return findTheNode(root, arr);
    }

    private boolean findTheNode(TreeNode root, int[] arr) {
        if (root == null) {
            return false;
        }
        if (root.val == arr[0]) {
            if (findPattern(root, null, 0, arr)) {
                return true;
            }
        }
        findTheNode(root.left, arr);
        findTheNode(root.right, arr);

        return false;
    }

    private boolean findPattern(TreeNode root, TreeNode prev, int index, int[] arr) {
        if (arr.length == index && prev.left == null && prev.right == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        if (index < arr.length && root.val == arr[index]) {
            return findPattern(root.left, root, index + 1, arr) || findPattern(root.right, root, index + 1, arr);
        }
        return false;
    }

    public static void main(String[] args) {
        TreePattern treePattern = new TreePattern();
        TreeNode root = treePattern.constructTree();
        System.out.println(treePattern.isValidSequence(root, new int[] { 0, 1, 0, 1 }));

    }

    private TreeNode constructTree() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.right = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.left.right.right = new TreeNode(0);
        root.left.right.left = new TreeNode(0);

        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.val = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "val=" + val + '}';
        }
    }
}
