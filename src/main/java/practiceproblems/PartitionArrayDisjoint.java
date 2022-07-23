package practiceproblems;

/**
 * https://leetcode.com/problems/partition-array-into-disjoint-intervals
 */
public class PartitionArrayDisjoint {

    /**
     * We need to divide the array into 2 parts such that max element on the left side is smaller or equal to every element on the right
     * We can maintain a maxLeft to keep track of this.
     * Now if we come across any number num[i] which is smaller than the maxLeft, this number cannot be part of the right side.
     * Thus we need to update the maxLeft and the index of our partition p
     * Regarding updating our index, its straight forward to update to i since the ith element has to be part of left side.
     * Now what would be the updated maxLeft.
     * This has to simply be the value of the maximum element encountered so far
     * since this element was present before ith index so has to be the maxLeft now.
     * Thus we will keep track of a max and update maxLeft to this value whenever we encounter a smaller number than maxLeft
     *
     * @param nums
     * @return
     */
    public int partitionDisjointEff(int[] nums) {
        int maxLeft = nums[0];
        int max = nums[0];
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < maxLeft) {
                maxLeft = max;
                p = i;
            } else if (max < nums[i]) {
                max = nums[i];
            }
        }
        return p + 1;
    }

    public int partitionDisjoint(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = nums[0];

        //If the maximum from left array is less than minimum from right array then we can make a split at that idx.
        for (int i = 1; i < nums.length; i++) {
            left[i] = Math.max(left[i - 1], nums[i]);
        }

        right[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = Math.min(nums[i], right[i + 1]);
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (left[i] <= right[i + 1]) return i + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionArrayDisjoint().partitionDisjointEff(new int[]{5, 0, 3, 8, 6, 1,9}));
    }
}
