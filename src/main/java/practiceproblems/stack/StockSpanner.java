package practiceproblems.stack;


import graph.leetcode.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {

    Deque<Pair<Integer, Integer>> stack;

    public StockSpanner() {
        this.stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int value = 1;
        while (!stack.isEmpty() && stack.peek().getKey() <= price) {
            value += stack.pop().getValue();
        }

        stack.push(new Pair(price, value));

        return stack.peek().getValue();
    }
}