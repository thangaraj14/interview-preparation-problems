package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int left = 0;

        int sum = 0;
        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        // Let’s assume (D+E+3=k)
        // sum =A+B+C+D+E+3
        // preSum = A+B+C
        // Thus, we can compose critical equation
        // sum - preSum = k
        // Since we’re looking for specific preSum to compose value k
        // we can re-arrange the above equation like below
        // sum - k = preSum
        // For multiple matching counts
        // Ex:
        // input [0,-1,1,2,3] k=5
        // We notice that it’s necessary to record occurrence of specific preSum.
        // In this case, we need to know preSum 2 occur three times. [0,-1,1,2], [-1,1,2], [2]
        // This indicate that it's necessary to record preSum counts

        while (left < nums.length) {
            sum += nums[left];
            if (map.get(sum - k) != null) {
                result += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            left++;

        }
        return result;

    }

    public static int subarraySumDivByK(int[] nums, int K) {
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
}