package trees;

import java.util.*;

public class TreeTraversals {

    public int index = 0;
    public TreeMap<Integer, List<Integer>> tm;
    List<Integer> nodes = new ArrayList<>(1000);

    public List<Integer> preOrderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        // add root to the stack
        // while stack is not empty
        // pop the element from the stack
        // add it to the result
        // if right is not null add it to the stack
        // if left is not null add it to the stack
        // reason to add right first is because stack is LIFO
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
     * Just like with in-order traversal we go to the left subtree as long as we can.
     * At the same time we keep adding the nodes to the stack.
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
        //     1
        //    / \
        //   2   3
        //  / \
        // 4   5
        //Initialize: cur = 1, stack = [], res = []
        //Push 1, 2, 4 onto stack. cur = null, stack = [1, 2, 4], res = []
        //Process 4: res = [4], lastVisited = 4, stack = [1, 2]
        //Peek at 2, it has right child 5. cur = 5
        //Push 5 onto stack. stack = [1, 2, 5]
        //Process 5: res = [4, 5], lastVisited = 5, stack = [1, 2]
        //Process 2: res = [4, 5, 2], lastVisited = 2, stack = [1]
        //Peek at 1, it has unvisited right child 3. cur = 3
        //Push 3 onto stack. stack = [1, 3]
        //Process 3: res = [4, 5, 2, 3], lastVisited = 3, stack = [1]
        //Finally, process 1: res = [4, 5, 2, 3, 1], stack = []

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode peek = stack.peek();
            if (peek.right != null && peek.right != lastVisited) {
                cur = peek.right;
            } else {
                res.add(peek.val);
                //The lastVisited variable is crucial here.
                // It helps us determine whether we've already processed the right child of a node.
                // Without it, we might end up in an infinite loop when backtracking from a right child to its parent.
                lastVisited = stack.pop();
            }

        }

        return res;
    }

    //https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree
    // question asks for result in vertical order with some order preservation, can be ignored
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        tm = new TreeMap<>();
        if (root == null) return res;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            tm.computeIfAbsent(cur.index, k -> new ArrayList<>()).add(cur.node.val);
            if (cur.node.left != null) {
                q.offer(new Pair(cur.node.left, cur.index - 1));
            }
            if (cur.node.right != null) {
                q.offer(new Pair(cur.node.right, cur.index + 1));
            }
        }

        for (int key : tm.keySet()) {
            res.add(tm.get(key));
        }
        return res;
    }

    public List<List<Integer>> AllIterativeTraversalInOneGo(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        int PREORDER = 0;
        int INORDER = 1;
        int POSTORDER = 2;
        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, PREORDER));

        while (!stack.isEmpty()) {
            Pair curr = stack.pop();

            if (curr.index == PREORDER) {
                preOrder.add(curr.node.val);
                stack.push(new Pair(curr.node, INORDER));
                if (curr.node.left != null) stack.push(new Pair(curr.node.left, PREORDER));
            } else if (curr.index == INORDER) {
                inOrder.add(curr.node.val);
                stack.push(new Pair(curr.node, POSTORDER));
                if (curr.node.right != null) stack.push(new Pair(curr.node.right, PREORDER));
            } else {
                postOrder.add(curr.node.val);
            }
        }

        return List.of(preOrder, inOrder, postOrder);

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode curNode = q.poll(); // for left assign if curNode != null
                if (i == n - 1) {
                    res.add(curNode.val);
                }
                if (curNode.left != null) {
                    q.offer(curNode.left);
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                }
            }
        }
        return res;
    }

    static class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode n, int i) {
            node = n;
            index = i;
        }
    }
}
