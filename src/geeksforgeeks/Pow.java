package geeksforgeeks;

// https://leetcode.com/problems/powx-n/
public class Pow {

    public double myPow(double x, int n) {

        if (n < 0) {
            n *= -1;
            x = 1 / x;
        }
        double result = 1;

        result = powerCalcUtil(x, n);
        return result;
    }


    public double powerCalcUtil(double x, int n) {
        if (n == 0)
            return 1;

        double half = powerCalcUtil(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double pow1(double x, int n) {
        double result = 1.0;
        for(int i = n; i != 0; i /= 2, x *= x) {
            if( i % 2 != 0 ) {
                result *= x;
            }
        }
        return n < 0 ? 1.0 / result : result;
       }
}