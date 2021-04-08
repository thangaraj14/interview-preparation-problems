package geeksforgeeks;

/**
 * https://algorithms.tutorialhorizon.com/inorder-predecessor-and-successor-in-binary-search-tree/
 */
public class InorderSuccessorPredecessor {
    static int successor, predecessor;

    public void successorPredecessor(TNode root, int val) {
            if (root == null) return;

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

         i.successorPredecessor(root, 20);

        System.out.println("Inorder Successor of 20 is : " + successor + " and predecessor is : " + predecessor);

    }

    TreeNode result=null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            helperFn(root,p);
            return result;
    }
    
    public void helperFn(TreeNode root, TreeNode p){
        if(root==null) return;
        
      if(root.val>p.val){
            result=root;
            helperFn(root.left,p);
        }else{
             helperFn(root.right,p);
        }
        
    }
    private TreeNode findPredecessor(TreeNode root, TreeNode node) {
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < node.val) {
                pre = cur;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return pre;
    }
    private TreeNode findSuccessor(TreeNode root, TreeNode node) {
        TreeNode succ = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > node.val) {
                succ = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return succ;
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