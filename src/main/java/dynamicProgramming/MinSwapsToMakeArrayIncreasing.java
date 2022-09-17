package dynamicProgramming;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 */
public class MinSwapsToMakeArrayIncreasing {
    Integer[][] dp;
    final int MAX = 10_000;

    /**
     * There are two states possible,
     * <p>
     * Don't swap elements at the current index
     * Swap elements at the current index
     * <p>
     * We just have to find out which one gives the minimum number of swaps for the rest of the array.
     * That is, we will compute answer for both the states.
     * The answer for the current state is dependent on the relation between the element at the current index
     * and the previous index.
     * <p>
     * If they are already in increasing order, t
     * hen the state for the current index is applied to the previous index
     * (that is, no swap remains no swap, swap remains swap).
     * Else, the state for the current index is reversed for the previous index.
     * But, what if swap and no swap both achieve the increasing order? In this case,
     * we take the minimum of both states from the previous index.
     *
     * @param A
     * @param B
     * @return
     */
    public int minSwapTopDown(int[] A, int[] B) {
        dp = new Integer[A.length][2];
        return minSwapHelper(A, B, 0, -1, -1, 0);
    }

    private int minSwapHelper(int[] A, int[] B, int i, int prevA, int prevB, int swapped) {

        if (i == A.length) return 0;
        if (dp[i][swapped] != null) return dp[i][swapped];

        int minSwaps = MAX;

        if (A[i] > prevA && B[i] > prevB) { // with-no-swap
            minSwaps = minSwapHelper(A, B, i + 1, A[i], B[i], 0);
        }

        if (B[i] > prevA && A[i] > prevB) { // with-swap
            minSwaps = Math.min(minSwaps, minSwapHelper(A, B, i + 1, B[i], A[i], 1) + 1);
        }

        dp[i][swapped] = minSwaps;
        return minSwaps;
    }

    /**
     * https://www.youtube.com/watch?v=mLTF2UXkd2o
     * @return
     */
    public static int minSwap(int[] A, int[] B) {

        int[] noSwap = new int[A.length];
        int[] swap = new int[A.length];
        swap[0] = 1;
        for (int i = 1; i < A.length; ++i) {

            /** // This is max value, could be anything as long as they are higher
             // than max possible value (which would be A.length-1, since max
             // swaps you can do is A.length-1)*/
            noSwap[i] = A.length;
            swap[i] = A.length;

            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                /**
                 *  We are here because this index does not need to swap.
                 // Great, what would be the cost to reach here and not swap?
                 // It'll be same as cost of not swapping for prev. index.
                 // Why dont we rather take min(noSwap[i-1], swap[i-1]), because
                 // in that case noSwap[i-1] will be min anyway.
                 */
                noSwap[i] = noSwap[i - 1];


                /** // what would be the cost to reach here and swap?
                 // It'll be cost of swapping for prev. index + 1.
                 // Why dont we rather take min(noSwap[i-1], swap[i-1]) + 1, because
                 // we are tracking optimistic swaps and if we do min, we will lose
                 // track of swaps that were needed and done in past.
                 */
                swap[i] = swap[i - 1] + 1;

            }
            /**
             * In this case, if we want to keep A and B increasing before the index i, can only have two choices.
             * -> 2.1 swap at (i-1) and do not swap at i, we can get notswap[i] = Math.min(swap[i-1], notswap[i] )
             * -> 2.2 do not swap at (i-1) and swap at i, we can get swap[i]=Math.min(notswap[i-1]+1, swap[i])
             */
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                noSwap[i] = Math.min(noSwap[i], swap[i - 1]);
                swap[i] = Math.min(noSwap[i - 1] + 1, swap[i]);
            }
        }

        // Now that we cost of swapping and not swapping each index,
        // answer is:
        return Math.min(noSwap[A.length - 1], swap[A.length - 1]);
    }

    public static void main(String[] args) {

        System.out.println(minSwap(new int[]{0, 4, 4, 5, 9}, new int[]{0, 1, 6, 8, 10}));
    }
}
