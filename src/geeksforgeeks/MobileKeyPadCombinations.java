package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-possible-words-phone-digits/
 */
public class MobileKeyPadCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] keypad = { "--", "00", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        generateCombinations(digits, keypad, digits.length(), 0, new StringBuilder(), result);
        return result;
    }

    public void generateCombinations(String digits, String[] keypad, int len, int startIndex, StringBuilder sb,
            List<String> result) {
        if (startIndex == len) {
            result.add(sb.toString());
            return;
        }

        for (char ch : keypad[digits.charAt(startIndex) - '0'].toCharArray()) {
            sb.append(ch);
            generateCombinations(digits, keypad, len, startIndex + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        new MobileKeyPadCombinations().letterCombinations("23").stream().forEach(System.out::println);
    }
}