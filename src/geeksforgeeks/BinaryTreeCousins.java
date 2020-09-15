package geeksforgeeks;

import java.util.LinkedList;
import java.util.Queue;

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