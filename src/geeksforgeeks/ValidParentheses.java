package geeksforgeeks;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public static boolean isValid(String s) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("({[])}"));
    }
}
