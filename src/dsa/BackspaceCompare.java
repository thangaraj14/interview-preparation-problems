package dsa;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 */
class BackspaceCompare {

    public static boolean backspaceCompare(String S, String T) {

        if (S == null || T == null) {
            return S == T;
        }
        int i = S.length() - 1, j = T.length() - 1;
        int cnt1 = 0, cnt2 = 0;//number of '#';
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (S.charAt(i) == '#' || cnt1 > 0)) {
                if (S.charAt(i) == '#') {
                    cnt1++;
                } else {
                    cnt1--;
                }
                i--;
            }
            while (j >= 0 && (T.charAt(j) == '#' || cnt2 > 0)) {
                if (T.charAt(j) == '#') {
                    cnt2++;
                } else {
                    cnt2--;
                }
                j--;
            }
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
        //         S = "ab##", T = "c#d#"
        //"ab#c", "ad#c")
        // "a##c", T = "#a#c"
        System.out.println(backspaceCompare("a##c", "#a#c"));
    }
}