package trees;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindLeaves {

    private int calculateHeight(TreeNode root, List<Set<TreeNode>> result) {
        if (root == null) return -1;
        int height = 1 + Math.max(calculateHeight(root.left, result), calculateHeight(root.right, result));
        if (result.size() <= height) result.add(new HashSet<>());
        result.get(height).add(root);
        return height;
    }


    public List<Set<TreeNode>> fallingLeaves(TreeNode root) {
        List<Set<TreeNode>> result = new ArrayList<>();
        calculateHeight(root, result);
        return result;
    }


    public int calculateHeightNArray(TreeNode root, List<Set<TreeNode>> result) {
        if (root == null) return -1;
        int max = -1;

        for (TreeNode child : root.children) {
            max = Math.max(max, calculateHeight(child, result));
        }
        int height = 1 + max;

        if (result.size() <= height) result.add(new HashSet<>());
        result.get(height).add(root);
        return height;
    }


}
