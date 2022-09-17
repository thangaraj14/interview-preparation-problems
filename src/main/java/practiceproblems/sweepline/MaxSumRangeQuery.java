package practiceproblems.sweepline;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation
 * <p>
 * Idea is to find the frequencies/(number of times an index appear
 * in the requests) and then keep the maximum number from input array
 * in the max frequency index and so on.
 * <p>
 * Example: input array [1,2,3,4,5] requests [[1,3],[0,1]]
 * frequencyOfRequests =[1,2,1,1,0];
 * <p>
 * Since we are trying to get maximum sum we have to position max number in the input array in the index such that,
 * index has maximum frequency.
 * Now sort the input array and frequencyOfRequests so that we will have max number and max frequency in first indexes.
 * After sorting:
 * input array = [5,4,3,2,1]
 * frequencyOfRequests =[2,1,1,1,0];
 * <p>
 * for i in input array {
 * sum += inputArray[i] * frequencyOfRequests[i]
 * since sum can be very large and can cause overflow int; sum = sum % (10^9 + 7);
 * };
 * <p>
 * frequencyOfRequests can be found by iterating every request from start to end and mark the inputArray[index]++; in this case Time =                   * O(inputArray.size) * O(request.size) but with marking start index with +1 and (end + 1) index with -1 Time = (requests.size) * (1);
 * <p>
 * Time - r + n + nlog(n) + flog(f) + n -> O(nlog(n))
 * Space - O(n) for storing frequencies.
 */
public class MaxSumRangeQuery {

    public static int maxSumRangeQuery(int[] nums, int[][] requests) {

        int[] freq = new int[nums.length];
        long mod = (long) 1e9 + 7;
        long res = 0;
        int n = nums.length;
        /*
        Mark start and ends of requests with +1 and -1
        Imagine that you had intervals [1:3], [3:5].
        For each mark boundaries only to avoid complexity raise (TLE) on a wide interval as you do nothing with values
        inside the interval and ignore it quantity.
        t[start] +=1 means every index after this one will be counted 1 more time,
        t[end+1] -= 1, means every index this one will be counted 1 less time
        after [1:3] : [0, 1(+1), 0, 0, -1(-1), 0, 0]
        after [3:5] : [0, 1, 0, 1(+1), -1, 0, -1(-1)]
        Then after all intervals start|end+1 marking we can set the final frequency in one pass.
        So the prefix sum of t will correspond to the number of requests for each index.
        for i in range(1, n):
        count[i] += count[i - 1]
        It will become
        [0, 1, 0, 1, -1, 0, -1] -> [0, 1, 1, 2, 1, 1, 0]
        As you can see 3rd element occurred 2 times 1,2,4,5 1 time and others 0 time
        */
        for (int[] r : requests) {
            freq[r[0]] += 1;
            if (r[1] + 1 < n)
                freq[r[1] + 1] -= 1;
        }
        for (int i = 1; i < n; ++i)
            freq[i] += freq[i - 1];
        Arrays.sort(nums);
        Arrays.sort(freq);
        for (int i = 0; i < n; i++)
            res += (long) nums[i] * freq[i];
        return (int) (res % mod);

    }

    public static void main(String[] args) {
        maxSumRangeQuery(new int[]{1, 2, 3, 4, 5}, new int[][]{{1, 3}, {0, 1}});
    }
}
