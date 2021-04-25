package dsa;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

    public static String decodeString(String s) {
        String result = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resultStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resultStack.push(result);
                result = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resultStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(result);
                }
                result = temp.toString();
                idx++;
            } else {
                result += s.charAt(idx++);
            }
        }
        return result;
    }

    //3[a]2[bc]
    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }
}