package geeksforgeeks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
class ConstructTreeFromInorderAndPreorder {

    // The basic idea is here:
    // Say we have 2 arrays, PRE and IN.
    // Preorder traversing implies that PRE[0] is the root node.
    // Then we can find this PRE[0] in IN, say it's IN[5].
    // Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left
    // side, IN[6] to the end is on the right side.
    // Recursively doing this on subarrays, we can build a tree out of it :)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> set = new LinkedList<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        for (int i = 0; i < preorder.length; i++) {
            set.add(preorder[i]);
        }

        return buildTreeUtil(map, set, 0, inorder.length - 1);

    }

    public TreeNode buildTreeUtil(Map<Integer, Integer> map, List<Integer> set, int start, int end) {
        if (start > end)
            return null;
        if (set.isEmpty())
            return null;
        int rootval = set.get(0);
        set.remove(0);
        TreeNode root = new TreeNode(rootval);
        int inorderIndex = map.get(rootval);
        root.left = buildTreeUtil(map, set, start, inorderIndex - 1);
        root.right = buildTreeUtil(map, set, inorderIndex + 1, end);
        return root;
    }
}