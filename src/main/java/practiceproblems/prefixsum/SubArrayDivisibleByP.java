package practiceproblems.prefixsum;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/make-sum-divisible-by-p/
 * <p>
 * Calculate the rem = sum(nums) % p, which means we need to remove a subarray which has sum % p == rem to make the rest sum divisible by p.
 * It also requires that the removed subarray should be the shortest subarray.
 * Then the questions become: Find the shortest array with sum(subarray) % p == rem.
 * Since we need the shortest length, last[remainder] = idx records the last index that (A[0] + A[1] + ... + A[i]) % p == remainder.
 * Everytime, we find a possible sum, we need get the right-most index to make sure that the subarray removed is the shortest.
 * If there is no element what we want, then i - (-N) > N. Since we cannot remove the whole array, therefore, return res when res < N.
 */
public class SubArrayDivisibleByP {

    public static int minSubarray(int[] nums, int p) {

        int remainder = 0, prefixSum = 0;
        for (int num : nums) {
            remainder = (remainder + num) % p;
        }

        if (remainder == 0) return 0;


        int result = nums.length;
        var prefixSumToIndex = new HashMap<Integer, Integer>();
        prefixSumToIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixSum = (prefixSum + nums[i]) % p;

            prefixSumToIndex.put(prefixSum, i);

            int key = (prefixSum - remainder + p) % p; //Avoid the case where prefixSum - remainder is negative

            if (prefixSumToIndex.containsKey(key)) {
                result = Math.min(result, i - prefixSumToIndex.get(key));
            }


        }

        return result == nums.length ? -1 : result;

    }

    public static void main(String[] args) {
        minSubarray(new int[]{3,1,4,2},6);
    }
}
