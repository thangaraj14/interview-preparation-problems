package dynamicProgramming.lcs;

/**
 *
 * http://tinyurl.com/y4mffrn7
 */
public class MaximumContiguousSubarraySum {

	public static void main(String[] args) {
		int[] arr2 = { -6, 2, -4, 1, 3, -1, 5, -1 };
		MaximumContiguousSubarraySum mcs = new MaximumContiguousSubarraySum();
		System.out.println(mcs.maxSubArray(arr2));

	}

	public int maxSubArray(int[] nums) {

		int maxSoFar = nums[0];
		int maxEndingHere = nums[0];

		for (int i = 1; i < nums.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

}
