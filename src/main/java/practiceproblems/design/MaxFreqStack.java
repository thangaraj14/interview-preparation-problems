package practiceproblems.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * FreqStack has two functions:
 *
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element,
 * the element closest to the top of the stack is removed and returned.
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 */
public class MaxFreqStack {

    PriorityQueue<EntryStack> maxQueue;
    Map<Integer, Integer> hashMap;
    int sequence = 0;

    public MaxFreqStack() {
            
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
        maxQueue.offer(new EntryStack(x, hashMap.get(x), sequence++));
    }

    public int pop() {
        EntryStack temp = maxQueue.poll();
        hashMap.put(temp.val, temp.frequency - 1);

        return temp.val;
    }

}

// 1. val = value of the number
// 2. frequency = current frequency of the number when it was pushed to the heap
// 3. sequenceNumber = a sequence number, to know what number came first
class EntryStack {
    int val;
    int frequency;
    int sequence;

    public EntryStack(int val, int frequency, int sequence) {
        this.val = val;
        this.frequency = frequency;
        this.sequence = sequence;
    }
}

/**
 * tricky
 */
class FreqStack {

    HashMap<Integer, LinkedList<Integer>> st ; // stores max freq as key and stack consist of all element that has freq equals to key.

    HashMap<Integer,Integer> freqMap; // frequency map
    int maxFreq; // will contain the max frequency of element.

    public FreqStack() {
        st = new HashMap<>() ;
        freqMap = new HashMap<>();
        maxFreq = 0 ;
    }

    public void push(int val) {

        freqMap.put(val, freqMap.getOrDefault(val,0)+1) ; // incrementing the freq of current val

        int freq= freqMap.get(val) ;

        st.getOrDefault(freq, new LinkedList<>()).addFirst(val) ; // adding on top.

        maxFreq = Math.max(maxFreq, freqMap.get(val)) ; // updating the max frequency
    }

    public int pop() {

        // getting the max freq element.

        int val = st.get(maxFreq).removeFirst() ;

        if(st.get(maxFreq).isEmpty()){
            // means stack becomes empty that means we don't have any more element of maxFreq so decrease the maxFreq.
            maxFreq-- ;
        }

        // also decrement the freq of val in fmap.

        int freq = freqMap.get(val) ;

        if(freq == 1){
            freqMap.remove(val) ;
        }
        else{
            freqMap.put(val,freq-1) ;
        }

        return val ;

    }
}
