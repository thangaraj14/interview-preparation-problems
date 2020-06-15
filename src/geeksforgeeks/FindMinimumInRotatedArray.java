package geeksforgeeks;

/*https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/*/
public class FindMinimumInRotatedArray {

    public static int findMin(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = ((end - start) / 2) + start;

            if (nums[start] <= nums[end]) {
                return nums[start];
            }
            // mid is compared against end, if mid is high, then the rotated part is right of mid
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public int findMinWithDuplicate(int[] nums) {
        if(nums.length==1) return nums[0];
        int start=0;
        int end= nums.length-1;
        while(start<end){
            
            if(nums[start]==nums[end]){
                start++; // we need to find the imbalance in the array to start the alg. 
            }else{
                int mid= start+ (end-start)/2;
             if(nums[mid]>nums[end]){
               start=mid+1;
            }else{
                end=mid;
            }
            }
           
        }
        
        return nums[start];
    }

    public static void main(String[] args) {
        int[] arr = { 7, 1, 2, 3, 4, 5, 6 };
        findMin(arr);
    }
}