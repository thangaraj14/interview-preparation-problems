package dsa;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Unresolved
 */
public class RemoveParenthesisValid {

    public List<String> removeInvalidParentheses(String s) {
        if (isValid(s)) {
            return Collections.singletonList(s);
        }
        List<String> ans = new ArrayList<>();
        //The queue only contains invalid middle steps
        Queue<Tuple> queue = new LinkedList<>();
        //The 3-Tuple is (string, startIndex, lastRemovedChar)
        queue.add(new Tuple(s, 0, ')'));
        while (!queue.isEmpty()) {
            Tuple x = queue.poll();
            //Observation 2, start from last removal position
            for (int i = x.start; i < x.string.length(); ++i) {
                char ch = x.string.charAt(i);
                //Not parentheses
                if (ch != '(' && ch != ')') {
                    continue;
                }
                //Observation 1, do not repeatedly remove from consecutive ones
                if (i != x.start && x.string.charAt(i - 1) == ch) {
                    continue;
                }
                //Observation 3, do not remove a pair of valid parentheses
                if (x.removed == '(' && ch == ')') {
                    continue;
                }
                String t = x.string.substring(0, i) + x.string.substring(i + 1);
                //Check isValid before add
                if (isValid(t)) {
                    ans.add(t);
                }
                //Avoid adding leaf level strings
                else if (ans.isEmpty()) {
                    queue.add(new Tuple(t, i, ch));
                }
            }
        }
        return ans;
    }

    public static boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++count;
            }
            if (c == ')' && count-- == 0) {
                return false;
            }
        }
        return count == 0;
    }
}

class Tuple {
    public final String string;
    public final int start;
    public final char removed;

    public Tuple(String string, int start, char removed) {
        this.string = string;
        this.start = start;
        this.removed = removed;

    }
}