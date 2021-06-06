package dsa;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * <p>
 * // Let’s assume (D+E+3=k)
 * // sum =A+B+C+D+E+3
 * // preSum = A+B+C
 * // Thus, we can compose critical equation
 * // sum - preSum = k
 * // Since we’re looking for specific preSum to compose value k
 * // What specific preSum we're looking for?
 * // Some math operation results in
 * // sum - k = preSum
 * // For multiple matching counts
 * // Ex:
 * // input [0,-1,1,2,3] k=5
 * // We notice that it’s necessary to record occurrence of specific preSum.
 * // In this case, we need to know preSum 2 occur three times. [0,-1,1,2], [-1,1,2], [2]
 * // This indicate that it's necessary to record preSum counts
 */
public class SubarrayK {

    public static int subarraySum(int[] nums, int k) {
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int val : nums) {
            sum += val;
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static int subarraysDivByK(int[] nums, int K) {
        int prefix = 0;
        int result = 0;
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        for (int a : nums) {
            //(prefix+a%K+K)%K is just a trick to make the remainder positive.
            prefix = (prefix + (a % K) + K) % K;
            result += count.getOrDefault(prefix, 0);
            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[] { 1, 0, 1, 2, -1 }, 2));
        System.out.println(subarraysDivByK(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
    }
}
