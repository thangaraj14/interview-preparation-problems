package practiceproblems.parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Idea :
 * <p>
 * Idea is similar to other Balance Parentheses related problems. i.e we check the balance of ( and ) brackets.
 * <p>
 * And whenever number of ) exceeds number of ( , we can say that it is unbalanced from start.
 * <p>
 * And whenever number of ( exceeds number of ) , we can say that it is unbalanced from end ( this one is trivial).
 * <p>
 * This can be done by incrementing when we see opening ( bracket and decrementing vice versa.
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        int cur = 0, result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                //cur is local variable. This line stores cur so that it could be retrieved later
                stack.push(cur);
                //From here a new function call starts. So reset cur
                cur = 0;
            } else if (c == ')') {
                //Retrieve cur
                cur = stack.pop();
                //calculate result based on result
                if (s.charAt(i - 1) == '(') {
                    cur += 1;
                } else {
                    cur += result * 2;
                }
                //store intermediate result
                result = cur;
            }
        }
        return result;
    }
}
