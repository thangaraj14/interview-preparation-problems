package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class TreeTraversals {

    public List<Integer> preOrderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {

            TreeNode temp = stack.pop();
            result.add(temp.val);
            if (temp.left != null) stack.push(temp.left);
            if (temp.right != null) stack.push(temp.right);
        }

        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }

        return result;
    }

    /**
     * Just like with in-order traversal we go to the left subtree as long as we can. At the same time we keep adding the nodes to the stack.
     * If we can't (left = null) - we try to go to the right subtree. In order to do that we check the last one we added to the stack.
     * If it has a right subtree and we haven't visited it yet then we go there and repeat steps 1 and 2.
     * Else we visit the node (also pop out of the stack) 'cause by that time we visited left and right subtrees snd it's time to visit their parent.
     * After that we continue the outer loop, peek another node from the stack and repeat 2, 3.
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, lastVisited = null;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode peek = stack.pop();
            if (peek.right != null && peek.right != lastVisited) {
                cur = peek.right;
            } else {
                res.add(peek.val);
                lastVisited = stack.pop();
            }

        }

        return res;
    }

    public int index = 0;
    public TreeMap<Integer, List<Integer>> tm;

    static class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode n, int i) {
            node = n;
            index = i;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        tm = new TreeMap<>();
        if (root == null) return res;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (!tm.containsKey(cur.index)) tm.put(cur.index, new ArrayList<>());
            tm.get(cur.index).add(cur.node.val);

            if (cur.node.left != null) q.offer(new Pair(cur.node.left, cur.index - 1));
            if (cur.node.right != null) q.offer(new Pair(cur.node.right, cur.index + 1));
        }

        for (int key : tm.keySet()) res.add(tm.get(key));
        return res;
    }

    List<Integer> nodes = new ArrayList<>(1000);

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        if (root == null) return nodes;

        nodes.add(root.val);
        leftBoundary(root.left);
        leaves(root);
        rightBoundary(root.right);

        return nodes;
    }

    public void leftBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        nodes.add(root.val);
        if (root.left == null) leftBoundary(root.right);
        else leftBoundary(root.left);
    }

    public void rightBoundary(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) return;
        if (root.right == null) rightBoundary(root.left);
        else rightBoundary(root.right);
        nodes.add(root.val); // add after child visit(reverse)
    }

    public void leaves(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            nodes.add(root.val);
            return;
        }
        leaves(root.left);
        leaves(root.right);
    }
}
