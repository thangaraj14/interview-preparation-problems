package dsa;

/**
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0;
        int tail = s.length() - 1;
        char cHead;
        char cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        isPalindrome("A man, a plan, a canal: Panama");
    }
}