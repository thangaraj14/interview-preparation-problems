package dsa;
/*
  https://leetcode.com/problems/lru-cache/
  An adaption of the answer from user "liaison" on Leetcode.
  Link: https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-%2B-Double-linked-list-(with-a-touch-of-pseudo-nodes)
  Revision by Benyam Ephrem (Dec. 31th 2018)
    > Making variable names more conventional
    > Adding more clarifying comments
    > Moving code around to be more conventional
  This code passes all Leetcode test cases as of Dec. 31st 2018
  Runtime: 77 ms, faster than 95.85% of Java online submissions for LRU Cache.
  The video to explain this code is here: https://www.youtube.com/watch?v=S6IfqDXWa10
*/

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;
    }

    private Map<Integer, DNode> hashtable = new HashMap<>();
    private DNode head, tail;
    private int maxCapacity;

    public LRUCache(int maxCapacity) {

        this.maxCapacity = maxCapacity;

        head = new DNode();
        tail = new DNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        DNode node = hashtable.get(key);

        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {

        DNode node = hashtable.get(key);

        if (node != null) {
            DNode newNode = new DNode();
            newNode.key = key;
            newNode.value = value;

            hashtable.put(key, newNode);
            addNode(newNode);

            if (hashtable.size() > maxCapacity) {
                removeLRUEntryFromStructure();
            }

        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void removeLRUEntryFromStructure() {
        DNode tail = popTail();
        hashtable.remove(tail.key);
    }

    private void addNode(DNode node) {

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DNode node) {

        DNode savedPrev = node.prev;
        DNode savedNext = node.next;

        savedPrev.next = savedNext;
        savedNext.prev = savedPrev;
    }

    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }

    private DNode popTail() {
        DNode itemBeingRemoved = tail.prev;
        removeNode(itemBeingRemoved);
        return itemBeingRemoved;
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.get(2);
        lru.put(4, 4);
        lru.put(2, 2);
        lru.put(3, 3);
    }
}
