package practiceproblems;

/**
 * https://www.geeksforgeeks.org/maximum-product-subarray/
 */
public class MaximumProductSubarray {

    // 1, -2, -3, 0, 8, 7, -2
    public static int maxProductSubArray(int[] A) {

        if (A.length == 0) {
            return 0;
        }

        int maxHerePre = A[0];
        int minHerePre = A[0];
        int maxsofar = A[0];

        for (int i = 1; i < A.length; i++) {
            /**
             * maxHere is updated by taking the maximum value among:
             *
             *     Current number:
             *         This value will be picked if the accumulated product has been awful (even compared to the current number).
             *         This can happen when the current number has a preceding zero (e.g. [0,4]) or is preceded by a single negative number (e.g. [-3,5]).
             *
             *     Product of last max_so_far and current number:
             *         This value will be picked if the accumulated product has been steadily increasing (all positive numbers).
             *
             *     Product of last min_so_far and current number:
             *         This value will be picked if the current number is a negative number
             *         and the combo chain has been disrupted by a single negative number before
             *         (In a sense, this value is like an antidote to an already poisoned combo chain).
             */
            int maxHere = Math.max(Math.max(maxHerePre * A[i], minHerePre * A[i]), A[i]);
            int minHere = Math.min(Math.min(maxHerePre * A[i], minHerePre * A[i]), A[i]);
            maxsofar = Math.max(maxHere, maxsofar);
            maxHerePre = maxHere;
            minHerePre = minHere;
        }
        return maxsofar;
    }

    public static MaxSubarray getMaxSubarray(int[] inputArr) {
        MaxSubarray result;
        int start = 0;
        int end = 0;
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        int actualStart = 0;
        for (int i = 0; i < inputArr.length; i++) {

            currSum += inputArr[i];

            if (currSum > maxSum) {
                maxSum = currSum;
                actualStart = start;
                end = i;
            }

            if (currSum < 0) {
                currSum = 0;
                start = i + 1;
            }
        }
        if (start > end) {
            start = end;
        }

        result = new MaxSubarray(maxSum, actualStart, end);
        return result;

    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxHere = 0;
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            maxHere = Math.max(i, maxHere + i);
            max = Math.max(maxHere, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int arr[] = {1, -2, -3, 0, 8, 7, -2};
        System.out.println("Maximum Sub array sum is " + maxSubArray(arr));
        System.out.println("Maximum Sub array product is " + maxProductSubArray(arr));
    }

    /**
     * Simplest case.
     * Consider this array [1,2,3,-4,5,6].
     * We can think of -4 as dividing the array into 2 halves, [1,2,3] and [5,6].
     * The forward traversal yields the max as 6, while the reverse traversal yields 30.
     * <p>
     * Say the array has even number of negative numbers eg. [1,2,-3,-4,5,6].
     * Both forward and reverse traversals yield the same result, so it doesnt matter.
     * <p>
     * Say the array has multiple odd number of negative integers. eg. [1,2,-3,-4,-5, 6].
     * We can think of the "last" negative number in each traversal breaks the array to 2 halves.
     * In this case , the max array in forward traversal is the maximum of ([1,2,-3,-4] and [6]) which is 24.
     * In the reverse, the split is delimited by -3. So the max subarrray is teh maximum of ([6,-5,-4] and [2])
     * <p>
     * Hence, in the end, its all about the presence of odd or even number of negative integers.
     * In case of even, the product is always positive. In case of odd, the max product is limited by the last negative integer in each traversal.
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        int n = nums.length;
        double left = 1;
        double right = 1;
        double ans = nums[0];
        for (int i = 0; i < n; i++) {
            left = left == 0 ? 1 : left;
            right = right == 0 ? 1 : right;

            left *= nums[i];
            right *= nums[n - i - 1];
            ans = Math.max(ans, Math.max(left, right));
        }
        return (int) ans;

    }

    static class MaxSubarray {
        int maxSum;
        int startIndex;
        int endIndex;

        public MaxSubarray(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}


