package practiceproblems;

import java.util.*;

class MyStack {

    Stack<Integer> stack;
    Integer minEle;

    MyStack() {
        stack = new Stack<>();
    }

    void getMin() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Minimum Element in the " + " stack is: " + minEle);
        }
    }

    void peek() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty ");
            return;
        }

        Integer t = stack.peek();
        System.out.print("Top Most Element is: ");

        if (t < minEle) {
            System.out.println(minEle);
        } else {
            System.out.println(t);
        }
    }

    void pop() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        System.out.print("Top Most Element Removed: ");
        Integer t = stack.pop();

        if (t < minEle) {
            System.out.println(minEle);
            minEle = 2 * minEle - t;
        } else {
            System.out.println(t);
        }
    }

    void push(Integer x) {
        if (stack.isEmpty()) {
            minEle = x;
            stack.push(x);
            System.out.println("Number Inserted: " + x);
            return;
        }

        if (x < minEle) {
            // x-minEle<0
            // x-minEle+x<0+x
            // 2x-minEle<x
            stack.push(2 * x - minEle);
            minEle = x;
        } else {
            stack.push(x);
        }

        System.out.println("Number Inserted: " + x);
    }
};

public class MinimumStack {
    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(3);
        s.push(5);
        s.getMin();
        s.push(2);
        s.push(1);
        s.getMin();
        s.pop();
        s.getMin();
        s.pop();
        s.peek();
    }
}

class MinStack {
    private Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min) {
            this(val, min, null);
        }

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}