package binarysearch;

/**
 * https://leetcode.com/problems/sqrtx
 */
public class Sqrt {

    public int mySqrt(int x) {
        if(x==0 || x==1) return x;

        long left= 0L;
        long right= x;

        while(left<right){
            long mid= left + (right-left)/2;
            if(mid*mid>x) right=mid;
            else left=mid+1;
        }
        left--;
        return (int)left;
    }
}
