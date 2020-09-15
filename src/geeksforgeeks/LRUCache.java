package geeksforgeeks;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    
    class DLLNode{
        DLLNode prev;
        DLLNode next;
        int val;
        int key;
        public DLLNode(int key, int val){
            this.val=val;
            this.key=key;
        }
    }
        Map<Integer, DLLNode> map;
        DLLNode head;
        DLLNode tail;
        int capacity=0;
        public LRUCache(int capacity) {
            map= new HashMap<>();
            head= new DLLNode(-1,-1);
            tail= new DLLNode(-1,-1);
            head.next=tail;
            tail.prev=head;
            this.capacity=capacity;
        }
        
        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            DLLNode node= map.get(key);
            update(node);
            return node.val;
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)){
                DLLNode node= map.get(key);
                node.val=value;
                update(node);
            }else{
                 if(map.size()>=capacity){
                    removeTail();
                }
                DLLNode newNode= new DLLNode(key,value);
                map.put(key, newNode);
               
               updateHead(newNode);
            }
        }
        
        public void removeTail(){
            DLLNode tailNode=tail.prev;
            remove(tailNode);
            map.remove(tailNode.key);
        }
        
        public void updateHead(DLLNode node){
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
        
        public void remove(DLLNode node){
             DLLNode next= node.next;
             DLLNode prev= node.prev;
             prev.next=next;
             next.prev=prev;
        }
        public void update(DLLNode node){
            remove(node);
            updateHead(node);
        }
    }
    

    class LRUCache1 {
        LinkedHashMap<Integer, Integer> isbnToPrice;
    
       public LRUCache1(final int capacity) {
            this.isbnToPrice
                    = new LinkedHashMap<Integer, Integer>(capacity, 1f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
                    return this.size() > capacity;
                }
            };
        }
    
        public Integer lookup(Integer key) {
            if (!isbnToPrice.containsKey(key)) {
                return null;
            }
            return isbnToPrice.get(key);
        }
    
        public Integer insert(Integer key, Integer value) {
    // We add the value for key only if key is not present - we don’t update
    // existing values.
            Integer currentValue = isbnToPrice.get(key);
            if (!isbnToPrice.containsKey(key)) {
                isbnToPrice.put(key, value);
                return currentValue;
            } else {
                return null;
            }
        }
    
        public Integer erase(Object key) {
            return isbnToPrice.remove(key);
        }
    
    }
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */