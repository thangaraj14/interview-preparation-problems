package trees;

public class LCA {

    /**
     *Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     *
     * According to the definition of LCA on Wikipedia:
     * “The lowest common ancestor is defined between
     * two nodes p and q as the lowest node in T that has both p and q as descendants
     * (where we allow a node to be a descendant of itself).”
     *  p and q will exist in the tree.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root.val==p.val || root.val==q.val) return root;

        TreeNode left=lowestCommonAncestor( root.left, p,q);
        TreeNode right= lowestCommonAncestor(root.right,p,q);

        if(left!=null && right!=null ) return root;
        if(left!=null) return left;
        return right;

    }

    /**
     * the variation with the LCA is that
     * It is guaranteed that both p and q are in the tree.
     * A node can be a descendant of itself.
     * In the case of p = 5 and q = 4:
     * Because of the premises, we can return either p OR q as soon as we find one of them.
     * But for this question, the premises are different:
     * It is NOT guaranteed that both p and q are in the tree.
     * A node can still be a descendant of itself.
     * Hence,
     * We need a way to record if we've seen both p and q
     * We need to traverse the entire tree even after we've found one of them.
     * Here are the differences in code. The rest is the same.
     * Use either boolean or integers as flags
     * Keep traversing down the entire tree. If you return early, the above example would be null,
     * because the code stops when it finds 5 and does not keep searching for 4.
     */

    boolean pFound = false;
    boolean qFound = false;

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = LCA(root, p, q);
        return pFound && qFound ? LCA : null;
    }

    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (root == p) {
            pFound = true;
            return root;
        }
        if (root == q) {
            qFound = true;
            return root;
        }
        return left == null ? right : right == null ? left : root;
    }
}
