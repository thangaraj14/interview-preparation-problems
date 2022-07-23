package practiceproblems.parentheses;

/**
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether this string is valid.
 * We define the validity of a string by these rules:
 * <p>
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * <p>
 * Input: "(*))"
 * Output: True
 * <p>
 * Input: "(*)"
 * Output: True
 */
public class ValidParenthesesString {
    public boolean checkValidString(String s) {
        int cmin = 0;
        int cmax = 0; // open parentheses count in range [cmin, cmax]
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin--;
            } else if (c == '*') {
                cmax++; // if `*` become `(` then openCount++
                cmin--; // if `*` become `)` then openCount--
                // if `*` become `` then nothing happens
                // So openCount will be in new range [cmin-1, cmax+1]
            }
            if (cmax < 0) {
                return false; // Currently, don't have enough open parentheses to match close parentheses-> Invalid
            }
            // For example: ())(
            cmin = Math.max(cmin, 0);   // It's invalid if open parentheses count < 0 that's why cmin can't be negative
        }
        return cmin == 0; // Return true if can found `openCount == 0` in range [cmin, cmax]
    }

    public boolean checkValidStringAnother(String s) {
        if (s.length() < 1) return true;

        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') balance--;
            else balance++;

            if (balance < 0) return false;
        }

        if (balance == 0) return true;
        balance = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') balance--;
            else balance++;
            if (balance < 0) return false;

        }
        return true;
    }
}
