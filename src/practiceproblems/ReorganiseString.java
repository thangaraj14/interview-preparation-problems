package practiceproblems;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Input: S = "aab"
 * Output: "aba"
 *
 * Input: S = "aaab"
 * Output: ""
 */
class ReorganiseString {
    public String reorganizeString(String s) {
        if (s == null || s.length() < 1) return "";

        int[] hash = new int[26];
        for (char c : s.toCharArray()) {
            hash[c - 'a']++;
        }

        int max = 0;
        int letter = 0;

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }

        if (max > (s.length() + 1) / 2) return "";

        char[] res = new char[s.length()];

        int pos = 0;
        while (hash[letter] > 0) {
            res[pos] = (char) (letter + 'a');
            pos += 2;
            hash[letter]--;
        }

        // We construct the resulting string in sequence: at position 0, 2, 4, ... and then 1, 3, 5, ...
        // In this way, we can make sure there is always a gap between the same characters

        // Consider this example: "aaabbbcdd", we will construct the string in this way:

        // a _ a _ a _ _ _ _ // fill in "a" at position 0, 2, 4
        // a b a _ a _ b _ b // fill in "b" at position 6, 8, 1
        // a b a c a _ b _ b // fill in "c" at position 3
        // a b a c a d b d b // fill in "d" at position 5, 7

        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (pos >= res.length) pos = 1;

                res[pos] = (char) (i + 'a');
                pos += 2;
                hash[i]--;
            }
        }

        return new String(res);
    }

    public static void main(String[] args) {

        new ReorganiseString().reorganizeString("aabccdeeee");
    }
}