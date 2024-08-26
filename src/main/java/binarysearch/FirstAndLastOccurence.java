package binarysearch;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
 */
public class FirstAndLastOccurence {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) return result;

        int first = binarySearch(nums, target);

        if (first == nums.length || nums[first] != target) return result;

        result[0] = first;
        int last = binarySearch(nums, target + 1);

        result[1] = nums[last] == target ? last : last - 1;

        return result;

    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;

            }
        }

        return left;
    }
    public int[] searchRangeII(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int[] ans = {-1 , -1};

        if(nums.length < 1){
            return ans;
        }
        while(low <= high){ //Binary search for 1st occurrence
            int mid = (low + high)/2;
            if(target == nums[mid]){
                ans[0] = mid;
                high = mid -1; //searching on the left half
            }
            else if(target > nums[mid]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        low = 0; //Binary search for 2nd occurrence
        high = nums.length - 1;
        while(low <= high){
            int mid = (low + high)/2;
            if(target == nums[mid]){
                ans[1] = mid;
                low = mid + 1; //searching on the right half
            }
            else if(target > nums[mid]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return ans;
    }
}