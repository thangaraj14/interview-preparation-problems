package trees;


/**
 * https://leetcode.com/problems/binary-tree-cameras/
 */
public class BinaryTreeCamera {

    int count = 0;
    int NOT_MONITERED = 0;
    int MONITERED = 1;
    int CAMERA = 2;

    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        if (dfs(root) == NOT_MONITERED) count++;
        return count;
    }

    public int dfs(TreeNode root) {
        /**
         So that leaf doesn't install camera on it
         Note: I will never assign camera to a leaf node.
         It will be better if I assign camera to that leaf's
         parent node,because parent will cover both its
         child as well its own parent as well.
         **/
        if (root == null) return MONITERED;


        int left = dfs(root.left);
        int right = dfs(root.right);

        if (left == NOT_MONITERED || right == NOT_MONITERED) {
            /**
             if any of my child wants me to put a camera on me,
             I will have to put a camera on my self. AND I will tell
             my parent that I have a camera. Not to worry
             */
            count++;
            return CAMERA;
        }

        if (left == CAMERA || right == CAMERA) {
            /**
             If any of my child has a camera, I will
             be covered as well. So, I will tell my parent
             that I am covered
             */
            return MONITERED;
        }

        return NOT_MONITERED;
    }
}
