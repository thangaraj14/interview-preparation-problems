package geeksforgeeks;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.util.Pair;

/**
 * https://leetcode.com/problems/online-stock-span/description/
 */
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

    public static void main(String[] args) {

    }
}