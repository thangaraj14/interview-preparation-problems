package geeksforgeeks;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
class GenerateParenthesis {

    static void formParenthesis(char[] str, int pos, int n, int open, int close) {
        if (close == n) {
            result.add(paran.toString());
            return;
        }

        if (close < open) {
            paran.append(")");
            generateUtil(n, paran, open, close + 1, result);
            paran.deleteCharAt(paran.length() - 1);
        }
        if (open < n) {
            paran.append("(");
            generateUtil(n, paran, open + 1, close, result);
            paran.deleteCharAt(paran.length() - 1);

        }

    }

}