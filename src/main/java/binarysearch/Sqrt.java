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
        if(x <= 1)return x;
        long min = 1, max = x / 2, ans = -1, mid;
        while(min <= max){
            mid =  (max + min) / 2;
            if (mid*mid == x)return (int)mid;
            if (mid*mid <= x){
                ans = mid;
                min = mid + 1;
            }
            else max = mid - 1;
        }
        return (int)ans;
    }
}
