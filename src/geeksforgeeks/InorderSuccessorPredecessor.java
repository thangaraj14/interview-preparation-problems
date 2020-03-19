package geeksforgeeks;

/**
 * https://algorithms.tutorialhorizon.com/inorder-predecessor-and-successor-in-binary-search-tree/
 */
public class InorderSuccessorPredecessor {
    static int successor, predecessor;

    public void successorPredecessor(TNode root, int val) {
        if (root != null) {
            if (root.data == val) {
                // go to the right most element in the left subtree, it will be the
                // predecessor.
                if (root.left != null) {
                    TNode t = root.left;
                    while (t.right != null) {
                        t = t.right;
                    }
                    predecessor = t.data;
                }
                if (root.right != null) {
                    // go to the left most element in the right subtree, it will be
                    // the successor.
                    TNode t = root.right;
                    while (t.left != null) {
                        t = t.left;
                    }
                    successor = t.data;
                }
            } else if (root.data > val) {
                // we make the root as successor because we might have a
                // situation when value matches with the root, it wont have
                // right subtree to find the successor, in that case we need
                // parent to be the successor
                successor = root.data;
                successorPredecessor(root.left, val);
            } else if (root.data < val) {
                // we make the root as predecessor because we might have a
                // situation when value matches with the root, it wont have
                // left subtree to find the predecessor, in that case we need
                // parent to be the predecessor.
                predecessor = root.data;
                successorPredecessor(root.right, val);
            }
        }
    }

    public void shortSolution(TNode root, int val) {
        if (root != null) {
            if (root.data > val) {
                // we make the root as successor because we might have a
                // situation when value matches with the root, it wont have
                // right subtree to find the successor, in that case we need
                // parent to be the successor
                successor = root.data;
                successorPredecessor(root.left, val);
            } else if (root.data < val) {
                // we make the root as predecessor because we might have a
                // situation when value matches with the root, it wont have
                // left subtree to find the predecessor, in that case we need
                // parent to be the predecessor.
                predecessor = root.data;
                successorPredecessor(root.right, val);
            }
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
        root.left.right.left = new TNode(19);
        root.left.right.right = new TNode(20);
        InorderSuccessorPredecessor i = new InorderSuccessorPredecessor();

        // i.successorPredecessor(root, 20);
/*        TNode tempSuccessor = root.right;
        TNode successor = i.findSuccessor(tempSuccessor);
        successor = successor==null?tempSuccessor:successor;*/

        System.out.println("Inorder Successor of 10 is : " + successor + " and predecessor is : " + predecessor);

    }
}

class TNode {
    int data;
    TNode left;
    TNode right;

    public TNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}