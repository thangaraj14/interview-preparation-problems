package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

public class MobileKeyPadCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] charsCombs = { "--", "00", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        generateCombinations(digits, charsCombs, digits.length(), 0, new StringBuilder(), result);
        return result;
    }

    public void generateCombinations(String digits, String[] charsCombos, int len, int start, StringBuilder sb,
            List<String> result) {
        if (start == len) {
            result.add(sb.toString());
            return;
        }

        for (char ch : charsCombos[digits.charAt(start) - '0'].toCharArray()) {
            sb.append(ch);
            generateCombinations(digits, charsCombos, len, start + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        new MobileKeyPadCombinations().letterCombinations("23");
    }
}