package practiceproblems.design;

/**
 * tricky sweep line
 * https://leetcode.com/problems/design-a-stack-with-increment-operation
 */
public class DesignStackIncrement {
    int[] st;
    int top;
    int maxSize;

    public DesignStackIncrement(int maxSize) {
        this.maxSize = maxSize;
        st = new int[maxSize];
        top = 0;
    }

    public void pushAlter(int x) {
        if (top < maxSize) {
            st[top++] = x;
        }
    }

    public int popAlter() {
        if (top <= 0)
            return -1;
        return st[--top];
    }

    public void incrementAlter(int k, int val) {
        int l = Math.min(k, top);
        for (int i = 0; i < l; i++)
            st[i] += val;
    }
}