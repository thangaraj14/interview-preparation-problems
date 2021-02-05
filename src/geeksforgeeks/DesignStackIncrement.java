package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/design-a-stack-with-increment-operation/discuss/539716/JavaC%2B%2BPython-Lazy-increment-O(1)
 * we wont increment all the values, we just increment the kth index
 */
public class DesignStackIncrement {

    int n;
    int[] inc;
    Stack<Integer> stack;

    public DesignStackIncrement(int maxSize) {
        n = maxSize;
        inc = new int[n];
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.size() < n) {
            stack.push(x);
        }
    }

    public int pop() {
        int i = stack.size() - 1;
        if (i < 0) {
            return -1;
        }
        if (i > 0) {
            inc[i - 1] += inc[i];
        }
        int res = stack.pop() + inc[i];
        inc[i] = 0;
        return res;
    }

    public void increment(int k, int val) {
        int i = Math.min(k, stack.size()) - 1;
        if (i >= 0) {
            inc[i] += val;
        }
    }

    public static void main(String[] args) {
        DesignStackIncrement customStack = new DesignStackIncrement(4);
        customStack.push(1);
        customStack.push(2);
        customStack.push(3);
        customStack.push(4);
        customStack.increment(5, 100);
        customStack.increment(2, 100);
        System.out.println(customStack.pop());
        System.out.println(customStack.pop());
        System.out.println(customStack.pop());
        System.out.println(customStack.pop());

        List<String> list = new ArrayList<>(Arrays.asList("hello".split("")));
        list.size();
    }
}
