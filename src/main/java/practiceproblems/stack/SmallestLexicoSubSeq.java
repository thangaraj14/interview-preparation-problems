package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 * https://leetcode.com/problems/remove-duplicate-letters/
 */
public class SmallestLexicoSubSeq {

    /**
     * Question is asking sequence without duplicates but in sorted order
     * The Characters Which are unique will remain in the same order.
     * If we have pushed a unique character then there is no benefit of changing element before unique character.
     *
     * So we process character by character in to a stack
     *   if we find a character which is smaller that stack's peek, we check if we are going to encounter it again by having lastPos array
     *      |--> if yes then we pop from stack and remove from used arr, so we can process the duplicate which will give smaller string
     *
     */
    public String smallestSubsequence(String s) {
        int[] lastPos = new int[26];
        boolean[] used = new boolean[26];
        int n = s.length();
        for (int i = 0; i < n; i++)
            lastPos[s.charAt(i) - 'a'] = i;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (used[c]) continue;
            // if stack has larger value and lastPos of that larger value is not yet reached we pop and place smaller char
            while (!stack.isEmpty() && stack.peekLast() >= c && lastPos[stack.peekLast()] > i) {
                used[stack.pollLast()] = false;
            }
            used[c] = true;
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append((char) (stack.poll() + 'a'));
        return sb.toString();
    }
}
