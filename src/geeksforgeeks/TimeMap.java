package geeksforgeeks;

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
    HashMap<String,Node>map;
    
    public TimeMap() {
        map=new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(map.containsKey(key)){
            Node node=new Node(value,timestamp);
            node.next=map.get(key);
            map.put(key,node);
        }else{
            Node temp=new Node(value,timestamp);
            map.put(key,temp);
        }
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