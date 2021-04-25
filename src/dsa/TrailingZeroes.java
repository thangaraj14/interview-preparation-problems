package dsa;

/**
 * https://www.geeksforgeeks.org/count-trailing-zeroes-factorial-number/
 */
class TrailingZeroes {
    public int trailingZeroes(int n) {

        int count = 0;
        while (n != 0) {
            int tmp = n / 5;
            count += tmp;
            n = tmp;
        }
        return count;
    }

    public static void main(String[] args) {
        TrailingZeroes solution = new TrailingZeroes();
        System.out.println(solution.trailingZeroes(30));
    }
}