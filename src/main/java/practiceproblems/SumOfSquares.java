package practiceproblems;

/**
 * https://leetcode.com/problems/sum-of-square-numbers/
 *
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 */
public class SumOfSquares {
    public boolean judgeSquareSum(int c) {
        long l = 0, r = (long) Math.sqrt(c);
        while (l <= r) {   // this <= other than <
            long sum = l * l + r * r;
            if (sum == c) return true;
            if (sum < c) l++;
            else r--;
        }
        return false;
    }
}
