package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
public class DecodeString {
    public static String decodeString(String s) {
        if (s == null)
            return "";

        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<String> characterStack = new ArrayDeque<>();
        int start = 0;
        StringBuilder result = new StringBuilder();
        while (start < s.length()) {
            // whenever we see number, we push to countStack
            if (Character.isDigit(s.charAt(start))) {
                int num = 0;
                while (Character.isDigit(s.charAt(start))) {
                    num = num * 10 + s.charAt(start) - '0';
                    start++;
                }
                countStack.push(num);
            } else if (s.charAt(start) == '[') {
                // whenever we see a open brace we push the string we have to characterStack
                characterStack.push(result.toString());
                result = new StringBuilder();
                start++;
            } else if (s.charAt(start) == ']') {
                // whenever a closing brace comes, we pop the last seen countStack and last seen string
                // and replicate that string and stores in temp characterStack
                StringBuilder sb = new StringBuilder(characterStack.pop());
                int tempCount = countStack.pop();
                sb.append(result.toString().repeat(tempCount));
                result = sb;
                start++;
            } else {
                // whenever we see a char, we push to characterStack string
                result.append(s.charAt(start++));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }
}