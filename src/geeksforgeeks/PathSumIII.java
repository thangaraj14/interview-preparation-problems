package geeksforgeeks;
public class PathSumIII {
    List<Integer> list= new ArrayList<>();
    int count=0;
    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
         list.add(root.val);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
       
        int k=0;
        for(int i=list.size()-1;i>=0;i--){ // coming in reverse order, inorder to avoid considering
                                          // head node
            k+=list.get(i);
            if(sum==k){
                count++;
            }
        }
        
        list.remove(list.size()-1);
        return count;
    }

    public void preorder(TreeNode node, int currSum) {
        if (node == null)
            return;
        
        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;
        
        // number of times the curr_sum − k has occured already, 
        // determines the number of times a path with sum k 
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);
        
        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum);
        // process right subtree
        preorder(node.right, currSum);

        // remove the current sum from the hashmap
        // in order not to use it during 
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }    
            
    public int pathSumAlter(TreeNode root, int sum) {
        k = sum;
        preorder(root, 0);
        return count;
    }

}