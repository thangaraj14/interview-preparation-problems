package practiceproblems;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 */
class HappyNumber {
    public boolean isHappy(int n) {
        if (n == 1) return true;

        Set<Integer> seen = new HashSet<>();

        while (true) {
            int result = 0;
            while (n > 0) {
                int temp = n % 10;
                result += temp * temp;
                n /= 10;
            }
            if (!seen.add(result)) return false;
            if (result == 1) break;
            seen.add(result);
            n = result;
        }

        return true;
    }

    public int getNext(int n) {
        int res = 0;
        while (n > 0) {
            int t = n % 10;
            res += t * t;
            n /= 10;
        }
        return res;
    }

    public boolean isHappyOpt(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);

        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappyOpt(19));
    }
}