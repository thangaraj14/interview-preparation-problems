package geeksforgeeks;

public class MaxFreqStack {

    PriorityQueue<Entry> maxQueue;
    Map<Integer, Integer> hashMap;
    int sequence = 0;

    public FreqStack() {
            
            maxQueue= new PriorityQueue<>((a,b)->{
                if(a.frequency==b.frequency){
                    return Integer.compare(b.sequence,a.sequence);
                }
                return Integer.compare(b.frequency,a.frequency);
            });
            hashMap= new HashMap<>();
        }

    public void push(int x) {
        hashMap.put(x, hashMap.getOrDefault(x, 0) + 1);
        maxQueue.offer(new Entry(x, hashMap.get(x), sequence++));
    }

    public int pop() {
        Entry temp = maxQueue.poll();
        hashMap.put(temp.val, temp.frequency - 1);

        return temp.val;
    }
}

// 1. val // value of the number
// 2. frequency // current frequency of the number when it was pushed to the heap
// 3. sequenceNumber // a sequence number, to know what number came first
class Entry {
    int val;
    int frequency;
    int sequence;

    public Entry(int val, int frequency, int sequence) {
        this.val = val;
        this.frequency = frequency;
        this.sequence = sequence;
    }
}
