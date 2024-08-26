package trees;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestorBinarySearchTree(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            // 1st one is that if both p & q are smaller than the root then call the left subtree
            if (root.val > p.val && root.val > q.val)
                root = root.left;
            // 2nd if both p & q are greater than the root then call the right subtree
            else if (root.val < p.val && root.val < q.val)
                root = root.right;
            else
                return root;
        }
        // if(root==null) return null;
        //
        //        if(root.val>p.val && root.val>q.val){
        //            return lowestCommonAncestor(root.left,p,q);
        //        }else if(root.val<p.val && root.val<q.val){
        //            return lowestCommonAncestor(root.right,p,q);
        //        }
        //        return root;
        return root;
    }

    /**
     * It is guaranteed that both p and q are in the tree.
     * A node can be a descendant of itself.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) return root;

        TreeNode lcaLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode lcaRight = lowestCommonAncestor(root.right, p, q);

        if (lcaLeft != null && lcaRight != null) return root;

        if (lcaLeft == null) return lcaRight;

        return lcaLeft;
    }

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        boolean[] exists = new boolean[2];
        TreeNode lca = LCAHelper(root, p, q, exists);
        return (exists[0] && exists[1] ? lca : null);
    }

    TreeNode LCAHelper(TreeNode root, TreeNode p, TreeNode q, boolean[] exists) {
        if (root == null)
            return root;

        TreeNode left = LCAHelper(root.left, p, q, exists);
        TreeNode right = LCAHelper(root.right, p, q, exists);

        if (root.val == p.val) {
            exists[0] = true;
            return root;
        }
        if (root.val == q.val) {
            exists[1] = true;
            return root;
        }

        if (left == null)
            return right;
        else if (right == null)
            return left;
        else //both are not null and hence we have found our LCA
            return root;
    }

    public Node lowestCommonAncestorIIIExtraSpace(Node p, Node q) {
        Set<Node> seen = new HashSet<>();
        while (p != null) {
            seen.add(p);
            p = p.parent;
        }

        while (q != null) {
            if (seen.contains(q)) {
                return q;
            }
            q = q.parent;
        }

        return null;
    }

    /**
     * tricky LCA
     */
    public Node lowestCommonAncestorIII(Node p, Node q) {
        Node pRunner = p;
        Node qRunner = q;

        while (pRunner != qRunner) {
            // Guaranteed to complete since both nodes belong to same tree
            // We are simulating a cycle when any of the conditions below is satisfied
            // by pointing runner to the head of the other "list"  to make sure
            // intersection is found before either of the below conditions are satisfied again
            pRunner = pRunner.parent == null ? q : pRunner.parent;
            qRunner = qRunner.parent == null ? p : qRunner.parent;
        }

        return pRunner;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null)
            throw new IllegalArgumentException("Invalid input as p and q are guaranteed to exist");

        int pDepth = getDepth(p), qDepth = getDepth(q);

        while (pDepth > qDepth) {
            pDepth--;
            p = p.parent;
        }

        while (qDepth > pDepth) {
            qDepth--;
            q = q.parent;
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }

        return p;
    }

    private int getDepth(Node a) {
        int depth = 0;
        while (a != null) {
            a = a.parent;
            depth++;
        }
        return depth;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> s = new HashSet<>();
        for (TreeNode n : nodes) s.add(n.val);
        return lcaHelper(root, s);
    }

    private TreeNode lcaHelper(TreeNode root, Set<Integer> s) {
        if (root == null) return null;
        if (s.contains(root.val)) return root;
        TreeNode left = lcaHelper(root.left, s);
        TreeNode right = lcaHelper(root.right, s);
        if (left != null && right != null) return root;
        else return (left != null) ? left : right;
    }

    static class Node {
        public Node left;
        public Node right;
        public Node parent;
        public int data;
    }

}
