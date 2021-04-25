package dp.lcs;

/**
 * <p>
 * Reference https://leetcode.com/problems/wildcard-matching/
 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0&t=471s&ab_channel=TusharRoy-CodingMadeSimple
 */
public class WildCardMatching {

    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        // replace multiple * with one *
        // e.g a**b***c --> a*b*c
        // write index is pattern's length after trunc which is a*b*c = 5
        int writeIndex = 0;
        boolean isFirst = true;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                }
            } else {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            }
        }
        boolean[][] T = new boolean[str.length + 1][writeIndex + 1];
        if (writeIndex > 0 && pattern[0] == '*') {
            T[0][1] = true;
        }

        T[0][0] = true;
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j <= writeIndex; j++) {
                if (pattern[j - 1] == '?' || str[i - 1] == pattern[j - 1]) {
                    T[i][j] = T[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    T[i][j] = T[i - 1][j] || T[i][j - 1];
                }
            }
        }

        return T[str.length][writeIndex];
    }

    public static void main(String args[]) {
        WildCardMatching wcm = new WildCardMatching();
        System.out.println(wcm.isMatch("xbylmz", "x?y***z"));
    }
}