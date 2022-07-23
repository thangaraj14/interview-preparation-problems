package practiceproblems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two binary arrays arr1[] and arr2[] of same size n.
 * Find length of the longest common span (i, j)
 * where j >= i such that arr1[i] + arr1[i+1] + …. + arr1[j] = arr2[i] + arr2[i+1] + …. + arr2[j].
 * <p>
 * Expected time complexity is Θ(n).
 * <p>
 * Input: arr1[] = {0, 1, 0, 0, 0, 0};
 * arr2[] = {1, 0, 1, 0, 0, 1};
 * Output: 4
 * The longest span with same sum is from index 1 to 4.
 * <p>
 * Input: arr1[] = {0, 1, 0, 1, 1, 1, 1};
 * arr2[] = {1, 1, 1, 1, 1, 0, 1};
 * Output: 6
 * The longest span with same sum is from index 1 to 6.
 */
class LongestSpanWithSameSumArray {
    // Returns largest common subarray with equal
    // number of 0s and 1s
    static int longestCommonSum(int[] arr1, int[] arr2, int n) {
        // Find difference between the two
        int[] arr = new int[n];
        // the reason we take the difference is, the resultant array
        // will only contain 3 values 0,1,-1, checking for zero sum
        // on the resultant array means we get the longest span where elements are same
        for (int i = 0; i < n; i++)
            arr[i] = arr1[i] - arr2[i];

        // Creates an empty hashMap hM
        Map<Integer, Integer> hM = new HashMap<>();

        int sum = 0;     // Initialize sum of elements
        int max_len = 0; // Initialize result

        // Traverse through the given array
        for (int i = 0; i < n; i++) {
            // Add current element to sum
            sum += arr[i];

            // To handle sum=0 at last index
            if (sum == 0)
                max_len = i + 1;

            // If this sum is seen before,
            // then update max_len if required
            if (hM.containsKey(sum))
                max_len = Math.max(max_len, i - hM.get(sum));

            else // Else put this sum in hash table
                hM.put(sum, i);
        }
        return max_len;
    }


    public static void main(String args[]) {
       /* int[] arr1 = {0, 1, 0, 1, 1, 1, 1};
        int[] arr2 = {1, 1, 1, 1, 1, 0, 1};*/
        int arr1[] = {0, 1, 0, 0, 1, 1, 1, 0};
        int arr2[] = {1, 1, 1, 1, 1, 1, 0, 1};
        //{-1,0,-1,0,0,1,0}
        int n = arr1.length;
        System.out.println(longestCommonSum(arr1, arr2, n));
    }
}