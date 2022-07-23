package practiceproblems;

/**
 * https://leetcode.com/problems/one-edit-distance/
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different c
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 */
public class IsEditOneDistanceAway {
    static boolean isOneEdit(String first, String second) {
        // if the input string are same
        if (first.equals(second))
            return false;

        int len1 = first.length();
        int len2 = second.length();
        // If the length difference of the stings is more than 1, return false.
        if ((len1 - len2) > 1 || (len2 - len1) > 1) {
            return false;
        }
        int i = 0, j = 0;
        int diff = 0;
        while (i < len1 && j < len2) {
            char f = first.charAt(i);
            char s = second.charAt(j);
            if (f != s) {
                diff++;
                // delete a character
                if (len1 > len2) {
                    i++;
                }
                // add a character
                if (len2 > len1) {
                    j++;
                }
                // replace a character
                if (len1 == len2) {
                    i++;
                    j++;
                }

            } else {
                i++;
                j++;
            }
            if (diff > 1) {
                return false;
            }
        }
        // If the length of the string is not same. ex. "abc" and "abde" are not one
        // edit distance.
        return diff != 1 || len1 == len2 || (i == len1 && j == len2);
    }

    /*
 * There are 3 possibilities to satisfy one edit distance apart:
 *
 * 1) Replace 1 char:
 	  s: a B c
 	  t: a D c
 * 2) Delete 1 char from s:
	  s: a D  b c
	  t: a    b c
 * 3) Delete 1 char from t
	  s: a   b c
	  t: a D b c
	  *
	  *  tricky substring
 */
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }
}