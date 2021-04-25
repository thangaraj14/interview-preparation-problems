package tree;

/**
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 * <p>
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 */
public class LCAII {

    class Node {
        Node left;
        Node right;
    }

    boolean pFound = false;
    boolean qFound = false;

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        Node LCA = LCA(root, p, q);
        return pFound && qFound ? LCA : null;
    }

    public Node LCA(Node root, Node p, Node q) {
        if (root == null) {
            return null;
        }
        Node left = LCA(root.left, p, q);
        Node right = LCA(root.right, p, q);
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