package trees;

import java.util.HashSet;

/**
 * Given Binary tree, return the number of nodes on the longest distinct path that starts at the root.
 * Grab Codility
 * Question states that if the current node val is seen anywhere in the tree, stop proceeding further
 */
public class UniqueTreePath {

    int result = 0;

    public int solution(TreeNode T) {
        // write your code in Java SE 8
        HashSet<Integer> set = new HashSet<>();
        helper(T, set);
        return result;

    }

    private void helper(TreeNode root, HashSet<Integer> set) {
        if (root == null)
            return;
        if (set.contains(root.val)) {
            return;
        }
        set.add(root.val);
        result = Math.max(result, set.size());
        helper(root.left, set);
        helper(root.right, set);
        set.remove(root.val);

    }
}
