package strings.parentheses;

/**
 * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid
 * Idea :
 *     Idea is similar to other Balance Parentheses related problems. i.e we check the balance of ( and ) brackets.
 *     And whenever number of ) exceeds number of ( , we can say that it is unbalanced from start.
 *     And whenever number of ( exceeds number of ) , we can say that it is unbalanced from end ( this one is trivial).
 *     This can be done by incrementing when we see opening ( bracket and decrementing vice versa.
 */
public class CanBeValid {

    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) return false;

        int open = 0;
        int close = 0;

        // First check balance from left to right
        // For opening '(' brackets
        for (int i = 0; i < n; i++) {

            // If either char is ( or it is unlocked,
            // We can increment open balance
            if (locked.charAt(i) == '0' || s.charAt(i) == '(') open++;
            else close++; // otherwise, decrement balance, since it is ) and also locked

            // Since balance is negative, we have more ')'.
            // And there is no unlocked char available
            // SO, it is invalid string for sure
            if (close > open) return false;
        }

        open = 0;
        close = 0;
        // Then also check balance from right to left
        // For closing ')' brackets
        for (int i = n - 1; i >= 0; i--) {

            // If either char is ) or it is unlocked,
            // We can increment balance
            if (locked.charAt(i) == '0' || s.charAt(i) == ')') close++;
            else open++;

            // Since balance is negative, we have more '('.
            // And there is no unlocked char available
            // SO, it is invalid string for sure

            if (open > close) return false;
        }

        return true;
    }
}
