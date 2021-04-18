package binarysearch;

/**
 * https://leetcode.com/problems/search-insert-position/
 *
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int left=0;
        //Also notice that the input target might be larger than all elements in nums and therefore needs to placed at the end of the array.
        // That's why we should initialize right = len(nums) instead of right = len(nums) - 1.
        int right= nums.length;

        while(left<right){
            int mid= left+ (right-left)/2;
            if(nums[mid]>= target) right=mid;
            else left=mid+1;
        }

        return left;
    }
}
