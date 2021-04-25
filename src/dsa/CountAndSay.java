package dsa;

/**
 * https://leetcode.com/problems/count-and-say/
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if (n <= 0) {
            return "-1";
        }
        String result = "1";

        for (int i = 1; i < n; i++) {
            result = build(result);
        }
        return result;
    }

    private String build(String result) {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < result.length()) {
            char val = result.charAt(index);
            int count = 0;

            while (index < result.length() && result.charAt(index) == val) {
                index++;
                count++;
            }
            builder.append(count);
            builder.append(val);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(4));
    }

}