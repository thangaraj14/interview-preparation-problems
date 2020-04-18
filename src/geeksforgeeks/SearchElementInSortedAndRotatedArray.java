package geeksforgeeks;

/*https://leetcode.com/problems/search-in-rotated-sorted-array/*/
public class SearchElementInSortedAndRotatedArray {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        SearchElementInSortedAndRotatedArray search = new SearchElementInSortedAndRotatedArray();
        System.out.println(search.searchInArray(arr, 1));
    }

    // 4, 5, 6, 7, 0, 1, 2
    int searchInArray(int arr[], int key) {

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
 
            if (nums[start] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
 
            if (nums[mid] <= nums[end]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    // with duplicates
    public boolean searchII(int[] nums, int target) {
        int left = 0, right =  nums.length-1, mid;
        
        while(left<=right)
        {
            mid = (left + right) >> 1;
            if(nums[mid] == target) return true;

            // the only difference from the first one, trickly case, just updat left and right
            if ((nums[left] == nums[mid]) && (nums[right] == nums[mid])) {
                ++left;
                --right;
            }

            else if(nums[left] <= nums[mid])
            {
                if( (nums[left]<=target) && (nums[mid] > target) ) right = mid-1;
                else left = mid + 1; 
            }
            else
            {
                if((nums[mid] < target) &&  (nums[right] >= target) ) left = mid+1;
                else right = mid-1;
            }
        }
        return false;
    }

}
