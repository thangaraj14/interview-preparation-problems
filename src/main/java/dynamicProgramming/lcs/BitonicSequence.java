package dynamicProgramming.lcs;

/**
 * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 *
 * tricky LIS
 */
public class BitonicSequence {
    /**
     * Concept: We need to find the maximum number of elements of the array that can be
     * involved in a mountain array. We know, that a mountain array contains a peak element
     * and there is an increasing subsequence in the left of the peak and a decreasing subsequence in the right.
     * So, we need to find out the element(peak), for which the total number of elements from the
     * original array involved in the left increasing subsequence and the right decreasing
     * subsequence, in maximum. This will create a mountain array with the peak element.
     * Then, we can delete the rest of the elements of the array not involved in this mountain array.
     */
    public int longestSequence(int arr[]) {
        int[] lis = new int[arr.length];
        int[] lds = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lis[i] = 1;
            lds[i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        // because that middle element is common in both sequence .. for example,
        // increasing subsequence 2,8,20 .. decreasing one 20,13,14 .. each of them has
        // length 3 .. but bitonic subsequence 2,8,20,13,14 .. length= 3+3-1=5
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
			 /*
		       If the below conditional statement is not given, then strictly increasing or strictly
			   decreasing sequences will also be considered. It will hence fail in,
			   Test case: [10, 9, 8, 7, 6, 5, 4, 5, 4].
					---Thanks to @chejianchao for suggesting the test case.
				We need to make sure both the LIS on the left and right, ending at index i,
				has length > 1.
		   */
            int max1 = lis[i] + lds[i] - 1; //Peak is counted twice in lis[] and lsd[] so -1
            System.out.print(max1 + " ");
            if (max < max1) {
                max = max1;
            }
        }

        return max;
    }

    public static void main(String args[]) {
        BitonicSequence bs = new BitonicSequence();
        int[] arr = {1, 4, 3, 7, 2, 1, 8, 11, 13, 0};
        int r = bs.longestSequence(arr);
        System.out.println(r);

    }
}