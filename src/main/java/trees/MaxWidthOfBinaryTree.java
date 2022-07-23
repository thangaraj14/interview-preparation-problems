package trees;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *  tricky level order traversal
 *
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null
 *
 * Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 */
public class MaxWidthOfBinaryTree {

    // Each time a node is traversed, the position of the node(as it is in a full binary tree) is stored in the HashMap.
    //If the position of the parent node is 'n', then the left child is '2 * n' and the right child is '2 * n + 1'.
    //The width of each level is the last node's position in this level subtracts the first node's position in this level plus 1.
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;

        Map<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, 1); // if we are assigning head as 0 then the formula is left=>2*n+1, right=> 2*n+2
        while (!queue.isEmpty()) {
            int width = queue.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < width; i++) {
                TreeNode temp = queue.poll();
                if (i == 0) start = map.get(temp);
                if (i == width - 1) end = map.get(temp);
                if (temp.left != null) {
                    map.put(temp.left, map.get(temp) * 2);
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    map.put(temp.right, map.get(temp) * 2 + 1);
                    queue.offer(temp.right);
                }
            }

            maxWidth = Math.max(maxWidth, end - start + 1);
        }

        return maxWidth;
    }
}