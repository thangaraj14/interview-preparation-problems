package practiceproblems;

// Hi all, I think my solution is pretty straightforward and easy to understand, not that efficient though. And the serialized tree is compact.
// Pre order traversal of BST will output root node first, then left children, then right.

// root left1 left2 leftX right1 rightX
// If we look at the value of the pre-order traversal we get this:

// rootValue (<rootValue) (<rootValue) (<rootValue) |separate line| (>rootValue) (>rootValue)
// Because of BST's property: before the |separate line| all the node values are less than root value, all the node values after |separate line| are greater than root value. We will utilize this to build left and right tree.

import java.util.*;

class SerializeDeserializeBST {
    private static final String SEP = ",";
    private static final String NULL = "null";
    // Encodes a tree to a single string.

    public String serialize(TreeNode root) {

        if (root == null) {
            return NULL + SEP;
        }

        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);

        return root.val + SEP + leftSerialized + rightSerialized;
    }

    public String serializeIterative(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return NULL;
        //traverse it recursively if you want to, I am doing it iteratively here
        Deque<TreeNode> st = new ArrayDeque<>();
        st.push(root);
        while (!st.isEmpty()) {
            root = st.pop();
            sb.append(root.val).append(SEP);
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) return null;
        String[] strs = data.split(SEP);
        Queue<Integer> q = new LinkedList<>();
        for (String e : strs) {
            q.offer(Integer.parseInt(e));
        }
        return getNode(q);
    }

    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
        if (q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());//root (5)
        Queue<Integer> samllerQueue = new LinkedList<>();
        while (!q.isEmpty() && q.peek() < root.val) {
            samllerQueue.offer(q.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        root.left = getNode(samllerQueue);
        //q: 6,7   storing elements bigger than 5 (root)
        root.right = getNode(q);
        return root;
    }

}