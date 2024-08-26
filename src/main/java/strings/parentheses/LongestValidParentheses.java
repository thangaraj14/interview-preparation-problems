package strings.parentheses;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/submissions/
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {

        int open = 0;
        int close = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(open + close, maxLen);
            }
            if (close > open) {
                open = 0;
                close = 0;
            }
        }
        open = 0;
        close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(open + close, maxLen);
            }
            if (open > close) {
                open = 0;
                close = 0;
            }
        }

        return maxLen;
    }
}
