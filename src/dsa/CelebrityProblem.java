package dsa;

/**
 * https://www.lintcode.com/problem/find-the-celebrity/description
 */
public class CelebrityProblem {

    public int findCelebrity(int n) {
        if (n == 1) {
            return 0;
        }
        int result = 0;
        for (int i = 1; i < n; i++) {
            if (knows(result, i)) {
                result = i;
            }
        }

        for (int i = 0; i < n; i++) {
            // just to make sure everybody knows celebrity
            if (i != result && knows(result, i)) {
                return -1;
            }
            //celebrity doesnt know anyone.
            if (result != i && !knows(i, result)) {
                return -1;
            }
        }

        return result;
    }

    public boolean knows(int candidate, int i) {
        return false;
    }
}