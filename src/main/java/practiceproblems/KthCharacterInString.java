package practiceproblems;

/**
 * TODO
 * given 2 string s , t we have to find kth character from the string formed by following process lets say x = ""
 * i = 1 append s to x 1 time
 * i = 2 append t to x 2 times
 * i = 3 append s to x 3 times
 * i = 4 append t to x 4 times
 * i = 5 append s to x 5 times
 * and so on
 * <p>
 * you are given k <= 10^16, you have to find kth character from x formed using above process.
 * eg: s = "a", t = "bc", k = 4 ---> given input
 * Output: b
 * (since string x = "abcbcaaabcbcbcbc..... 4th char is b)
 */
public class KthCharacterInString {

    public static char kthCharacter(String s, String t, int k) {
        k = k - 1;
        for (int i = 1; k >= 0; i++) {
            if ((i) % 2 == 1) {//odd s
                int currIdxLen1 = (i) * s.length();
                if (currIdxLen1 > k) { // can't reduce k further
                    return s.charAt(k % s.length());
                } else {
                    k = k - currIdxLen1;
                }
            } else {
                int currIdxLen = (i) * t.length();
                if (currIdxLen > k) { // can't reduce k further
                    return t.charAt(k % t.length());
                } else {
                    k = k - currIdxLen;
                }
            }
        }
        return 'x';
    }
}
