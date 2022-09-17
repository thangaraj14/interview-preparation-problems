package practiceproblems.parentheses;

/**
 * tricky braces
 *
 * https://leetcode.com/problems/valid-parenthesis-string/discuss/543521/Java-Count-Open-Parenthesis-O(n)-time-O(1)-space-Picture-Explain
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

            /**
             * Case - 1:
             * If cmax < 0, the number of ')' is lesser than 0. We immediately return false.
             * Why : Let's take an example "())", in this case, cmax would be less than 0 because we have two ' )' and only one '('.
             * Now irrespective of how many '*' we have, this sequence is already invalid, hence we return false.
             */
            if (cmax < 0) {
                return false; // Currently, don't have enough open parentheses to match close parentheses-> Invalid
            }

            /**
             * Case - 2:
             * cmin = Math.max(cmin, 0)
             *
             * The way I got to wrap my head around this was:
             * Cmin and Cmax are both subtracted by 1, whenever we encounter a ")".
             * Therefore, Case -1 covers the case in which we have more ")" than "(".
             * Now the additional case we have to look at is, when we have extra ")", which we can account to the "*" [Since we do --cmin here].
             *
             * However, we can just ignore the "*" as empty strings in this case.
             * Example: "( ) * * "
             * cmax = 1 0 1 2
             * cmin = 1 0 0 0 -> We don't want the last two to become 1 0 -1 -2
             *
             * We can see that the cmin values would become -1 and -2 for the last two "*".
             * However this would mean we would be adding additional ")", which makes the sequence "()))".
             * This is not a right sequence. Therefore, we must keep them as empty strings.
             * Hence we do a max with 0, which implies that if we have additional "*", we don't take them as ")", instead we treat them as empty strings.
             */
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
