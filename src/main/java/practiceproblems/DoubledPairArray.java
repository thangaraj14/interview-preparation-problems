package practiceproblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/array-of-doubled-pairs
 *
 * tricky offset/sort
 */
public class DoubledPairArray {
    /**
     * Question is essentially asking to find a pair of doubles, in question they twisted it by providing a formula
     * arr[2 * i + 1] = 2 * arr[2 * i].
     * What the formula essentially means is find a pair for given A[i]
     *
     * if 2i == j or i == 2j (reverse to accommodate negatives) then we have solution
     *
     * The edge case here is that the elements may be negative in the array
     *
     * We greedily process elements starting from the smallest value, WHY smallest value but not an arbitrary value?
     *
     * Because since it's the smallest values, let say x, there is only one choice to pair with x:
     * If x is a positive number, then it pairs with y = x*2, for example: x = 4 pair with y = 8.
     * If x is a non-positive number, then it pairs with y = x/2, for example: x = -8 pair with y = -4.
     * (for arr = [-2, -1], -2 is the smallest, this need to be processed first, then it returns valid.)
     *
     * If there is no corresponding y then it's IMPOSSIBLE, return FALSE.
     * If it's an arbitrary value, let say x, there are two choices,
     * either x/2 or x*2 is also a good pairing with x (no matter if x is a possible or negative number),
     * if we choose x/2 or x*2 to pair with x, it maybe WRONG, because some other elements may need it to make pair.
     *
     * For example: arr = [2, 4, 1, 8]
     * If we process x = 2 first, then there are 2 choices,
     * either 4 or 1 can be paired with 2, if we choose 4 -> we got WRONG ANSWER.
     *
     * Because 8 needs 4, so 2 should be paired with 1.
     * So we need to sort our arr array first.
     *
     * When a pair of (x and y) match, we need to decrease their count.
     * So we need to use a HashTable data structure to count the frequency of elements in the arr array.
     *
     * @param arr
     * @return
     */
    public static boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        var map = new HashMap<Integer, Integer>();

        for (int x : arr) map.put(x, map.getOrDefault(x, 0) + 1);

        for (int elem : arr) {

            if (map.get(elem) == 0) continue;

            if (elem < 0 && elem % 2 != 0) return false;

            int value = elem < 0 ? elem / 2 : elem * 2;

            if (map.getOrDefault(value, 0) == 0) return false;

            map.put(elem, map.get(elem) - 1);
            map.put(value, map.get(value) - 1);

        }

        return true;
    }

    public static void main(String[] args) {
        canReorderDoubled(new int[]{4,-2,2,-4});
    }

    /**
     * The same approach as above but without sorting
     */
    public static boolean canReorderDoubledWithoutSorting(int[] A) {
        int offset=100000;
        // Count the frequency of each number
        int[] freq= new int[2*offset];
        for (int n: A) freq[n+offset]++;  // n+offset is to accommodate negative values inside array
                                          // -10 becomes 99990

        // deal with "0", there must be even 0s in array
        if (freq[0]%2==1) return false;

        // from -100000 to 100000
        for (int i=0; i<2*offset; i++){
            // if there's no current number left, skip
            if (freq[i]==0) continue;

            // otherwise, calculate current number, and it's pair number, then get their frequencies
            int cur= i-offset;
            int next= cur<0?cur/2:cur*2;

            int curCnt= freq[i];
            int nextCnt= freq[next+offset];

            // if not enough pair number left, return false
            if (nextCnt<curCnt) return false;

            // otherwise, update the frequency of pair number
            freq[next+offset]=nextCnt-curCnt;
        }
        return true;
    }
}
