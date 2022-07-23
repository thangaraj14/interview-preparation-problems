package internals;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMap<K, V> {
   
//    final Segment<K,V>[] segments;
//    // Other varialbes
//
//    static final class HashEntry<K,V> {
//        final int hash;
//        final K key;
//        volatile V value;
//        volatile HashEntry<K,V> next;
//            // Constructors + utility methods
//    }
//
//    static final class Segment<K,V> extends ReentrantLock implements Serializable {
//            // Implementations of methods like replace,clear,put etc.
//            // Each such operation is handled by the particular Segment.
//    }
//
//    public boolean isEmpty() {
//      // checks by iterating through all the segments.
//    }
//
//    public int size() {
//        // Iterates through all the segments to get the count/size.
//    }
//
//    // Performed without acquiring the lock.
//    public V get(Object key) {
//        // Get segment
//        // Get HashEntries for that Segment
//        // itarate over the HashEntries to compare the Key.
//    }
//
//    public boolean containsValue(Object value) {
//        // Iterates over all the Segments.
//        // To check any Segment, locks it and then gets all HashEntries, and compare each of them.
//        // Releases the lock for that segment after it.
//    }
//
//    public V put(K key, V value) {
//        // Gets the Segment, and calls the put method implemented within that segment.
//    }
//
//    public V remove(Object key) {
//        // Gets the Segment, and calls the remove method implemented within that segment.
//    }
//
//    @Override
//    public boolean remove(Object key, Object value) {
//        return false;
//    }
//
//    public boolean replace(K key, V oldValue, V newValue) {
//         // Gets the Segment, and calls the replace method implemented within that segment.
//    }
//
//    public void clear() {
//        // Gets the Segment, and calls the clear method implemented within that segment.
//    }
//    public V get(Object key) {
//        Segment<K,V> s; // manually integrate access methods to reduce overhead
//        HashEntry<K,V>[] tab;
//        int h = hash(key.hashCode());
//        long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
//        if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
//                (tab = s.table) != null) {
//            for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
//                    (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
//                 e != null; e = e.next) {
//                K k;
//                if ((k = e.key) == key || (e.hash == h && key.equals(k)))
//                    return e.value;
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    public Set<Entry<K, V>> entrySet() {
//        return null;
//    }


    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    private static final int INITIAL_CAPACITY = 16;

    private float LOAD_FACTOR = 0.75f;

    private int capacity = INITIAL_CAPACITY;

    private int size;

    private Lock[] locks;

    private MyHashMap myHashMap = new MyHashMap();

    private class MyHashMap {
        private LinkedList[] lists = new LinkedList[INITIAL_CAPACITY];

        private class MapEntry {
            final Object key;
            volatile Object value;

            MapEntry(Object key, Object value) {
                this.key = key;
                this.value = value;
            }
        }

        void put(Object key, Object value) {
            if (key == null)
                throw new IllegalArgumentException("Key Cannot be Null");
            int hash = key.hashCode();
            hash %= lists.length;
            if (size >= LOAD_FACTOR * lists.length) {
                capacity = lists.length * 2;
                LinkedList[] tempLists = new LinkedList[capacity];
                System.arraycopy(lists, 0, tempLists, 0, lists.length);
                lists = tempLists;
                reHash();
            }
            if (lists[hash] == null) {
                lists[hash] = new LinkedList<>();
                size++;
            }
            int i = 0;
            for (; i < lists[hash].size(); i++) {
                MapEntry mapEntry = (MapEntry) (lists[hash].get(i));
                if (mapEntry != null && mapEntry.key.equals(key)) {
                    mapEntry.value = value;
                    break;
                }
            }
            if (i == lists[hash].size()) {
                lists[hash].addLast(new MapEntry(key, value));
            }
        }

        Object get(Object key) {
            int hash = key.hashCode();
            hash %= lists.length;
            Object value = null;
            if (lists[hash] != null) {
                for (int i = 0; i < lists[hash].size(); i++) {
                    MapEntry mapEntry = (MapEntry) (lists[hash].get(i));
                    if (mapEntry != null && mapEntry.key.equals(key)) {
                        value = mapEntry.value;
                        break;
                    }
                }
            }
            return value;
        }

        private void reHash() {
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    int hash = ((MapEntry) lists[i].getFirst()).key.hashCode();
                    hash %= lists.length;
                    if (i != hash) {
                        lists[hash] = lists[i];
                        lists[i] = null;
                    }
                }
            }

        }

        int size() {
            return size;
        }

        int capacity() {
            return capacity;
        }
    }

    public ConcurrentHashMap(int concurrencyLevel) {
        locks = new Lock[concurrencyLevel];
        for (int i = 0; i < concurrencyLevel; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    public ConcurrentHashMap() {
        this(DEFAULT_CONCURRENCY_LEVEL);
    }

    public void put(Object key, Object value) {
        int hash = key.hashCode();
        hash %= myHashMap.capacity();
        locks[hash].lock();
        myHashMap.put(key, value);
        locks[hash].unlock();
    }

    public Object get(Object key) {
        return myHashMap.get(key);
    }

    public static void main(String[] args) {
        ConcurrentHashMap myCCHashMap = new ConcurrentHashMap();
        myCCHashMap.put(1, "Thomas");
        myCCHashMap.put(9, "Mathew");
        myCCHashMap.put(17, "Tissa");
        myCCHashMap.put(9, "Mathew Thomas");
        System.out.println(myCCHashMap.get(1));
        System.out.println(myCCHashMap.get(9));
        System.out.println(myCCHashMap.get(17));
    }
}

// final class Segment<K,V> extends ReentrantLock implements Serializable {
//
//    transient volatile HashEntry<K,V>[] table;
//    transient int threshold;
//    final float loadFactor;
//
//     Segment(float loadFactor) {
//         this.loadFactor = loadFactor;
//     }
//
//     final V put(K key, int hash, V value, boolean onlyIfAbsent) {
//        // Obtain the lock on the current Segment
//        HashEntry<K,V> node = tryLock() ? null : scanAndLockForPut(key, hash, value);
//        try {
//            // Do normal Put operation like in hashmap
//        } finally {
//            // Release the lock after put is finished.
//            unlock();
//        }
//    }
//
//    /**
//     * Remove; match on key only if value null, else match both.
//     */
//    final V remove(Object key, int hash, Object value) {
//        // Obtain the lock on the current Segment
//        if (!tryLock())
//            scanAndLock(key, hash);
//
//        try {
//            // Normal Remove operation as in HashMap.
//        } finally {
//            // Release the lock once the remove is done.
//            unlock();
//        }
//
//    }
//
//    final boolean replace(K key, int hash, V oldValue, V newValue) {
//        // Obtain the lock on the current Segment
//        if (!tryLock())
//            scanAndLock(key, hash);
//        try {
//            // Normal Replace operation as in HashMap.
//        } finally {
//            // Release the lock once the remove is done.
//            unlock();
//        }
//        return replaced;
//    }
//
//    final void clear() {
//        // Obtain the lock on the current Segment
//        lock();
//        try {
//            // Normal clear operation as in HashMap.
//        } finally {
//            // Release the lock once the clear is done.
//            unlock();
//        }
//    }
//}