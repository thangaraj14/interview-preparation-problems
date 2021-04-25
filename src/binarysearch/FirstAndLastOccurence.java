package geeksforgeeks;

public class FirstAndLastOccurence {

    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int[] result= new int[2];
        result[0]=binarySearch(nums,target,false);
        if(result[0]==-1){
            result[1]=-1;
            return result;
        }
        result[1]=binarySearch(nums,target,true);
        return result;
    }
    
    public int binarySearch(int[]nums, int target, boolean findMaxIndex){
        int start=0;
        int end=nums.length-1;
        int key=-1;
        while(start<=end){
            int mid= start + (end-start)/2;
            
            if(nums[mid]<target){
                start=mid+1;
            }else if(nums[mid]>target){
                end=mid-1;
            }else{
                key=mid;
                if(findMaxIndex) start=mid+1;
                else end=mid-1;
            }
        }
        
        return key;
    }
}