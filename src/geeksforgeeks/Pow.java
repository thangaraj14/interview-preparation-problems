package geeksforgeeks;

/**
 * https://leetcode.com/problems/powx-n/
 */
public class Pow {

    public static void main(String[] args) {
        Pow pow = new Pow();
        System.out.println(pow.powIterative(2.00000, 10));
    }

    public double pow(double x, int m) {
        double temp;
        if (m == 0) {
            return 1;
        }
        temp = pow(x, m / 2);
        if (m % 2 == 0) {
            return temp * temp;
        } else {
            if (m > 0) {
                return x * temp * temp;
            } else {
                return (temp * temp) / x;
            }
        }
    }

    /**
     * 2 pow 10 ,
     */
    public double powIterative(double x, int n) {
        double result = 1.0;
        for (int i = n; i != 0; i /= 2, x *= x) {
            if (i % 2 != 0) {
                result *= x;
            }
        }
        return n < 0 ? 1.0 / result : result;
    }

}