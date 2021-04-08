package geeksforgeeks;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 */
class BackspaceCompare {

    public static boolean backspaceCompare(String S, String T) {

        if (S == null || T == null) {
            return S == T;
        }
        int i = S.length() - 1, j = T.length() - 1;
        int cnt1 = 0, cnt2 = 0;//number of '#';
        while (i >= 0 || j >= 0) {
            //this while loop is executed 2 times i) when it sees '#' it increments the count 'cnt1'
            // ii) since 'cnt1'>0
            // the above logic is decrementing the 'i' i.e deleting the char before '#'

            while (i >= 0 && (S.charAt(i) == '#' || cnt1 > 0)) {
                if (S.charAt(i) == '#') {
                    cnt1++;
                } else {
                    cnt1--;
                }
                i--;
            }

            // same as previous comment
            while (j >= 0 && (T.charAt(j) == '#' || cnt2 > 0)) {
                if (T.charAt(j) == '#') {
                    cnt2++;
                } else {
                    cnt2--;
                }
                j--;
            }
            // if the non '#' char is not equal, then no need to proceed further
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else {
                return i == -1 && j == -1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("a##c", "#a#c"));
    }
}