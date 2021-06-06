package dsa;

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
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }
}