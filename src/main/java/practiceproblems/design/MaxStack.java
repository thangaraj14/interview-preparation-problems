package practiceproblems.design;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxStack {
    Deque<Integer> stack;
    Deque<Integer> maxStack;

    public MaxStack() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }

    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(Math.max(max, x));
        stack.push(x);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Deque<Integer> buffer = new ArrayDeque<>();
        while (top() != max) buffer.push(pop());
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());
        return max;
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(3);
        stack.push(7);
        System.out.println(stack.peekMax());
        stack.push(2);
        System.out.println(stack.popMax());
        stack.push(4);
        System.out.println(stack.top());
        stack.push(6);
        System.out.println(stack.popMax());
        stack.push(9);
        System.out.println(stack.peekMax());

    }
}
