package geeksforgeeks;

/**
 * https://algorithms.tutorialhorizon.com/inorder-predecessor-and-successor-in-binary-search-tree/
 * https://www.lintcode.com/problem/inorder-successor-in-bst/description
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorPredecessor {

    public TNode successor(TNode root, TNode p) {
        if (root == null) {
            return null;
        }

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    public TNode predecessor(TNode root, TNode p) {
        if (root == null) {
            return null;
        }

        if (root.val >= p.val) {
            return predecessor(root.left, p);
        } else {
            TNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }

    public static void main(String args[]) {
        TNode root = new TNode(25);
        root.left = new TNode(15);
        root.right = new TNode(40);
        root.left.left = new TNode(10);
        root.left.left.left = new TNode(5);
        root.left.right = new TNode(18);
        root.right.left = new TNode(35);
        root.right.right = new TNode(45);
        root.left.right.right = new TNode(20);
        InorderSuccessorPredecessor i = new InorderSuccessorPredecessor();

        TNode successor = i.successor(root, root.right);
        TNode predecessor = i.predecessor(root, root.left.right.right);
/*        TNode tempSuccessor = root.right;
        TNode successor = i.findSuccessor(tempSuccessor);
        successor = successor==null?tempSuccessor:successor;*/

        System.out.println("Inorder Successor is : " + successor.val + " and predecessor is : " + predecessor.val);

    }
}

class TNode {
    int val;
    TNode left;
    TNode right;

    public TNode(int data) {
        this.val = data;
        left = null;
        right = null;
    }
}