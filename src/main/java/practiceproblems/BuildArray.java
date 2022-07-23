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
         * To achieve O(1) space complexicity ,we need to store two values  i.e. nums[i]  and nums[nums[i]]  in the same spot .
         * 	we can do this by
         * 	nums[i] = nums[i] + n *(nums[nums[i]] % n);
         *
         * 	In this both values i.e. nums[i]  and nums[nums[i]]   are being stored
         * 	For eg.
         *
         * 	input = [0,2,1,5,3,4]
         * 	Output: [0,1,2,4,5,3]
         *
         * 	here, let i=3,
         * 	then nums[3] = 5;
         * 	and   nums[nums[3]] = 4;
         *
         * 	Now we store both of them in nums[i] itself by
         * 	nums[i] = nums[i] + n *(nums[nums[i]] % n); //Equation  1
         *
         * 	nums[3] = 5 + 6 *(4 %6);
         * 	nums[3] = 29
         * 	Now in Second loop we Can retrieve nums[nums[i]] by dividing by n,
         * 	nums[i] = nums[i] + n *(nums[nums[i]] % n)  /  n;
         * 	nums[i] = 0 + nums[nums[i]];
         * 	nums[i]= nums[nums[i];
         *
         * 	nums[3]/n = 29/6 =4;
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
