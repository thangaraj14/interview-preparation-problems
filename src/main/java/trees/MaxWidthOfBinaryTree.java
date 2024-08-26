package trees;


import java.util.*;

/**
 * tricky level order and dfs traversal
 */
//https://leetcode.com/problems/maximum-width-of-binary-tree/
public class MaxWidthOfBinaryTree {

    //Considering nodes are in an array,
    //left child of any node at index i should be at index 2i.
    //right child of any node at index i should be ar index 2i+1.
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

    public int widthOfBinaryTreeDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, 0, 1, new ArrayList<Integer>());

    }

    private int helper(TreeNode root, int depth, int index, List<Integer> list) {
        if (root == null) {
            return 0;
        }
        //add index of leftmost node to list, at depth position in list
        //The condition depth == list.size() checks if we're seeing a new depth for the first time.
        //We only add to the list when we encounter a new depth, ensuring we always store the leftmost node at each level.
        //Depth   Tree         List
        //  0      1            [1]
        //        / \
        //  1    2   3         [1,2]
        //      / \   \
        //  2   4   5   6      [1,2,4]
        //     /
        //  3  7               [1,2,4,7]
        //When we first reach depth 2 at node 4:
        //
        //depth = 2
        //list.size() = 2 (contains [1, 2] from previous depths)
        //Since depth == list.size(), we add 4's index to the list
        //Now list becomes [1, 2, 4]
        //
        //
        //When we reach node 5, also at depth 2:
        //
        //depth = 2
        //list.size() = 3 (contains [1, 2, 4])
        //Since depth != list.size(), we don't add 5's index
        if (depth == list.size()) {
            list.add(index);
        }
        int currWidth = index - list.get(depth) + 1;
        int leftWidth = helper(root.left, depth + 1, index * 2, list);
        int rightWidth = helper(root.right, depth + 1, index * 2 + 1, list);
        return Math.max(currWidth, Math.max(leftWidth, rightWidth));
    }
}