package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSubarrMinProduct {
    /**
     * The main idea is
     * As we want to find min-product, calculating minimum number from all sub arrays gives us TLE
     * So what we do is, we consider nums[i] where 0<=i< n,
     * as min element and find the min-products of subarrays with minimum as nums[i].
     *
     * left_max_index: index of the farthest element greater or equal to nums[i] in the left side
     * right_max_index: index of the farthest element greater or equal to nums[i] in the right side
     *
     * Then we find the prefix sum of given input array.
     * Then we find the number of largest numbers to the left in "array left" (left_max_index)
     * and number of largest numbers to the right in "array right" with nums[i] as minimum. (right_max_index)
     *
     * After finding that, now its become simple, as we know the length of sub array with nums[i] as minimum.
     *
     * nums = [3,1,5,6,4,2]
     * <p>
     * indices:     [0, 1, 2, 3, 4, 5]
     * input:       [3, 1, 5, 6, 4, 2]
     * left:        [0, 0, 2, 3, 2, 2]
     * right:       [0, 5, 3, 3, 4, 5]
     * prefixSum:   [3, 4, 9, 15, 19, 21]
     */
    public int maxSumMinProductEff(int[] nums) {
        int n = nums.length;
        long max = -1;
        long[] prefix = new long[n + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        int[] leftMaxIndex = new int[n];
        int[] rightMaxIndex = new int[n];
        for (int i = 0; i < n; i++) {
            while (!s1.isEmpty() && nums[s1.peek()] >= nums[i]) {
                s1.pop();
            }
            if (s1.isEmpty()) {
                leftMaxIndex[i] = -1;
            } else {
                leftMaxIndex[i] = s1.peek();
            }
            s1.push(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!s2.isEmpty() && nums[s2.peek()] >= nums[i]) {
                s2.pop();
            }
            if (s2.isEmpty()) {
                rightMaxIndex[i] = n;
            } else {
                rightMaxIndex[i] = s2.peek();
            }
            s2.push(i);
        }

        for (int i = 0; i < n; i++) {
            int leftIndex = leftMaxIndex[i];
            int rightIndex = rightMaxIndex[i];
            int min = nums[i];

            // range sum technique
            long subArraySum = prefix[rightIndex] - prefix[leftIndex + 1];
            long maxSubArrMinProduct = min * subArraySum;
            max = Math.max(max, maxSubArrMinProduct);
        }
        return (int) (max % 1_000_000_007);
    }


    public static int maxSumMinProduct(int[] nums) {
        int mod = (int) Math.pow(10, 9) + 7;

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {

                min = Math.min(nums[j], min);
                sum += nums[j];

                result = Math.max(result, sum * min);
            }

            System.out.println(result);
        }

        return result % mod;
    }
}
