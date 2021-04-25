package dsa;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/4sum-ii/
 */
public class FourSum {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> sums = new HashMap<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                sums.put(sum, sums.getOrDefault(sum, 0) + 1);

            }
        }
        
        for (int k = 0; k < A.length; k++) {
            for (int z = 0; z < C.length; z++) {
                // if above sum is +3, then we are looking for -3, so we are negating the value.
                int sum = -(C[k] + D[z]);
                if (sums.containsKey(sum)) {
                    count += sums.get(sum);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = { 1, 2 };
        int[] B = { -2, -1 };
        int[] C = { -1, 2 };
        int[] D = { 0, 2 };

        FourSum fs = new FourSum();
        fs.fourSumCount(A, B, C, D);
    }
}
