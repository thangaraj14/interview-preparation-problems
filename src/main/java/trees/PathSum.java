package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathSum {


    /**
     * Given the root of a binary tree and an integer targetSum,
     * return true if the tree has a root-to-leaf
     * path such that adding up all the values along the path equals targetSum.
     * <p>
     * A leaf is a node with no children.
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && targetSum - root.val == 0) return true;
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);

        return left || right;
    }

    /**
     * Given the root of a binary tree and an integer targetSum,
     * return all root-to-leaf paths where each path's sum equals targetSum.
     * <p>
     * A leaf is a node with no children.
     * <p>
     * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * Output: [[5,4,11,2],[5,8,4,5]]
     */

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        pathSumUtil(root, result, targetSum, new ArrayList<>());
        return result;
    }

    public void pathSumUtil(TreeNode root, List<List<Integer>> result, int targetSum, List<Integer> tempList) {
        if (root == null) return;
        tempList.add(root.val);
        if (root.left == null && root.right == null && targetSum - root.val == 0) {
            result.add(new ArrayList<>(tempList));
        }

        pathSumUtil(root.left, result, targetSum - root.val, tempList);
        pathSumUtil(root.right, result, targetSum - root.val, tempList);
        tempList.remove(tempList.size() - 1);
    }

}
