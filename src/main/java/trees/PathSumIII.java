package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tricky
 * You are given a binary tree in which each node contains an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 */
public class PathSumIII {
    Map<Integer, Integer> h = new HashMap<>();
    int count = 0;

    public void preorder(TreeNode node, int currSum, int targetSum) {
        if (node == null)
            return;

        // current prefix sum
        currSum += node.val;

        /**
         * situation 1, the tree path with the target sum starts from the root.
         * That means the current prefix sum is equal to the target sum curr_sum == k,
         * so one should increase the counter by 1: count += 1
         */
        if (currSum == targetSum)
            count++;

        /**
         * In situation 2, the tree path with the target sum starts somewhere downwards.
         * That means we should add to the counter the number of times we have seen the prefix sum curr_sum - target
         * so far: count += h[curr_sum - target].
         *
         * The logic is simple: the current prefix sum is curr_sum, and some elements before the prefix sum was curr_sum - target.
         * All the elements in between sum up to curr_sum - (curr_sum - target) = target.
         */

        count += h.getOrDefault(currSum - targetSum, 0);

        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum, targetSum);
        // process right subtree
        preorder(node.right, currSum, targetSum);

        /**
         * Now the current subtree is processed.
         * It's time to remove the current prefix sum from the hashmap,
         * in order not to blend the parallel subtrees: h[curr_sum] -= 1.
         */
        h.put(currSum, h.get(currSum) - 1);
    }

    public int pathSumAlter(TreeNode root, int sum) {
        preorder(root, 0, sum);
        return count;
    }

    List<Integer> list = new ArrayList<>();

    public int pathSumIII(TreeNode root, int sum) {
        if (root == null) return 0;
        list.add(root.val);
        pathSumIII(root.left, sum);
        pathSumIII(root.right, sum);

        int tempSum = 0;
        for (int i = list.size() - 1; i >= 0; i--) { // coming in reverse order, inorder to avoid considering head node
            tempSum += list.get(i);
            if (sum == tempSum) {
                count++;
            }
        }

        list.remove(list.size() - 1);
        return count;
    }

}