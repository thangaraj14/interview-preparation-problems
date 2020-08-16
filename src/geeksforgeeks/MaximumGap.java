package geeksforgeeks;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
Return 0 if the array contains less than 2 elements.
Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.


trick is to do in O(N)= > Radix sort
 */
public class MaximumGap {
    // The first step is to find the maximum value in nums array, it will
    // be the threshold to end while loop.
    // Then use the radix sort algorithm to sort based on each digit from Least Significant Bit
    // (LSB) to Most Significant Bit (MSB), that's exactly what's showing
    // in the link.
    // (nums[i] / exp) % 10 is used to get the digit, for each digit, basically the digit itself serves as the index to
    // access the count array. Count array stores the index to access aux
    // array which stores the numbers after sorting based on the current
    // digit.
    // Finally, find the maximum gap from sorted array.
    // Time and space complexities are both O(n). (Actually time is O(10n) at worst case for Integer.MAX_VALUE 2147483647)
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // m is the maximal number in nums
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }

        int exp = 1; // 1, 10, 100, 1000 ...
        int R = 10; // 10 digits

        int[] aux = new int[nums.length];

        while (m / exp > 0) { // Go through all digits from LSB to MSB
            int[] count = new int[R];

            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % 10]++;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }

            for (int i = 0; i < nums.length; i++) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }

        int max = 0;
        for (int i = 1; i < aux.length; i++) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }

        return max;
    }
}