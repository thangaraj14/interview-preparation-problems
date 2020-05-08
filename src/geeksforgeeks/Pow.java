package geeksforgeeks;

<<<<<<< HEAD
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
=======
/**
 * https://leetcode.com/problems/powx-n/
 */
public class Pow {

    public static void main(String[] args) {
        Pow pow = new Pow();
        System.out.println(pow.pow(2.00000, 10));
    }

    public double pow(double x, int m) {
        double temp = x;
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

    public double powIterative(double x, int n) {
        double result = 1.0;
        for (int i = n; i != 0; i /= 2, x *= x) {
            if (i % 2 != 0) {
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
                result *= x;
            }
        }
        return n < 0 ? 1.0 / result : result;
<<<<<<< HEAD
       }
=======
    }

>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
}