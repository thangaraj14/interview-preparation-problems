package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0)
            return result;
        // initially we send empty string and target number of pairs needed
        generateUtil(n, new StringBuilder(), 0, 0, result);

        return result;
    }

    public void generateUtil(int n, StringBuilder paran, int open, int close, List<String> result) {
        if (close == n) { // when close reaches n, we know n pairs have been created
            result.add(paran.toString());
            return;
        }

        if (close < open) { // when close is less than open we add a close and proceed
            paran.append(")");
            generateUtil(n, paran, open, close + 1, result);
            paran.deleteCharAt(paran.length() - 1); // backtracking to remove the last seen
        }
        if (open < n) {
            paran.append("(");
            generateUtil(n, paran, open + 1, close, result);
            paran.deleteCharAt(paran.length() - 1);

        }

    }

}