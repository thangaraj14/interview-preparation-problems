package trees;

public class InsertBST {

    public TreeNode insertIntoBST(TreeNode root, int val){
        if(root==null) return new TreeNode(val);

        if(val<root.val) root.left = insertIntoBST(root.left,val);
        else root.right = insertIntoBST(root.right,val);

        return root;
    }


    public TreeNode insertIntoBSTIterative(TreeNode curr, int val) {
        if(curr==null) return new TreeNode(val);
        TreeNode root = curr;
        while(true){
            if(val<root.val){
                if(root.left==null) {
                    root.left = new TreeNode(val);
                    break;
                }
                root=root.left;
            }else{
                if(root.right==null) {
                    root.right = new TreeNode(val);
                    break;
                }
                root = root.right;
            }
        }
        return curr;
    }
}
