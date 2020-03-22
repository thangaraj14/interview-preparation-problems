package geeksforgeeks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PreOrderInOrderTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> set = new ArrayList<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        for (int i = 0; i < preorder.length; i++) {
            set.add(preorder[i]);
        }

        return buildTreeUtil(map, set, 0, inorder.length - 1);

    }

    public TreeNode buildTreeUtil(Map<Integer, Integer> map, List<Integer> set, int start, int end) {
        if (start > end) {
            return null;
        }
        if (set.isEmpty()) {
            return null;
        }
        int rootval = set.get(0);
        set.remove(0);
        TreeNode root = new TreeNode(rootval);
        int inorderIndex = map.get(rootval);
        root.left = buildTreeUtil(map, set, start, inorderIndex - 1);
        root.right = buildTreeUtil(map, set, inorderIndex + 1, end);
        return root;
    }
}