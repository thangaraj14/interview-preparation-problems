package internals;

public class LinkedHashMap<K,V> {
        private Entry<K,V>[] buckets;
        private int capacity=4;
        private Entry<K,V> head;
        private Entry<K,V>tail;
        static class Entry<K,V>
        {
            K key;
            V value;
            Entry<K,V>next;
            Entry<K,V>before;
            Entry<K,V>after;
            public Entry(K key ,V value,Entry<K,V> next)
            {
                this.key=key;
                this.value=value;
                this.next=next;
            }   
        }
        public LinkedHashMap()
        {
            buckets=new Entry[capacity];    
        }
        public void put(K key,V value)
        {
            if(key==null)//not allow null key
            {
                return;
            }
            boolean replace=false;
            int hash=hash(key);
            Entry<K,V> newEntry = new Entry<K,V>(key, value, null);
            //insert in bucket
        //  maintainOrderAfterInsert(newEntry);  
            Entry<K,V>curr=buckets[hash];

            if(curr==null)
            {
              buckets[hash]=newEntry;
            }
            else
            {
                Entry<K,V> prev=null;
            while(curr!=null)
            {
                if(curr.key.equals(key))
                {
                    replace=true;
                    curr.value=value;
                    break;
                }
                prev=curr;
                curr=curr.next;
            }
             if(prev!=null)
             prev.next=newEntry;
            }
            //newEntry.next=curr;
            //buckets[hash]=newEntry;
            if(replace==false)
            insertInList(newEntry);
            //buckets[hash]=newEntry;
        }
        private void insertInList(Entry<K,V> newEntry)
        {
            if(head==null)
            {
                head=newEntry;
                tail=newEntry;
            }
            else
            {
              tail.after=newEntry;
              newEntry.before=tail;
              tail=newEntry;
            }
        }
        public V get(K key)
        {   
            int hash=hash(key);
            Entry<K,V> curr=buckets[hash];
            while(curr!=null)
            {
                if(curr.key.equals(key))
                {
                    return curr.value;
                }
                curr=curr.next;
            }
            return null;
        }

        public void print()
        {
            Entry<K,V>curr=head;
            while(curr!=null)
            {
              System.out.println("key is "+ curr.key+"val is "+ curr.value+"->");   
              curr=curr.after;
            }
        }   
        private int hash(K key){
            return Math.abs(key.hashCode()) % capacity;
        }

        public void remove(K key)
        {
            int hash=hash(key);
            Entry<K,V>curr=buckets[hash];
            if(curr==null)//no exist
            {
                return;
            }
            Entry<K,V>p=null;
            Entry<K,V>n;
            while(curr!=null)
            {
                n=curr.next;
                if(curr.key.equals(key))
                {
                   if(p==null)//first
                   {
                       buckets[hash]=buckets[hash].next;
                   }
                   else
                   {
                       p.next=n;
                   }
                   //adjust Linked List
                   adjustList(curr);
                   break;
                }
                p=curr;
                curr=n;
            }

        }
        private void adjustList(Entry<K,V> curr)
        {
            if(curr==head)
            {
                head=head.after;
                if(head==null)
                {
                    tail=null;
                }
            }
            else if (curr==tail)
            {
                tail=tail.before;
                tail.after=null;
            }
            else
            {
                curr.before.after=curr.after;
                curr.after.before=curr.before;
            }
        }
        public void deleteAll()
        {
            head=null;
            tail=null;
            for(int i=0;i<capacity;i++)
            {
                buckets[i]=null;
            }   
        }
    }