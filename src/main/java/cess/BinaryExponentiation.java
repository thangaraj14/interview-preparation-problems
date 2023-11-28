package cess;

public class BinaryExponentiation {

    /**
     * Fast method to compute exponent values a^b
     * https://www.youtube.com/watch?v=L-Wzglnm4dM
     */
    public int power(int a, int b) {

        int result = 1;

        while (b > 0) {
            if (b == 1) result *= a;
            a *= a;
            b /= 2;
        }
        return result;
    }
}
