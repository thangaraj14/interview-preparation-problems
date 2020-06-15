package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/discuss/94547/Java-O(1)-Solution-Using-Two-HashMap-and-One-DoubleLinkedList
 */
public class LFUCache {
    class Node {
        int key;
        int val;
        int count;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            count = 1;
        }
    }

    class DLList {
        Node head, tail;
        int size;

        DLList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            } else {
                return null;
            }
        }
    }

    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, DLList> countMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }
        update(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.val = value;
            update(node);
        } else {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DLList lastList = countMap.get(min);
                nodeMap.remove(lastList.removeLast().key);
                size--;
            }
            size++;
            min = 1;
            DLList newList = countMap.getOrDefault(node.count, new DLList());
            newList.add(node);
            countMap.put(node.count, newList);
        }
    }

    private void update(Node node) {
        DLList oldList = countMap.get(node.count);
        oldList.remove(node);
        if (node.count == min && oldList.size == 0) {
            min++;
        }
        node.count++;
        DLList newList = countMap.getOrDefault(node.count, new DLList());
        newList.add(node);
        countMap.put(node.count, newList);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        lfuCache.get(2);
        lfuCache.put(4, 4);
    }
}