package cess;

/**
 * https://cses.fi/problemset/task/1755
 * https://www.youtube.com/watch?v=ou8Xhp_YO8E&t=767s
 */
public class PalindromeReorder {

    public static String reorderToPalindrome(String s) {

        int[] arr = new int[26];

        for (char c : s.toCharArray()) {
            arr[c - 'A']++;
        }

        int oddCharPos = -1;

        for (int i = 0; i < 26; i++) {
            if (arr[i] == 1 && oddCharPos != -1) {
                return "";
            } else if (arr[i] == 1 && oddCharPos == -1) {
                oddCharPos = i;
            }
        }

        if (oddCharPos == -1 && s.length() % 2 == 1) return "";
        if (oddCharPos != -1 && s.length() % 2 == 0) return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < arr[i] / 2; j++) {
                sb.append(Character.toString(i + 'A'));
            }
        }

        String s1 = sb.reverse().toString();
        sb.reverse();
        if (oddCharPos != -1) {
            sb.append(Character.toString(oddCharPos + 'A'));
        }
        return sb.append(s1).toString();
    }

    public static void main(String[] args) {
        System.out.println(reorderToPalindrome("AAAACACBA"));
    }
}
