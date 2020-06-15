package geeksforgeeks;
public class PathSumIII {
    List<Integer> list= new ArrayList<>();
    int count=0;
    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
         list.add(root.val);
        int left=pathSum(root.left,sum);
        int right= pathSum(root.right,sum);
       
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

}