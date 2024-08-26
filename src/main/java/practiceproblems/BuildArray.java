package practiceproblems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/build-array-from-permutation/
 *
 * tricky
 */
public class BuildArray {

    public static int[] buildArray(int[] nums) {
        int n = nums.length;
        int i = 0;
        /**
         a = r +bq Euclid's Division algorithm
         let r = nums[i]
         replace nums[i] with a number that:

         when you mod by q you get nums[i]
         when you divide by q you get nums[nums[i]]
         From a = r + bq

         we can observe that

         a % q = r
         a / q = b
         a % q = r

         Since a = r + bq, we can write:
         (r + bq) % q = r % q + (bq % q) = r % q + 0 = r
         This is because any multiple of q will give a remainder of 0 when divided by q.

         a / q = b
         Again, using a = r + bq:
         (r + bq) / q = r/q + b
         Since r < q (as r is a remainder), r/q will be 0 in integer division.
         So, (r + bq) / q = 0 + b = b

         nums[i] is stored as r (the remainder)
         nums[nums[i]] is stored as b (the quotient)
         n is stored as q
         */

        while (i < nums.length) {
            int temp = nums[i] + n * (nums[nums[i]] % n);
            nums[i] = temp;
            i++;
        }

        System.out.println(Arrays.toString(nums));

        i = 0;
        while (i < nums.length) {
            nums[i] /= n;
            i++;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(buildArray(new int[]{5, 0, 1, 2, 3, 4})));
    }
}
