package binarysearch;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class SearchElementInSortedAndRotatedArray {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};

        SearchElementInSortedAndRotatedArray search = new SearchElementInSortedAndRotatedArray();
        System.out.println(search.searchI(arr, 0));

        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        System.out.println(search.searchII(nums, 2));
    }

    // 4, 5, 0, 1, 2, 3
    public int searchI(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;

            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
            else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    // with duplicates
    //[2,5,6,0,0,1,2]
    //        0
    //duplicates, we know nums[mid] != target, so nums[start] != target //based on current information,
    // we can only move left pointer to skip one cell
    // thus in the worst case, we would have target: 2, and array like 11111111,
    // then the running time would be O(n)

    public boolean searchII(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) return true;

            // the only difference from the first one, tricky case, just update left and right
            if ((nums[left] == nums[mid]) && (nums[right] == nums[mid])) {
                ++left;
                --right;
            } else if (nums[left] <= nums[mid]) {
                if ((nums[left] <= target) && (nums[mid] > target)) right = mid - 1;
                else left = mid + 1;
            } else {
                if ((nums[mid] < target) && (nums[right] >= target)) left = mid + 1;
                else right = mid - 1;
            }
        }
        return false;
    }

}
