package dsa;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * FreqStack has two functions:
 * <p>
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 * <p>
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 * <p>
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 * <p>
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 * <p>
 * pop() -> returns 4.
 * The stack becomes [5,7].
 * <p>
 * https://leetcode.com/problems/maximum-frequency-stack/
 */
public class MaxFreqStack {

    PriorityQueue<EntryStack> maxQueue;
    Map<Integer, Integer> hashMap;
    int sequence = 0;

    public MaxFreqStack() {

        maxQueue = new PriorityQueue<>((a, b) -> {
            if (a.frequency == b.frequency) {
                return Integer.compare(b.sequence, a.sequence);
            }
            return Integer.compare(b.frequency, a.frequency);
        });
        hashMap = new HashMap<>();
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

    public static void main(String[] args) {
        MaxFreqStack maxFreqStack = new MaxFreqStack();
        maxFreqStack.push(1);
        maxFreqStack.push(2);
        maxFreqStack.push(2);
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        maxFreqStack.push(1);
        maxFreqStack.push(1);
        maxFreqStack.push(1);

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
