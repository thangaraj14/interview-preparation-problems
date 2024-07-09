package bitwise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-excellent-pairs/
public class ExcellentList {
    /**
     https://leetcode.com/problems/number-of-excellent-pairs/solutions/2370663/c-logic-explained-with-pictures/
     * If there are x set bits in a number and y set bits in b number
     * then the OR component is x+y-k where k is the number of set bits common in a&b
     * the AND component is k
     * so the final answer (AND+OR) is x+y-k+k = x+y
     * @param nums
     * @param k
     * @return
     */
    public long countExcellentPairs(int[] nums, int k) {
        long[] count = new long[30]; // 30 instead of 32 because the constraint of the problem is
                                    // 1E9 which can be considered as 2^30
        Set<Integer> set = Arrays.stream(nums).collect(HashSet::new, Set::add, Set::addAll);
        for (int a: set){
            count[Integer.bitCount(a)]++;
        }
        long result = 0L;
        for (int i=1;i<30;++i){
            for (int j=1;j<30;++j){
                if (i+j >= k){
                    // the reason we are multiplying the count[i] and count[j] is because
                    // if we have 2 elements with i bits and 3 elements with j bits, then
                    // we can have 2*3 = 6 excellent pairs
                    /**
                     * Array: nums = [4, 6, 7, 10]
                     * k = 5
                     * Count Array:
                     * count[2] = 2 (two numbers with 2 1s: 4 and 6)
                     * count[3] = 1 (one number with 3 1s: 7)
                     * count[4] = 1 (one number with 4 1s: 10)
                     * Multiplication:
                     * count[2] * count[3] = 2 * 1 = 2 (pairs: (4, 7), (6, 7))
                     * count[2] * count[4] = 2 * 1 = 2 (pairs: (4, 10), (6, 10))
                     * Total potential excellent pairs: 4
                     */
                    result+=count[i]*count[j];
                }
            }
        }
        return result;
    }
}
