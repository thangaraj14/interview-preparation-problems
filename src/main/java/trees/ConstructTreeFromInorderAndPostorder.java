package trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal

public class ConstructTreeFromInorderAndPostorder {
    int postorderIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        postorderIndex = postorder.length - 1;
        // build a hashmap to store value -> its index relations
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(postorder, 0, postorder.length - 1, inorderIndexMap);
    }

    private TreeNode arrayToTree(int[] postorder, int left, int right, Map<Integer, Integer> inorderIndexMap) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = postorder[postorderIndex];
        postorderIndex--;
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        /*
         * The intuition behind it is that since postorder: LEFT → RIGHT → ROOT,
         * so when we go in reverse order, we must construct the tree in the order of: ROOT → RIGHT → LEFT
         */
        root.right = arrayToTree(postorder, inorderIndexMap.get(rootValue) + 1, right, inorderIndexMap);
        root.left = arrayToTree(postorder, left, inorderIndexMap.get(rootValue) - 1, inorderIndexMap);
        return root;
    }

    public TreeNode buildTreeAlternative(int[] inorder, int[] postorder) {
        // build a hashmap to store value -> its index relations
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return helperFn(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, inorderIndexMap);
    }

    public TreeNode helperFn(int[] inorder, int iStart, int iEnd, int[] postorder, int ps, int pe, Map<Integer,Integer> map){
        if(iStart>iEnd || ps>pe) return null;

        TreeNode root= new TreeNode(postorder[pe]);
        pe--;
        int divider=map.get(root.val);
        int leftWindow = divider-iStart;
        root.left= helperFn(inorder, iStart,divider-1,postorder,ps,ps+leftWindow-1,map);
        root.right=helperFn(inorder,divider+1,iEnd,postorder,ps+leftWindow,pe,map);
        return root;
    }


    /**
     * The core idea is: Starting from the last element of the postorder and inorder array,
     * we put elements from postorder array to a stack and each one is the right child of the last one
     * until an element in postorder array is equal to the element on the inorder array.
     * Then, we pop as many as elements we can from the stack and decrease the mark in inorder array
     * until the peek() element is not equal to the mark value or the stack is empty.
     * Then, the new element that we are gonna scan from postorder array is the left child of the last element
     * we have popped out from the stack.
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeIterative(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        int postOrderPos = postorder.length - 1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        // last element in the postorder is the root of the tree
        TreeNode root = new TreeNode(postorder[postOrderPos--]);
        stack.push(root);
        TreeNode node = null;
        int inorderPos = inorder.length - 1;

        for (; postOrderPos >= 0; postOrderPos--) {
            TreeNode cur = new TreeNode(postorder[postOrderPos]);
            //pop  till all the elements matching inorder elements are removed from stack
            while (!stack.isEmpty() && stack.peek().val == inorder[inorderPos]) {
                node = stack.pop();
                inorderPos--;
            }
            if (node != null) {
                node.left = cur;
                node = null;
            } else {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        return root;
    }

    public static void main(String[] args) {
        ConstructTreeFromInorderAndPostorder tree = new ConstructTreeFromInorderAndPostorder();
        TreeNode root = tree.buildTreeIterative(new int[]{4, 2, 5, 1, 6, 3, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        System.out.println(root);
    }
}