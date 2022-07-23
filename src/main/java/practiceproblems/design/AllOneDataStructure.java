package practiceproblems.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 */
public class AllOneDataStructure {

    class ValueNode {
        ValueNode prev, next;
        int val;
        Set<String> strs;

        ValueNode(int v) {
            val = v;
            strs = new LinkedHashSet<>();
        }

        void insertAt(ValueNode node) {
            next = node;
            prev = node.prev;
            next.prev = this;
            prev.next = this;
        }

        void remove(String str) {
            strs.remove(str);
            if (strs.isEmpty()) {
                prev.next = next;
                next.prev = prev;
            }
        }
    }

    ValueNode valueHead, valueTail; // dummy
    Map<String, ValueNode> keys;

    /**
     * Initialize your data structure here.
     */
    public AllOneDataStructure() {
        valueHead = new ValueNode(0);
        valueTail = new ValueNode(0);
        valueHead.next = valueTail;
        valueTail.prev = valueHead;
        keys = new HashMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        ValueNode node = keys.getOrDefault(key, valueHead);
        ValueNode vn = node.next;
        if (vn.val != node.val + 1) {
            vn = new ValueNode(node.val + 1);
            vn.insertAt(node.next);
        }
        vn.strs.add(key);
        keys.put(key, vn);
        if (node != valueHead) node.remove(key);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        ValueNode node = keys.get(key);
        if (node == null) return;
        if (node.val == 1) {
            keys.remove(key);
            node.remove(key);
            return;
        }
        ValueNode vn = node.prev;
        if (vn.val != node.val - 1) {
            vn = new ValueNode(node.val - 1);
            vn.insertAt(node);
        }
        vn.strs.add(key);
        keys.put(key, vn);
        node.remove(key);
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (valueTail.prev == valueHead) return "";
        return valueTail.prev.strs.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (valueHead.next == valueTail) return "";
        return valueHead.next.strs.iterator().next();
    }

    public static void main(String[] args) {
        AllOneDataStructure o = new AllOneDataStructure();
        o.inc("Hello");
        o.inc("Hello");
        o.inc("Leet");
        o.dec("Hello");
    }
}
