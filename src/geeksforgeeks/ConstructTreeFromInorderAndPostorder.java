package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeFromInorderAndPostorder {
    // idea is same as inorder preorder, but we take postOrder[lastIndex] as root;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helperFn(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public TreeNode helperFn(int[] inorder, int iStart, int iEnd, int[] postorder, int pstart, int pend,
            Map<Integer, Integer> map) {
        if (iStart > iEnd || pstart > pend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[pend]);
        int divider = map.get(root.val);
        // one more change is, since we need to take last element as root
        // we have to carefully pass the right index to recursive calls
        // pstart, pstart+divider-iStart-1-> left subarray
        //pstart+divider-iStart, pend -> right sub array
        root.left = helperFn(inorder, iStart, divider - 1, postorder, pstart, pstart + divider - iStart - 1, map);
        root.right = helperFn(inorder, divider + 1, iEnd, postorder, pstart + divider - iStart, pend - 1, map);
        return root;
    }
}