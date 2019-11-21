package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/find-zeroes-to-be-flipped-so-that-number-of-consecutive-1s-is-maximized/
 * <p>
 * https://www.techiedelight.com/find-maximum-sequence-of-continuous-1s-can-formed-replacing-k-zeroes-ones/
 */
class FlipZeroesToFormConsecutiveMaximumOnes {

    public static void longestSeq(int[] A, int k) {
        int left = 0;
        int count = 0;
        int window = 0;

        int leftIndex = 0;

        // 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0
        for (int right = 0; right < A.length; right++) {
            // if current element is 0, increase count of zeros in the
            // current window by 1
            if (A[right] == 0) {
                count++;
            }

            // window becomes unstable if number of zeros in it becomes
            // more than
            while (count > k) {
                // if we have found zero, decrement number of zeros in the
                // current window by 1
                if (A[left] == 0) {
                    count--;
                }

                left++;
            }

            // when we reach here, the window [left..right] contains at-most
            // k zeroes and we update max window size and leftmost index
            // of the window
            if (right - left + 1 > window) {
                window = right - left + 1;
                leftIndex = left;
            }
        }

        System.out.println("The longest sequence has length " + window + " from index " + leftIndex + " to "
                + (leftIndex + window - 1));
    }

    // main function
    public static void main(String[] args) {
        int[] A = {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0};
        int k = 1;

        longestSeq(A, k);
    }
}
