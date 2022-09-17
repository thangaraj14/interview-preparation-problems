package trees;

import java.util.Stack;

/**
 * tricky
 *
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 *
 * In a binary tree, if we consider null as leaves, then
 *
 * all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
 * all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
 *
 * Suppose we try to build this tree. During building,
 * we record the difference between out degree and in degree diff = outdegree - indegree.
 * When the next node comes, we then decrease diff by 1, because the node provides an in degree.
 * If the node is not null, we increase diff by 2, because it provides two out degrees.
 * If a serialization is correct, diff should never be negative and diff will be zero when finished.
 */
public class isValidPreOrderSerialisation {

    public boolean isValidSerialization(String preorder) {
        int vacancy = 1;
        String[] preOrder = preorder.split(",");

        for (String s : preOrder) {
            vacancy--;
            if (vacancy < 0) return false;
            if (!s.equals("#")) vacancy += 2;

        }

        return vacancy == 0;
    }

    public boolean isValidSerializationStack(String preorder) {
        if (preorder == null) return false;

        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (String curr : strs) {
            if (curr.equals("#")) {
                // replace a number node and its left child "#" to a "#" node.
                while (!st.isEmpty() && st.peek().equals("#")) {
                    st.pop();
                    if (st.isEmpty()) return false;
                    st.pop();
                }
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }
}
