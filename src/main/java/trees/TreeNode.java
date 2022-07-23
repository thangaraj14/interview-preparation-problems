package trees;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
   public int val;

    public TreeNode(int val) {
        this.val = val;
    }

    public String toString() {
        return val + "";
    }
}