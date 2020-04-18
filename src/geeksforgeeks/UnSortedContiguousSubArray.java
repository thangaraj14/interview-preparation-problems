package geeksforgeeks;

class UnSortedContiguousSubArray {
    public int findUnsortedSubarray(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i < nums.length - 1) {
            if (nums[i] > nums[i + 1]) {
                break;
            }
            i++;
        }
        if (i >= j)
            return 0;

        while (j > 0) {
            if (nums[j] < nums[j - 1]) {
                break;
            }
            j--;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int k = i; k <= j; k++) {
            min = Math.min(min, nums[k]);
            max = Math.max(max, nums[k]);
        }

        while (i >= 0 && min < nums[i])
            i--;
        while (j < nums.length && max > nums[j])
            j++;

        return j - i - 1;

    }

}