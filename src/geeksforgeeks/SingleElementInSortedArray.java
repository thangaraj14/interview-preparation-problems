package geeksforgeeks;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Find this single element that appears only once.
 */
public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;
        // for 1,1,2,3,3,4,4,8,8
        // when mid is at 4 and right-mid is even, means the rest elements are only pairs, so we check         
        // left side by right=mid-2
        // when mid is at 3 and right-mid is odd means the 3's duplicate is present in right and rest           
        //are all pairs only so we check left side by right=mid-1
        // when mid is 4 => nums[mid]==nums[mid-1]
        // when mid is 3 => nums[mid]==nums[mid+1]
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean isEven = (right - mid) % 2 == 0;

            if (nums[mid] == nums[mid - 1]) {
                if (isEven) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if (isEven) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else {
                return nums[mid];
            }

        }

        return nums[left];
    }
}