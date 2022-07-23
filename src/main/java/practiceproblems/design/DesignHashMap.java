package practiceproblems.design;

public class DesignHashMap {
    ListNode[] nodes;

    /**
     * Initialize your data structure here.
     */
    public DesignHashMap() {
        nodes = new ListNode[10000];
    }

    private int idx(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    private ListNode find(ListNode node, int val) {
        ListNode temp = node, prev = null;
        while (temp != null && temp.key != val) {
            prev = temp;
            temp = temp.next;
        }
        return prev;
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int i = idx(key);
        if (nodes[i] == null) {
            nodes[i] = new ListNode(-1, -1);
        }
        ListNode prev = find(nodes[i], key);
        if (prev.next == null) {
            prev.next = new ListNode(key, value);
        } else {
            prev.next.val = value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int i = idx(key);
        if (nodes[i] == null) {
            return -1;
        }
        ListNode prev = find(nodes[i], key);
        if (prev.next == null) {
            return -1;
        }
        return prev.next.val;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int i = idx(key);
        if (nodes[i] == null) {
            return;
        }
        ListNode prev = find(nodes[i], key);
        if (prev.next == null) {
            return;
        }
        prev.next = prev.next.next;
    }

    class ListNode {
        int key;
        int val;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}



