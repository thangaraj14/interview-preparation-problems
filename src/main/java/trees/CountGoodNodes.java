package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 *
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 */
public class CountGoodNodes {

    int result = 0;

    /**
     * Thought Process:
     *
     * Question say that, path from root to X there are no nodes with a value greater than X
     * In other words, how many elements in the path maintain ascending order ?
     * What can be the best way to check if the ascending order is maintained or not ?
     * While traversing from root to any node, we can keep a variable having the maximum value till now
     *
     * Initially let the max value be int_min
     * At every node check if ( node value ) >= ( max till now from root )
     * if greater, increase the count( bcz. we got one good node ) and update the max value which we need to pass for left and right subtree
     * Now get the count for left and right subtree recursively passing the updated max value as argument
     * so final ans will be count( left subtree ) + count( right subtree ) + ( 1 or 0 as per step 2 )
     *
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;

        return recursionHelper(root, Integer.MIN_VALUE);
    }


    public int recursionHelper(TreeNode root, int max) {
        if (root == null) return 0;

        if (root.val >= max) {
            max = root.val;
            result++;
        }

        recursionHelper(root.left, max);
        recursionHelper(root.right, max);
        return result;
    }

    public int goodNodesBruteForce(TreeNode root) {
        if (root == null) return 0;

        recursionHelper(root, new ArrayList<>());
        return result;
    }


    public void recursionHelper(TreeNode root, List<Integer> tempList) {
        if (root == null) return;

        tempList.add(root.val);
        List<Integer> temp = new ArrayList<>(tempList);
        Collections.sort(temp);
        if (root.val >= temp.get(temp.size() - 1)) {
            result++;
        }

        recursionHelper(root.left, tempList);
        recursionHelper(root.right, tempList);
        tempList.remove(tempList.size() - 1);
    }

    static class Pair{
        TreeNode root;
        int val;
        Pair(TreeNode root, int val) {
            this.root = root;
            this.val = val;
        }
    }
    public int goodNodesBFS(TreeNode root) {
        if(root == null) return 0;
        int count = 0 ;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, root.val));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode t = p.root;
            if(t.val >= p.val) {
                count++;
            }
            if(t.left != null) {
                q.offer(new Pair(t.left, Math.max(t.left.val, p.val)));
            }
            if(t.right != null) {
                q.offer(new Pair(t.right, Math.max(t.right.val, p.val)));
            }
        }
        return count;
    }
}
