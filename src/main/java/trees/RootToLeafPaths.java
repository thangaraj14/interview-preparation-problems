package trees;

import java.util.LinkedList;
import java.util.List;

public class RootToLeafPaths {
    public void construct_paths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            path += "" + root.val;
            if ((root.left == null) && (root.right == null))  // if reach a leaf
                paths.add(path);  // update paths
            else {
                path += "->";  // extend the current path
                construct_paths(root.left, path, paths);
                construct_paths(root.right, path, paths);
            }
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        construct_paths(root, "", paths);
        return paths;
    }
}

