package trees;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/binary-tree-paths/
public class RootToLeafPaths {
    public List<String> binaryTreePaths(TreeNode root) {
        var result= new LinkedList<String>();
        binaryTreePathsUtil(root, result, "");
        return result;
    }

    public void binaryTreePathsUtil(TreeNode root, List<String> result , String temp ){
        if(root==null) return;
        if ( root.left==null && root.right==null){
            result.add(temp+root.val);
            return;
        }

        temp+=(root.val+"->");
        binaryTreePathsUtil(root.left,result,temp);
        binaryTreePathsUtil(root.right,result,temp);

    }

    public List<Integer> returnRootToLeafPaths(TreeNode root, int targetVal){
       List<Integer> result = new LinkedList<>();
       returnRootToLeafPathsUtil(root,result,targetVal);
       return result;
    }

    public void returnRootToLeafPathsUtil(TreeNode root, List<Integer> result, int targetVal){
        if(root==null) return;
        result.add(root.val);
        if(root.val == targetVal) return;
        returnRootToLeafPathsUtil(root.left,result,targetVal);
        returnRootToLeafPathsUtil(root.right,result,targetVal);
        result.remove(result.size()-1);
    }
}

