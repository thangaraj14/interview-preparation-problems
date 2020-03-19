package geeksforgeeks;

public class Pow {

    public double myPow(double x, int n) {
        if (n < 0) {
            isNegative = true;
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

}