package dsa;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/time-based-key-value-store/
 */
class TimeMap {

    class Node {
        String val;
        int time;
        Node next;

        public Node(String value, int timestamp) {
            val = value;
            time = timestamp;
        }
    }

    /**
     * Initialize your data structure here.
     */
    HashMap<String, Node> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        Node node = new Node(value, timestamp);
        if (map.containsKey(key)) {
            node.next = map.get(key);
        }
        map.put(key, node);
    }

    public String get(String key, int timestamp) {
        String vl = "";
        if (map.containsKey(key)) {
            Node y = map.get(key);
            while (y.time > timestamp && y.next != null)
                y = y.next;
            if (y.time <= timestamp) {
                vl = y.val;
            }
        }
        return vl;
    }
}