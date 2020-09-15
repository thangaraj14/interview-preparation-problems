package internals;

import java.util.Objects;

class HashMap<K, V> {
        private int size = 0;
        private int capacity = 16;
        private Entry<K, V>[] entries = new Entry[capacity];
        private double loadFactor = 0.75;
    
        private static class Entry<K, V> {
            private final K key;
            private V value;
            // Java initializes reference fields to null by default.
            //private Entry<K, V> next = null;
            private Entry<K, V> next;
        
            // No point in 'public' here:
            /*public*/ Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    
        public HashMap() {
        }
    
        public boolean isEmpty() {
            return this.size == 0;
        }
    
        public int getSize() {
            return this.size;
        }
    
        public boolean containsKey(K key) {
            Entry<K, V> matchingEntry = getMatchingEntry(key);
    
            return matchingEntry != null && matchingEntry.key == key;
        }
    
        public boolean containsValue(V value) {
            for (Entry<K, V> entry : this.entries) {
                while (entry != null && !matches(value, entry.value)) {
                    entry = entry.next;
                }
    
                if (entry != null) {
                    return true;
                }
            }
    
            return false;
        }
    
        public V get(K key) {
            Entry<K, V> matchingEntry = getMatchingEntry(key);
    
            return matchingEntry == null ? null : matchingEntry.value;
        }
    
        public void put(K key, V value) {
            if (this.shouldResize()) {
                this.resize();
            }
    
            if (addEntry(new Entry<>(key, value), this.entries)) {
                this.size++;
            }
    
        }
    
        public void remove(K key) {
            int index = indexOf(key);
            Entry<K, V> currentEntry = this.entries[index];
    
            while (currentEntry != null && currentEntry.next != null && !matches(key, currentEntry.next.key)) {
                currentEntry = currentEntry.next;
            }
    
            if (currentEntry != null) {
                // this case can only occur if there is only one non-null entry at the index
                if (matches(key, currentEntry.key)) {
                    this.entries[index] = null;
                // this case can only occur because the next entry's key matched
                } else if (currentEntry.next != null) {
                    currentEntry.next = currentEntry.next.next;
                }
    
                this.size--;
            }
        }
    
        private boolean shouldResize() {
            return this.size > Math.ceil((double) this.capacity * this.loadFactor);
        }
    
        private void resize() {
            this.capacity = this.size * 2;
    
            Entry<K, V>[] newEntries = new Entry[this.capacity];
            for (Entry<K, V> entry : this.entries) {
                if (entry != null) {
                    this.setEntry(entry, newEntries);
                }
            }
    
            this.entries = newEntries;
        }
    
        private void setEntry(Entry<K, V> entry, Entry<K, V>[] entries){
            Entry<K, V> nextEntry = entry.next;
            entry.next = null;
    
            this.addEntry(entry, entries);
    
            if (nextEntry != null) {
                this.setEntry(nextEntry, entries);
            }
        }
    
        private boolean addEntry(Entry<K, V> entry, Entry<K, V>[] entries) {
            int index = indexOf(entry.key);
            Entry<K, V> existingEntry = entries[index];
    
            if (existingEntry == null) {
                entries[index] = entry;
                return true;
            } else {
                while (!this.matches(entry.key, existingEntry.key) && existingEntry.next != null) {
                    existingEntry = existingEntry.next;
                }
    
                if (this.matches(entry.key, existingEntry.key)) {
                    existingEntry.value = entry.value;
                    return false;
                }
    
                existingEntry.next = entry;
                return true;
    
            }
        }
    
        private Entry<K, V> getMatchingEntry(K key) {
            Entry<K, V> existingEntry = this.entries[indexOf(key)];
    
            while (existingEntry != null && !matches(key, existingEntry.key)) {
                existingEntry = existingEntry.next;
            }
    
            return existingEntry;
        }
    
        private int indexOf(K object) {
            return object == null ? 0 : hash(object) & (this.capacity - 1);
        }
    
        private boolean matches(Object o1, Object o2) {
            return Objects.equals(o1, o2);
        }
    
        /**
         * Applies a supplemental hash function to a given hashCode, which
         * defends against poor quality hash functions.  This is critical
         * because HashMap uses power-of-two length hash tables, that
         * otherwise encounter collisions for hashCodes that do not differ
         * in lower bits. Note: Null keys always map to hash 0, thus index 0.
         */
        private static int hash(Object key) {
            // This function ensures that hashCodes that differ only by
            // constant multiples at each bit position have a bounded
            // number of collisions (approximately 8 at default load factor).
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }
    }
    
