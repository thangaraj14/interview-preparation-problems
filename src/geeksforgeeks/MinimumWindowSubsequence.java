package geeksforgeeks;

/**
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 */
// unresolved
public class MinimumWindowSubsequence  {
    public String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) {
            return "";
        }

        /**
         * we can conduct two steps by using two pointers for this probelm:
         * 1. check feasibility from left to right
         * 2. check optimization from right to left
         * we can traverse from left to right, find a possible candidate until reach the first ending character of T
         * eg: for the string s = abcdebdde and t = bde, we should traverse s string until we find first e,
         * i.e. abcde, then traverse back from current "e" to find if we have other combination of bde with smaller
         * length.
         * @param right: fast pointer that always points the last character of T in S
         * @param left: slow pointer that used to traverse back when right pointer find the last character of T in S
         * @param tIndex: third pointer used to scan string T
         * @param minLen: current minimum length of subsequence
         * */
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        while (right < S.length()) {
            int tIndex = 0;
            // use fast pointer to find the last character of T in S
            while (right < S.length()) {
                if (S.charAt(right) == T.charAt(tIndex)) {
                    tIndex++;
                }
                if (tIndex == T.length()) {
                    break;
                }
                right++;
            }

            // if right pointer is over than boundary
            if (right == S.length()) {
                break;
            }

            // use another slow pointer to traverse from right to left until find first character of T in S
            int left = right;
            tIndex = T.length() - 1;
            while (left >= 0) {
                if (S.charAt(left) == T.charAt(tIndex)) {
                    tIndex--;
                }
                if (tIndex < 0) {
                    break;
                }
                left--;
            }
            // if we found another subsequence with smaller length, update result
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                result = S.substring(left, right + 1);
            }
            // WARNING: we have to move right pointer to the next position of left pointer, NOT the next position
            // of right pointer
            right = left + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.printf(new MinimumWindowSubsequence().minWindow("abcdebdde","bcde"));
    }
}
