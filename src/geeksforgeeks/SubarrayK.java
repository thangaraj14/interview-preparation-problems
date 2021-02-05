package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
 */
public class SubarrayK {

    public static int subarraySum(int[] nums, int k) {
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int val : nums) {
            sum += val;
            result += preSum.getOrDefault(sum - k, 0);
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
        System.out.println(subarraySum(new int[] { 1, 2, 3, 4, }, 3));
        System.out.println(subarraysDivByK(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
    }
}
