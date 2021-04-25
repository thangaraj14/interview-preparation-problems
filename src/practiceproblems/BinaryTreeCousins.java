package practiceproblems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a binary tree, the root node is at depth 0,
 * and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth,
 * but have different parents.
 * We are given the root of a binary tree with unique values,
 * and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 *               1
 *              / \
 *            2    3
 *             \    \
 *              4    5
 *
 */
public class BinaryTreeCousins {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
         if(root == null) return false;
         queue.add(root);
         int depthY = -1;
         int depthX = -2;
         int level = 0;
         while(!queue.isEmpty()){
             int size = queue.size();
             for(int i = 0 ; i < size ; i++){
                 TreeNode node = queue.remove();
                 // eagerly checking if both vales are of same parent
                 if(node.left != null && node.right != null){
                     if(node.left.val == x && node.right.val == y) return false;
                     if(node.left.val == y && node.right.val == x) return false;
                 }
                 //now checking if any of x or y matches with current node and records the level
                 if(node.val == x) depthX = level;
                 if(node.val == y) depthY = level;
                 if(node.left != null) queue.add(node.left);
                 if(node.right != null) queue.add(node.right);
             }
             level++;
         }
         return depthX == depthY;
     }
}