package trees.BST;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * tricky
 *
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 *      1        1          2         3          3
 *      \         \        / \       /         /
 *       2         3      1   3     2         1
 *        \       /                /           \
 *        3      2                1             2
 * Create root nodes by cycling through [start,end] or [1,n]
 * Recursively generate all possible left subtrees. By rules of a BST, all values in a left subtree are less than the root.
 * Thus, use the list [start, curRootVal - 1] to create the left subtrees
 * Recursively generate all possible right subtrees. By rules of a BST,
 * all values in a right subtree are greater than the root.
 * Thus, use the list [curRooVal + 1, end] to create the right subtrees.
 * For each combination of left and right subtrees, attach them to the current root to form a unique BST (or subtree)
 *
 * First we want each number for 1..n to be the root, for all different combinations tree roots so we can do
 *
 *      for i in range(1, n+1):
 * 	        curRoot = TreeNode(curRootVal)
 * 	  we can try to do the same and cycle through [1,n] for each of the children of curRoot
 * 	  But we have to obey the rules of a binary search tree which are:
 *          nodes in a left subtree must be less than the root
 *          nodes in a right subtree must be greater than the root
 *
 *  So we make each possible left child by cycling through all values less than the current root or [1,curRootVal -1].
 *   We make each possible right child by cycling through all values greater than the current root or [curRootVal + 1, n]
 *
 * Following above method we've created all possible roots using [1,n] in the for loop.
 * We somehow made the left subtree using the list [1,curRootVal-1] (numbers less than curRootVal).
 * We somehow made the right subtree using the list [curRootVal +1, n] (numbers greater than curRootVal).
 * Then we make left_subtree the left child of curRoot and right_subtree the right child of curRoot.
 *
 * From this code, should the recursive function return a single subtree?
 * Take a look at the trees for roots 1 and 3 above,
 * they have multiple possible subtrees! Thus, we should return all possible left and right subtrees.
 * That is, the recursive function returns a list! I'll call this list all_trees.
 */
public class GenerateAllPossibleBST {
    List<TreeNode>[][] cache;

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return Collections.emptyList();
        cache = new ArrayList[n + 1][n + 1];
        return recursiveTreeBuilder(1, n);
    }

    public List<TreeNode> recursiveTreeBuilder(int start, int end) {

        List<TreeNode> result = new ArrayList<>();

        // edge case , when i= start the call becomes recursiveTreeBuilder(i, i - 1)
        //             when i=end  the call becomes recursiveTreeBuilder(i+1, i)
        // both the cases produce start>end
        // so we return null list, the reason is if we return simply and empty list
        //  for (TreeNode left : leftTree), for (TreeNode right : rightTree)
        // one of the for loops for the edge case will get skipped because of empty list
        // so we place null at that position
        if (start > end) {
            result.add(null);
            return result;
        }
        if (cache[start][end] != null) return cache[start][end];

        for (int i = start; i <= end; i++) {

            List<TreeNode> leftTree = recursiveTreeBuilder(start, i - 1);
            List<TreeNode> rightTree = recursiveTreeBuilder(i + 1, end);

            // the double for loop is to generate all possible combinations of left and right
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return cache[start][end] = result;
    }
}
