package binarysearch;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Find this single element that appears only once.
 */
public class SingleElementInSortedArray {

    /**
     * The single element is at the first even index not followed by its pair. We used this property in the linear search algorithm,
     * where we iterated over all the even indexes until we encountered the first one not followed by its pair.
     *
     * for (int i = 0; i < nums.length - 1; i+=2) {
     *             if (nums[i] != nums[i + 1]) {
     *                 return nums[i];
     *             }
     *         }
     *
     * After the single element, the pattern changes to being odd indexes followed by their pair.
     * This means that the single element (an even index) and all elements after it are even indexes not followed by their pair.
     * Therefore, given any even index in the array, we can easily determine whether the single element is to the left or to the right.
     *
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicateElegant(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            //We need to make sure our mid index is even.
            // We can do this by dividing lo and hi in the usual way, but then decrementing it by 1 if it is odd.
            // This also ensures that if we have an even number of even indexes to search,
            // that we are getting the lower middle (incrementing by 1 here would not work,
            // it'd lead to an infinite loop as the search space would not be reduced in some cases).
            if (mid % 2 == 1) mid--;

            //Then we check whether the mid-index is the same as the one after it.
            //If it is, then we know that mid is not the single element, and that the single element must be at an even index after mid.
            //Therefore, we set lo to be mid + 2. It is +2 rather than the usual +1 because we want it to point at an even index.
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                //If it is not, then we know that the single element is either at mid, or at some index before mid.
                // Therefore, we set hi to be mid.
                hi = mid;
            }
        }
        return nums[lo];
    }



    /**
     * The key observation to make is that the starting array must always have an odd number of elements (be odd-lengthed),
     * because it has one element appearing once, and all the other elements appearing twice.
     * [1,1,4,4,5,5,6,6,8,9,9]
     * Here is what happens when we remove a pair(5,5) from the center. We are left with a left subarray and a right subarray.
     * [1,1,4,4]**[6,6,8,9,9]
     * <p>
     * Like the original array, the subarray containing the single element must be odd-length-ed.
     * The subarray not containing it must be even-length-ed.
     * So by taking a pair out of the middle and then calculating which side is now odd-length-ed,
     * we have the information needed for binary search.
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            boolean halvesAreEven = (right - mid) % 2 == 0;


            if (nums[mid] == nums[mid + 1]) {
                //Case 1: Mid’s partner is to the right, and the halves were originally even.
                //The right side becomes odd-lengthed because we removed mid's partner from it.
                // We need to set left to mid + 2 so that the remaining array is the part above mid's partner.
                //[1,1,4,4,$5,$5,6,8,8]  $=removed item
                if (halvesAreEven) {
                    left = mid + 2;
                } else {
                    //Case 2: Mid’s partner is to the right, and the halves were originally odd.
                    //The left side remains odd-lengthed. We need to set hi to mid - 1 so that the remaining array is the part below mid.
                    //[1,1,4,5,5,$6,$6,8,8,9,9] $=removed item
                    right = mid - 1;
                }
            } else if (nums[mid] == nums[mid - 1]) {
                //Case 3: Mid’s partner is to the left, and the halves were originally even.
                //The left side becomes odd-lengthed because we removed mid's partner from it.
                // We need to set hi to mid - 2 so that the remaining array is the part below mid's partner.
                //[1,1,4,$5,$5,6,6,8,8]  $=removed item
                if (halvesAreEven) {
                    right = mid - 2;
                } else {
                    //Case 4: Mid’s partner is to the left, and the halves were originally odd.
                    //The right side remains odd-lengthed. We need to set lo to mid + 1 so that the remaining array is the part above mid.
                    //[1,1,4,4,$5,$5,6,6,8,9,9]
                    left = mid + 1;
                }
            } else {
                return nums[mid];
            }

        }
        return nums[left];
    }
}