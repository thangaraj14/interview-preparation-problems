package practiceproblems.sweepline;

/**
 * https://leetcode.com/problems/range-addition/
 * https://leetcode.com/problems/corporate-flight-bookings/
 *
 * e.g. n =5 , [1,3,2] [2,4,3] [0,2,-2]
 *
 * idx:        0       1       2       3       4
 * nbr:        1       2       3       4       5
 * --------------------------------------------------------
 * [1,3,2]            +2                      -2
 * [2,4,3]                     +3                      -3
 * [0,2,-2]   -2                      +2
 * --------------------------------------------------------
 * Sum:       -2       2        3      2      -2
 * PrefixSum: -2       0        3      5       3
 */
public class ModifyArray {

    public int[] getModifiedArray(int length, int[][] updates) {

        int[] result = new int[length];

        for (int[] update : updates) {
            int val = update[2];
            int start = update[0];
            int end = update[1];

            result[start] += val;
            if (end < length - 1) {
                result[end + 1] -= val;
            }
        }


        for (int i = 1; i < length; i++) {
            result[i] += result[i - 1];
        }
        return result;
    }
}
