package binarysearch;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array
 */
public class SingleElementInSortedArray {

    // the main idea here is the single element flips the order of the duplicates
    // for example in [1,1,2,2,3,4,4,8,8] before 3 the order is (even,odd) after 3 the order is (odd,even)
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int left = 1;
        int right = nums.length - 1;
        if(nums[0]!=nums[1]) return nums[0];
        if(nums[nums.length-1]!=nums[nums.length-2]) return nums[nums.length-1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //base case
            if (nums[mid - 1] != nums[mid] && nums[mid + 1] != nums[mid]) {
                return nums[mid];
            }
            // if mid is odd and mid-1 is equal to mid then the single element is on the left
            // if mid is even and mid+1 is equal to mid then the single element is on the left
            //[1,1,2,2,3,4,4,8,8] here if the mid is odd say 3 we check the mid-1
            // if the mid is even we check the mid+1 if the condition meets then we haven't
            // seen the single element which flips the order of the duplicates
            if((mid%2==1 && nums[mid] == nums[mid-1]) || (mid%2==0 && nums[mid]==nums[mid+1])){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }

        return -1;
    }
}