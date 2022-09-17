package trees;

/**
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/submissions/
 *
 * I will use an example to explain the logic behind the solution of this exercise.
 * Below you see a Binary Tree.
 * In red, next to each node, you see the sum of all nodes' values rooted at the subtree.
 * This information can be computed by running DFS on the tree (see the code in the end).
 * For example, the sum of all nodes rooted at the node with value 2 is: 11= 2 + 5+ 4.
 * We can note that the sum of all nodes rooted at the root of the tree is the total sum of all nodes.
 * This information is important, and we will use it later.
 *           1 (21)
 *          / \
 *    (11) 2     3 (9)
 *       / \     \
 *      4   5     6
 *
 * Suppose we cut the edge connecting the node with value 1 and the node with value 2.
 *           1 (10) 21 becomes 10 because of cut
 *          X \
 *    (11) 2     3 (9)
 *       / \     \
 *      4   5     6
 *
 *  What is the product of the sum of the subtrees? Well,
 *  we have already pre-calculated the sum of all nodes in the left subtree (rooted at 2)
 *  and we can note that the sum of all nodes in the right subtree is the total sum minus the sum of the left subtree.
 *  We have pre-calculated all the information we need!
 *  However, we do not know which edge deletion results in the maximum product, so we need to try all the edges.
 */
public class MaxProductSplitBinaryTree {
    long max = Long.MIN_VALUE;
    long totalSum = 0;

    public int maxProduct(TreeNode root) {
        sum(root);
        max(root, totalSum);
        return (int) (max % (1e9 + 7));
    }

    public void max(TreeNode node, long totalSum) {
        if (node == null) return;
        if (node.left != null) {
            int sum1= (int) (totalSum - node.left.val);
            int sum2=node.left.val;
            max = Math.max(max, (long) sum1 * sum2);
        }
        if (node.right != null) {
            int sum1= (int) (totalSum - node.right.val);
            int sum2=node.right.val;
            max = Math.max(max, (long) sum1 * sum2);
        }
        max(node.left, totalSum);
        max(node.right, totalSum);
    }

    // use the root value to represent the sum of its subtree
    public void sum(TreeNode root) {
        if (root == null) return;
        totalSum += root.val;
        sum(root.left);
        sum(root.right);
        root.val += (root.left == null ? 0 : root.left.val) + (root.right == null ? 0 : root.right.val);
    }
}
