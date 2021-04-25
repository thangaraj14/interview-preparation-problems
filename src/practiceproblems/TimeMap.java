package practiceproblems;

import java.util.HashMap;

/**
 * Create a time based key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously,
 * with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 */

class TimeMap {

    class Node{
        String val;
        int time;
        Node next;
        public Node(String value, int timestamp){
            val=value;
            time=timestamp;
        }
    }
    /** Initialize your data structure here. */
    HashMap<String,Node> map;
    
    public TimeMap() {
        map=new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        Node node =new Node(value,timestamp);
        if(map.containsKey(key)){
            node.next=map.get(key);
        }
        map.put(key,node);
    }
    
    public String get(String key, int timestamp) {
        String vl="";
        if(map.containsKey(key)){ 
            Node y=map.get(key);
            while(y.time>timestamp&&y.next!=null)
                y=y.next;
            if(y.time<=timestamp)
                vl=y.val;
        }
        return vl;
    }
}