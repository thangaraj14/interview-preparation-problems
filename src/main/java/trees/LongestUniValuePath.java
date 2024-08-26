package trees;

// https://leetcode.com/problems/longest-univalue-path/
public class LongestUniValuePath {

    Integer result=0;
    public int longestUnivaluePath(TreeNode root) {

        if(root==null) return 0;

        helperFn(root,root.val);

        return result;

    }

    private int helperFn(TreeNode root, int prevVal){
        if(root==null) return 0;

        int left=helperFn(root.left, root.val);
        int right=helperFn(root.right,root.val);
        result= Math.max(result,left+right);
        if(root.val==prevVal) return 1+Math.max(left,right);
        return 0;
    }

}
