package practiceproblems;

/**
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays
 * <p>
 * Basically it can be broken it into 2 cases: L is always before M vs M is always before L.
 *
 *
 * Really tricky problem but not too hard once you see the method
 * here is how I see it lets say you have the following array
 *       [2 , 1 , 5 , 6 , 0 , 9 , 5 , 0 , 3 , 8] with L = 3, M = 2
 * now lets look for our answer when M comes before L we will need to iterate the array like this
 *       [2 , 1 , 5 , 6 , 0 , 9 , 5 , 0 , 3 ]
 *       [   M  ][    L     ]
 *           [   M   ][     L   ]
 *               [   M  ][    L   ]
 *                   [   M  ][    L   ]
 *                      [   M  ][    L   ]
 *
 *   where we keep track of the MMax and at every iteration we sum up the mMax and the current L and keep track of the maxValue
 *   the reasoning behind this is that since the arrays cant intercept mMax will keep track of greatest value of M before our current L
 *
 * once we do it one way we now run the algorithm with M and L reversed
 *
 *
 *                 i-M-L        i-M      i
 *              1,  2,  3,  4,  5,  6,  7
 *                 |- - L - - -|- M- - |
 *  To get the lengths:
 *                L= (i-M)-(i-M-L): M= i-(i-M)
 *
 *
 * */
public class MaxSumTwoNonOverlappingSubArray {

    public static int maxSumTwoNoOverlapConcise(int[] A, int L, int M) {
        int[] prefixSum = new int[A.length + 1];
        for (int i = 0; i < A.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        /*
        maxSum(prefixSum, L, M) ==> find maximum sum for L length before index i and add it with every M length sum right to it
        maxSum(prefixSum, M, L) ==> find maximum sum for M length before index i and add it with every L length sum right to it
         */
        return Math.max(maxSum(prefixSum, L, M), maxSum(prefixSum, M, L));
    }

    private static int maxSum(int[] p, int L, int M) {
        int ans = 0;
        for (int i = L + M, maxL = 0; i < p.length; ++i) {
            maxL = Math.max(maxL, p[i - M] - p[i - M - L]); // update max of L-length sub-array.
            ans = Math.max(ans, maxL + p[i] - p[i - M]); // update max of the sum of L-length & M-length sub-arrays.
        }
        return ans;
    }

    public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        // L and M could be at left or right
        // so we need to calculate the both to get the max non-overlapping sum of entire array
        return Math.max(calculate(A, L, M), calculate(A, M, L));
    }

    private static int calculate(int[] A, int L, int M) {
        int sum = 0;
        int len = A.length;

        // calculate the prefix sum from A[0] to A[i]
        int[] prefixSum = new int[len];
        prefixSum[0] = A[0];
        for(int i = 1; i < len; i++) {
            prefixSum[i] = prefixSum[i-1] + A[i];
        }

        // calculate the maximum sum with length L with rightmost position at A[i], A[i] doesn't have to be included
        int[] leftSum = new int[len];
        leftSum[L-1] = prefixSum[L-1];
        for(int i = L; i < len; i++) {
            leftSum[i] = Math.max(leftSum[i-1], prefixSum[i] - prefixSum[i-L]);
        }

        // calculate the suffix sum from A[i] to A[len-1]
        int[] suffixSum = new int[len];
        suffixSum[len-1] = A[len-1];
        for(int i = len-2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i+1] + A[i];
        }

        // calculate the maximum sum with length M with leftmost position at A[i], A[i] doesn't have to be included
        int[] rightSum = new int[len];
        rightSum[len-M] = suffixSum[len-M];
        for(int i = len-M-1; i >= 0; i--) {
            rightSum[i] = Math.max(rightSum[i+1], suffixSum[i] - suffixSum[i+M]);
        }

        // now we have all the data for max sum with length L from the left
        // and max sum with length M from the right
        // just iterate and add them up to find the max non-overlapping sum
        // note the i+1 index is for non-overlapping
        int res = Integer.MIN_VALUE;
        for(int i = L-1; i <= len-M-1; i++) {
            res = Math.max(leftSum[i] + rightSum[i+1], res);
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(maxSumTwoNoOverlap(new int[]{0, 6, 5, 2, 2, 5, 1, 9, 4}, 1, 2));
    }
}
