package practiceproblems;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 */
class BackspaceCompare {

    public static boolean backspaceCompare(String S, String T) {
        int count1 = 0; // stores the number of # in string 1
        int count2 = 0; // stores the number of # in string 2
        for (int p1 = S.length() - 1, p2 = T.length() - 1; p1 >= 0 || p2 >= 0; p1--, p2--) {

            while (p1 >= 0 && (count1 != 0 || S.charAt(p1) == '#')) {
                if (S.charAt(p1) == '#') count1++;
                else count1--;
                p1--;
            }
            while (p2 >= 0 && (count2 != 0 || T.charAt(p2) == '#')) {
                if (T.charAt(p2) == '#') count2++;
                else count2--;
                p2--;
            }
            if (p1 < 0 && p2 < 0) return true;
            if (p1 < 0 || p2 < 0) return false;
            if (S.charAt(p1) != T.charAt(p2)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("a##c", "#a#c"));
    }
}