package geeksforgeeks;
class CountNumbersLessThanSelf {
    public List<Integer> countSmaller(int[] nums) {
        if(nums.length==0) return new ArrayList<>();
        
        Integer[] result= new Integer[nums.length];
        Node root=null;
        for(int i=nums.length-1;i>=0;i--){ // build tree from end so that end is root and has sum 0
            root=buildBSTHelper(nums[i],root,result,i,0);
        }
        return Arrays.asList(result);
    }
    
    public Node buildBSTHelper(int val, Node root, Integer[] result, int index, int sum){
        if(root==null){
            root= new Node(val,0);
            result[index]=sum;
        }
       else if(root.val==val){
            root.dup++;
           result[index]=sum+root.sum;
        }
        else if(root.val>val){
            root.sum++;
            root.left=buildBSTHelper(val,root.left,result,index,sum);
        }else{
            root.right=buildBSTHelper(val,root.right,result,index,root.sum+root.dup+sum);
        }
        return root;
    }
    
    private class Node{
        int val;
        int sum;
        Node right;
        Node left;
        int dup=1;
        public Node(int val, int sum){
            this.val=val;
            this.sum=sum;
        }
    }
}