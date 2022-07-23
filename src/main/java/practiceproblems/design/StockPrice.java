package practiceproblems.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/stock-price-fluctuation/
 */
public class StockPrice {
    static class Node {
        public int timestamp;
        public int price;

        public Node(int timestamp, int price) {
            this.timestamp = timestamp;
            this.price = price;
        }
    }

    Map<Integer, Node> timeToPrice = new HashMap<>();

    PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));

    PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.price));

    Node latest = null;

    public StockPrice() {

    }

    /**
     * Updates the price of the stock at the given timestamp.
     */
    public void update(int timestamp, int price) {
        Node newNode = new Node(timestamp, price);
        timeToPrice.put(timestamp, newNode);
        maxHeap.add(newNode);
        minHeap.add(newNode);
        if (latest == null || timestamp >= latest.timestamp) {
            latest = newNode;
        }
    }

    /**
     * Returns the latest price of the stock.
     */
    public int current() {
        return latest.price;
    }

    /**
     * Returns the maximum price of the stock.
     */
    public int maximum() {
        if (maxHeap.isEmpty()) return -1;

        while (maxHeap.peek().price != timeToPrice.get(maxHeap.peek().timestamp).price) {
            maxHeap.remove();
        }
        return maxHeap.isEmpty() ? -1 : maxHeap.peek().price;
    }

    /**
     * Returns the minimum price of the stock.
     */
    public int minimum() {
        if (minHeap.isEmpty()) return -1;

        while (minHeap.peek().price != timeToPrice.get(minHeap.peek().timestamp).price) {
            minHeap.remove();
        }
        return minHeap.isEmpty() ? -1 : minHeap.peek().price;
    }
}
