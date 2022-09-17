package binarysearch;

/**
 * https://leetcode.com/problems/sqrtx
 */
public class Sqrt {

    /**
     * square root of any number can not be greater than half of the number
     * since 3 is a number but sqrt 3 is greater than its half.
     */
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        int left = 1, right = x, res = 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid > x / mid) {
                right = mid;
            } else {
                res = mid;
                left = mid + 1;
            }
        }
        return res;
    }
}
