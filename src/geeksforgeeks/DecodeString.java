package geeksforgeeks;

// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
public class DecodeString {
    public String decodeString(String s) {
        if (s == null)
            return "";

        Deque<Integer> count = new ArrayDeque<>();
        Deque<String> result = new ArrayDeque<>();
        int start = 0;
        StringBuilder tempRes = new StringBuilder();
        while (start < s.length()) {
            if (Character.isDigit(s.charAt(start))) {
                int num = 0;
                while (Character.isDigit(s.charAt(start))) {
                    num = num * 10 + s.charAt(start) - '0';
                    start++;
                }
                count.push(num);
            } else if (s.charAt(start) == '[') {
                result.push(tempRes.toString());
                tempRes = new StringBuilder();
                start++;
            } else if (s.charAt(start) == ']') {
                StringBuilder sb = new StringBuilder(result.pop());
                int tempCount = count.pop();
                for (int i = 0; i < tempCount; i++) {
                    sb.append(tempRes);
                }
                tempRes = sb;
                start++;
            } else {
                tempRes.append(s.charAt(start++));
            }
        }

        return tempRes.toString();
    }
}