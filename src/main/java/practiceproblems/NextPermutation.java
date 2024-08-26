package practiceproblems;

/**
 * https://leetcode.com/problems/next-permutation
 * https://www.youtube.com/watch?v=LuLCLgMElus
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0)
            return;

        //Find the break-point, i:first index i from the back of the given array arr[i] becomes smaller than arr[i+1].
        //For example, if the given array is {2,1,5,4,3,0,0}, the break-point will be index 1(0-based indexing).
        // Here from the back of the array, index 1 is the first index where arr[1] i.e. 1 is smaller than arr[i+1] i.e. 5.
        int idx = -1;
        int n = nums.length;
        // the reason for starting backwards is we need to find a sub-array in descending order
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            //If such a break-point does not exist i.e. if the array is sorted in decreasing order,
            // the given permutation is the last one in the sorted order of all possible permutations.
            // So, the next permutation must be the first i.e. the permutation in increasing order.
            //So, in this case, we will reverse the whole array and will return it as our answer.
            for (int i = 0; i < n / 2; i++) {
                swap(nums, i, n - i - 1);
            }
            return;
        }

        //Find the smallest number i.e. > arr[i] and in the right half of index i
        // (i.e. from index i+1 to n-1) and swap it with arr[i].
        for (int i = n - 1; i >= idx; i--) {
            if (nums[i] > nums[idx]) {
                swap(nums, i, idx);
                break;
            }
        }

        //Reverse the entire right half(i.e. from index i+1 to n-1) of index i. And finally, return the array.
        int start = idx + 1;
        int end = n - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
        return;

    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
