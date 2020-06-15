package geeksforgeeks;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0)
            return result;

        generateUtil(n, new StringBuilder(), 0, 0, result);

        return result;
    }

    public void generateUtil(int n, StringBuilder paran, int open, int close, List<String> result) {
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