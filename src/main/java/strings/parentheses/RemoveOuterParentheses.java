package strings.parentheses;

// https://leetcode.com/problems/remove-outermost-parentheses/
public class RemoveOuterParentheses {

    /**
     * opened count the number of opened parenthesis.
     * Add every char to the result,
     * unless the first left parenthesis,
     * and the last right parenthesis.
     * @param s
     * @return
     */
    public String removeOuterParentheses(String s) {
        int opened = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                if (opened > 0) sb.append(ch);
                opened++;

            } else {
                if (opened > 1) sb.append(ch);
                opened--;
            }
        }
        return sb.toString();
    }
}
