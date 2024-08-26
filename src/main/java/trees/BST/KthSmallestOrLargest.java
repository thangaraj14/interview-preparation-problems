package trees.BST;

import trees.TreeNode;
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestOrLargest {

    // doing inorder traversal and keeping track of the kth element
    public int kthSmallest(TreeNode root, int k) {
        int[] result = new int[]{0,0};
        kthSmallestHelper(root,k, result);
        return result[1];
    }

    public void kthSmallestHelper(TreeNode root, int k, int[] result){
        if(root==null || result[0]>k) return ;

        kthSmallestHelper(root.left,k,result);
        result[0]++;
        if (result[0]==k){
            result[1]=root.val;
            return;
        }
        kthSmallestHelper(root.right,k,result);

    }

    // doing reverse inorder traversal and keeping track of the kth element
    public int kthLargest(TreeNode root, int k) {
        int[] result = new int[]{0,0};
        kthSmallestHelper(root,k, result);
        return result[1];
    }

    public void kthLargestHelper(TreeNode root, int k, int[] result){
        if(root==null || result[0]>k) return ;

        kthSmallestHelper(root.right,k,result);
        result[0]++;
        if (result[0]==k){
            result[1]=root.val;
            return;
        }
        kthSmallestHelper(root.left,k,result);

    }
}
