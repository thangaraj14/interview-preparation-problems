package practiceproblems.prefixsum;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/k-divisible-elements-subarrays/
 *
 * It's tempting to apply a sliding window technique here, however, it won't help us identify distinct subarrays.
 * Because constraints are low (n <= 200), we can just identify all valid subarrays O(n ^ 2),
 * and use a set to dedup them O(n). So, the overall complexity would be O(n ^ 3).
 *
 * For O(n ^ 2) solution, we can use a rolling hash (Rabin-Karp).
 * Note that we only need to check hashes for arrays of the same size, which reduces the collision probability.
 *
 * Rolling Hash (Simple)
 * We need to do the collision check, but here I omitted it for simplicity (see third solution below if you want to handle collisions).
 */
public class UniqueSubArrayDivisibleByK {

    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        // we are storing hashcode for all the substrings so that we can compare them faster.
        // main goal is to avoid entire sub array comparision using hashcode.
        Set<Long> ways = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int cnt = 0;
            long hc = 1; // this is the running hashcode for sub array [i...j]
            for(int j = i; j < n; j++) {
                hc = 199L * hc + nums[j]; // updating running hashcode, since we nums are <=200, we shall consider a prime near 200 to avoid collision
                if(nums[j] % p == 0)
                    cnt++;
                if(cnt <= k) { // if current subarray [i...j] is valid, add its hashcode in our storage.
                    ways.add(hc);
                }
            }
        }
        return ways.size();
    }
    // for (int i = 0; i < n; i++) {
    //            StringBuilder sb = new StringBuilder();
    //            int t = 0;
    //            for (int j = i; j < n ; j++) {
    //                sb.append(nums[j] + ",");
    //                if(nums[j] % p == 0){
    //                    t++;
    //                }
    //                if(t > k){
    //                    break;
    //                }
    //
    //                set.add(sb.toString());
    //            }
    //        }

    public static void main(String[] args) {
        UniqueSubArrayDivisibleByK uniqueSubArrayDivisibleByK = new UniqueSubArrayDivisibleByK();
        System.out.println(uniqueSubArrayDivisibleByK.countDistinct(new int[]{2,3,3,2,2}, 2, 2));
        System.out.println(uniqueSubArrayDivisibleByK.countDistinct(new int[]{1, 2, 3, 4}, 4, 1));
    }
}
