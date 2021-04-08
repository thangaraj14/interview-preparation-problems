package trees;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 */
public class BinaryTraversalIterative {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }


        return result;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        List<Integer> list = new LinkedList<>();
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if (temp.right != null) stack.push(temp.right);
            if (temp.left != null) stack.push(temp.left);
        }

        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if(root==null) return new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> result= new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp= stack.pop();
            result.addFirst(temp.val);
            if(temp.left!=null) stack.push(temp.left);
            if(temp.right!=null) stack.push(temp.right);
        }

        return result;
    }
}