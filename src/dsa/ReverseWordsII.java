package dsa;

/**
 * Given a character array s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 * <p>
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * <p>
 * Example 2:
 * <p>
 * Input: s = ["a"]
 * Output: ["a"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
 * There is at least one word in s.
 * s does not contain leading or trailing spaces.
 * All the words in s are guaranteed to be separated by a single space.
 */
public class ReverseWordsII {

    public void reverseWords(char[] s) {
        // Three step to reverse
        // 1, reverse the whole sentence
        reverse(s, 0, s.length - 1);
        // 2, reverse each word
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
        // 3, reverse the last word, if there is only one word this will solve the corner case
        reverse(s, start, s.length - 1);
    }

    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
