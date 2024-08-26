package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class Zigzag {

    public List<List<Integer>> zigzagTraversal(TreeNode root) {
        List<List<Integer>> result= new ArrayList<>();
        if(root==null) return result;

        Queue<TreeNode> queue= new ArrayDeque<>();
        int level=1;
        queue.offer(root);
        while(!queue.isEmpty()){
            int n= queue.size();
            List<Integer> tempList= new ArrayList<>();
            for(int i=0;i<n;i++){
                TreeNode temp=queue.poll();
                if(level%2!=0){
                    tempList.add(temp.val);
                }else{
                    tempList.add(0,temp.val);
                }
                if(temp.left!=null) queue.offer(temp.left);
                if(temp.right!=null) queue.offer(temp.right);
            }
            level++;
            result.add(tempList);
        }
        return result;
    }
}
