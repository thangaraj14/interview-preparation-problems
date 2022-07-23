package practiceproblems;

import java.util.Arrays;

/**
 *
 * tricky array manipulation
 *
 * https://leetcode.com/problems/product-of-array-except-self/
 * <p>
 * Given numbers [2, 3, 4, 5], regarding the third number 4,
 * the product of array except 4 is 2*3*5 which consists of two parts: left 2*3 and right 5.
 * The product is left*right. We can get lefts and rights
 * <p>
 * Numbers:     2    3    4     5
 * Lefts:            2  2*3 2*3*4
 * Rights:  3*4*5  4*5    5
 * <p>
 * Let’s fill the empty with 1:
 * <p>
 * Numbers:     2    3    4     5
 * Lefts:       1    2  2*3 2*3*4
 * Rights:  3*4*5  4*5    5     1
 */
public class ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // once building the left product instead of having a new array to calculate rightsum
        // we use a variable to store last seen value 
        // for number                 2    3    4     5
        // the left product array is  1    2  2*3 2*3*4

        // for rightProd during step1 2    3    4     5  right is 1 so last product value retained
        //                                         2*3*4   
        // during step 2 the right becomes 5(1*nums[i]=>5)
        // so for value 4(length-2) the left product is 2*3 multiplying with 5 becomes 2*3*5
        // during step 3 the right is updated to (5*nums[i]=>4) 20
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[] { 1, 2, 3, 4, 5 })));
    }
}