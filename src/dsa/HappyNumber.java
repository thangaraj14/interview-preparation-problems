package dsa;

import java.util.HashSet;
import java.util.Set;

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

    public boolean isHappyLogic(int n) {

        Set<Integer> set = new HashSet<>();
        int temp = n;
        while (true) {
            int sum = 0;
            while (temp > 0) {
                int rem = temp % 10;
                temp = temp / 10;
                sum += rem * rem;
            }
            temp = sum;
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            if (sum == 1) {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(19));
    }
}