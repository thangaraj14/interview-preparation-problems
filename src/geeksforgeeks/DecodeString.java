package geeksforgeeks;

import java.util.ArrayDeque;
import java.util.Deque;

// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
public class DecodeString {
    public String decodeString(String s) {
        if (s == null)
            return "";

        Deque<Integer> count = new ArrayDeque<>();
        Deque<String> result = new ArrayDeque<>();
        int start = 0;
        StringBuilder tempResult = new StringBuilder();
        while (start < s.length()) {
            // whenever we see number, we push to count queue
            if (Character.isDigit(s.charAt(start))) {
                int num = 0;
                while (Character.isDigit(s.charAt(start))) {
                    num = num * 10 + s.charAt(start) - '0';
                    start++;
                }
                count.push(num);
            } else if (s.charAt(start) == '[') {
                // whenever we see a open brace we push the string we have to result queue
                result.push(tempResult.toString());
                tempResult = new StringBuilder();
                start++;
            } else if (s.charAt(start) == ']') {
                // whenever a closing brace comes, we pop the last seen count and last seen string
                // and replicate that string and stores in temp result
                StringBuilder sb = new StringBuilder(result.pop());
                int tempCount = count.pop();
                for (int i = 0; i < tempCount; i++) {
                    sb.append(tempResult);
                }
                tempResult = sb;
                start++;
            } else {
                // whenever we see a char, we push to result string
                tempResult.append(s.charAt(start++));
            }
        }

        return tempResult.toString();
    }
}