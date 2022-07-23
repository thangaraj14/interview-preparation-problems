package trees;

import java.util.Collection;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;
    Collection<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
    }

    public String toString() {
        return val + "";
    }
}