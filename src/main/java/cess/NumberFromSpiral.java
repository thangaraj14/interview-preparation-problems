package cess;

/**
 * https://cses.fi/problemset/task/1071
 * https://www.youtube.com/watch?v=pNN35ZdX77Y
 * <p>
 * tricky complex
 */
public class NumberFromSpiral {

    public int getValueAtIndex(int i, int j) {

        int max = Math.max(i, j);

        if ((max & 1) == 0) {
            if (j == 1) {
                return max * max;
            }

            if (i < max) {
                return getValueAtIndex(max, max) - (max - i);
            } else if (i == max) {
                return max * max - (j - 1);
            }
        } else {
            if (i == 1) {
                return max * max;
            }

            if (j < max) {
                return getValueAtIndex(max, max) - (max - j);
            } else {
                return max * max - (i - 1);
            }
        }
        return -1;
    }

}
