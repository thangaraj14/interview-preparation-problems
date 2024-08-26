package trees.BST;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CeilAndFloor {

    public int[] ceilAndFloor(TreeNode root, int key) {
        int[] result = new int[2];
        ceilAndFloorHelper(root, key, result);
        return result;
    }

    public void ceilAndFloorHelper(TreeNode root, int key, int[] result) {
        if (root == null) return;

        if (root.val == key) {
            result[0] = root.val;
            result[1] = root.val;
            return;
        }

        if (root.val > key) {
            result[0] = root.val;
            ceilAndFloorHelper(root.left, key, result);
        } else {
            result[1] = root.val;
            ceilAndFloorHelper(root.right, key, result);
        }
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        for (int q : queries) {
            int[] ceilAndFloor = new int[2];
            Arrays.fill(ceilAndFloor, -1);
            closestNodesHelper(root, q, ceilAndFloor);
            result.add(Arrays.asList(ceilAndFloor[0], ceilAndFloor[1]));
        }

        return result;
    }

    //In the worst case (skewed tree), this can become O(n), where n is the number of nodes.
    // or we can use inorder traversal to get the sorted list of nodes and then find the closest nodes
    //using binary search
    public void closestNodesHelper(TreeNode root, int target, int[] ceilAndFloor) {
        if (root == null)
            return;

        if (root.val == target) {
            ceilAndFloor[0] = root.val;
            ceilAndFloor[1] = root.val;
            return;
        }

        if (root.val > target) {
            ceilAndFloor[1] = root.val;
            closestNodesHelper(root.left, target, ceilAndFloor);
        } else {
            ceilAndFloor[0] = root.val;// ceil

            closestNodesHelper(root.right, target, ceilAndFloor);
        }
    }

    //private List<Integer> closestBinarySearch(List<Integer> sortedTree, int target){
    //        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
    //        int left = 0, right = sortedTree.size() - 1;
    //
    //
    //        // TreeNode curr = root;
    //
    //        while(left <= right){
    //            int mid = left + (right - left) / 2;
    //            int midVal = sortedTree.get(mid);
    //
    //            if(midVal == target){
    //                min = midVal;
    //                max = midVal;
    //                break;
    //            }
    //
    //            // min_t is the largest value in the tree that is smaller than or equal to
    //            // min_t=  Max(Set of values < target)
    //            //      Set of values < target are
    //            // max_t=  Min(Set of values > target)
    //
    //            if(midVal < target){
    //                min = Math.max(min, midVal);
    //                left = mid + 1;
    //            }else {
    //                max = Math.min(max, midVal);
    //                right = mid - 1;
    //            }
    //        }
    //
    //        min = (min == Integer.MIN_VALUE) ? -1 : min;
    //        max = (max == Integer.MAX_VALUE) ? -1 : max;
    //
    //        return List.of(min, max);
    //    }

}
