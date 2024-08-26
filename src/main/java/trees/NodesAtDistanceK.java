package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * this problem can be asked as Min time taken to burn a binary tree starting from target node
 * https://www.geeksforgeeks.org/minimum-time-to-burn-a-tree-starting-from-a-leaf-node/
 */
public class NodesAtDistanceK {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, TreeNode> parentsMap = new HashMap<>();
        buildParentMap(root, root, parentsMap);//first root is node and second root is its parent considered
        Set<TreeNode> visited = new HashSet<>(); //keep track of visited nodes
        Queue<TreeNode> q = new LinkedList<>();//for BFS
        q.add(target);  //instead of root here we are adding target as we need to find from that node
        visited.add(target); //also target node need to be visited first
        int level = 0; //as we are using level order traversal k == level then we find all the nodes in queue
        while (!q.isEmpty()) {
            int size = q.size();
            if (level == K) return buildResult(q);//elements remaining in queue will be nodes at dist
            for (int i = 0; i < size; i++) {                  //k from target
                TreeNode t = q.remove();
                if (t.left != null && !visited.contains(t.left)) {
                    q.add(t.left);
                    visited.add(t.left);
                }
                if (t.right != null && !visited.contains(t.right)) {
                    q.add(t.right);
                    visited.add(t.right);
                }
                TreeNode parent = parentsMap.get(t);
                if (!visited.contains(parent)) { //if parent is not visited
                    q.add(parent);//add parent to q
                    visited.add(parent);   //mark it as visited
                }
            }
            level++; //we check level wise and each level covers 1 unit distance
        }
        return res;
    }

    private List<Integer> buildResult(Queue<TreeNode> q) {
        List<Integer> lst = new ArrayList<>();
        while (!q.isEmpty()) {
            lst.add(q.remove().val);
        }
        return lst;
    }

    private void buildParentMap(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> parentsMap) {
        if (root == null)
            return;
        parentsMap.put(root, parent);
        buildParentMap(root.left, root, parentsMap); //build for left subtree root.left will be child of root
        buildParentMap(root.right, root, parentsMap);//build for right subtree
    }
}
