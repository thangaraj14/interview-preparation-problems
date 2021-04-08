package geeksforgeeks;

/**
 * 303. Range Sum Query - Immutable
 *
 * Given an integer array nums,
 * find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int i, int j) Return the sum of the elements of the nums array in the range [i, j]
 * inclusive (i.e., sum(nums[i], nums[i + 1], ... , nums[j]))
 *
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 *
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
public class RangeSum {
    int[] dp;
    public void NumArray(int[] nums) {
        if(nums.length==0) return ;
        dp= new int[nums.length];
        dp[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i]=dp[i-1]+nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        if(i==0) return dp[j];
        return dp[j]-dp[i-1];
    }
}