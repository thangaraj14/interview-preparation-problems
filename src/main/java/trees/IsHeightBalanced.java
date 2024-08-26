package trees;

// https://leetcode.com/problems/balanced-binary-tree/
public class IsHeightBalanced {
    public boolean isBalanced(TreeNode root) {
        return isDepthBalanced(root)!=-1;
    }

    public int isDepthBalanced(TreeNode root){
        if(root==null) return 0;

        int left = isDepthBalanced(root.left);
        if (left==-1){
            return -1;
        }
        int right = isDepthBalanced(root.right);
        if (right==-1){
            return -1;
        }

        if (Math.abs(left-right)>1){
            return -1;
        }
        return 1+Math.max(left,right);
    }
}
