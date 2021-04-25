package dsa;

/**
 * https://leetcode.com/problems/happy-number/
 */
class HappyNumber {

    int next(int n) {
        int res = 0;
        while (n > 0) {
            int t = n % 10;
            res += t * t;
            n = n / 10;
        }
        return res;
    }

    boolean isHappy(int n) {
        int i1 = n;
        int i2 = next(n);

        while (i1 != i2) {
            System.out.println(i1 + "--" + i2);
            i1 = next(i1);
            i2 = next(next(i2));
        }
        return i1 == 1;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(19));
    }
}