package practiceproblems;


import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    Map<Integer, Integer> h= new HashMap<>();
    int count=0;

    public void preorder(TreeNode node, int currSum, int k) {
        if (node == null)
            return;

        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;

        // number of times the curr_sum − k has occured already,
        // determines the number of times a path with sum k
        // has occurred upto the current node
        count += h.getOrDefault(currSum - k, 0);

        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum, k);
        // process right subtree
        preorder(node.right, currSum, k);

        // remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }

    public int pathSumAlter(TreeNode root, int sum) {
        int k = sum;
        preorder(root, 0, k);
        return count;
    }

}