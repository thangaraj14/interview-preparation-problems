package internals;

public class HashMapJava8<K,V> {
    private final Entry[] entries;
    private Integer size;

    public HashMapJava8(Entry[] entries, Integer size) {
        this.entries = entries;
        this.size = size;
    }

    public HashMapJava8(int size){
        this.size=size;
        this.entries= new Entry[size];
        for(int i=0;i<size;i++){
            entries[i]= new Entry();
        }
    }

    public Integer getHash(K key){
        return key.hashCode()%this.size;
    }

    public HashMapJava8<K,V> put(K key, V value){
        int hash= getHash(key);
        Entry oldEntry= entries[hash];
        Entry newEntry= new Entry(key,value);
        newEntry.next=oldEntry;
        entries[hash]= newEntry;
        size++;
        return new HashMapJava8<>(entries,size);
    }

}

class Entry{
    Object key;
    Object value;
    Entry next;
    public Entry(){

    }

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.next=null;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Entry getNext() {
        return next;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}
