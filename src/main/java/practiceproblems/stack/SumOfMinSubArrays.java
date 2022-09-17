package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 * <p>
 * https://www.youtube.com/watch?v=Ulb3ixSpE4Y&ab_channel=AnuragCodes
 */
public class SumOfMinSubArrays {

    public static void main(String[] args) {
        sumSubarrayMins(new int[]{5, 3, 4, 1, 2, 7});
    }

    public static int sumSubarrayMins(int[] arr) {
        int length = arr.length;
        long mod = (long) 1e9 + 7;
        long result = 0;
        Deque<int[]> previousLess = new ArrayDeque<>();
        Deque<int[]> nextLess = new ArrayDeque<>();
        int[] left = new int[length];
        int[] right = new int[length];
        // previous less element - imagine 2, 5, 6, 5 you don't want to double count so you can either enforce the = on the left or right array.
        for (int i = 0; i < length; i++) {
            while (!previousLess.isEmpty() && previousLess.peek()[0] >= arr[i]) {
                previousLess.pop();
            }
            // i - previousLess.peek()[1] similar to histogram problem
            //For example [7 8 4 3], there is no Prev Left Element for element 4, so left[2] = 2+1 =3.
            left[i] = previousLess.isEmpty() ? i + 1 : i - previousLess.peek()[1];
            previousLess.push(new int[]{arr[i], i});
        }
        // left[i] corresponds => for the subarray size left[i] the current element arr[i] is the minimum
        // the arr[3]=>1 is having left[i]=4 means for the subarray length size 4 to the left, 1 is minimum;
        // [5, 3, 4, 1, 2, 7]
        // [1, 2, 1, 4, 1, 1]
        System.out.println(Arrays.toString(left));
        // next less element. for this we do reverse traversal
        for (int i = length - 1; i >= 0; i--) {
            while (!nextLess.isEmpty() && nextLess.peek()[0] > arr[i]) {
                nextLess.pop();
            }
            right[i] = nextLess.isEmpty() ? length - i : nextLess.peek()[1] - i;
            nextLess.push(new int[]{arr[i], i});
        }
        System.out.println(Arrays.toString(right));

        /**
         * Generally there are (N*N+1)/2 sub arrays in a given array,
         * to find out the number of sub arrays the arr[i] to be present in
         * we calculate using arr[i]*leftLength*rightLength
         *
         * example: [5, 3, 4, 1, 2, 7] total subarrays = 6*(6+1)/2 = 21
         * excluding 1 from above we have the left part [5,3,4] => 3*4/2 = 6
         * excluding 1 from above we have the right part [2,7] => 2*3/2 = 3
         * so out of 21, the arr[3]= 1 will  be part of only 21-6+3 sub arrays = 12
         *
         * the reason to multiply arr[i] is to get total sum
         *
         * How much the element 2 contributes to the final answer?
         * Since we want to add the minimum from each subarray.
         * In this case in the 12 subarrays, 1 is the minimum, so it will contribute 1*12 i.e. 12.
         * if the min is 2 then it will be 2*12= 24
         */
        for (int i = 0; i < length; i++) {
            result = (result + (long) arr[i] * left[i] * right[i]) % mod;
        }
        return (int) result;
    }

    public int sumSubarrayMinsTLE(int[] arr) {
        if (arr.length == 1) return arr[0];
        int mod = (int) Math.pow(10, 9) + 7;

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                result += min;
                result %= mod;
            }
        }
        return result;

    }

    public int sumSubarrayMinsMLE(int[] arr) {
        if (arr.length == 1) return arr[0];
        int mod = (int) Math.pow(10, 9) + 7;
        int[][] dp = new int[arr.length][arr.length];
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = arr[i];
            result += dp[i][i];
        }

        for (int l = 1; l < arr.length; l++) {
            for (int i = 0; i < arr.length - l; i++) {
                int j = i + l;
                dp[i][j] = Math.min(dp[i + 1][j] % mod, dp[i][j - 1] % mod);
                result += dp[i][j];
                result %= mod;
            }
        }

        return result;
    }
}
