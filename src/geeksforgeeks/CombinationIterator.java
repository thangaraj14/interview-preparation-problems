package geeksforgeeks;

// CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/iterator-for-combination/
 */
// iterator.next(); // returns "ab"
// iterator.hasNext(); // returns true
// iterator.next(); // returns "ac"
// iterator.hasNext(); // returns true
// iterator.next(); // returns "bc"
// iterator.hasNext(); // returns false
public class CombinationIterator {

    Deque<String> deque;

    public CombinationIterator(String characters, int combinationLength) {
        deque = new ArrayDeque<>();
        generateCombinations(characters, deque, new StringBuilder(), combinationLength, 0);
    }

    public String next() {
        if (hasNext()) {
            return deque.poll();
        }
        return "";
    }

    public boolean hasNext() {
        return !deque.isEmpty();
    }

    public void generateCombinations(String characters, Deque<String> deque, StringBuilder sb, int limit, int start) {

        if (sb.length() == limit) {
            deque.offer(sb.toString());
            return;
        }
        for (int i = start; i < characters.length(); i++) {
            sb.append("" + characters.charAt(i));
            generateCombinations(characters, deque, sb, limit, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}