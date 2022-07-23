package strings.stringmatching;

import java.util.Arrays;

/**
 * the key to KMP algo is prefix suffix match i.e the longest prefix which is also a suffix (LPS)
 *
 * for example take a pattern abcdabcd
 *
 * the prefix = [a,ab,abc,abcd,abcda,abcdab,abcdabc,final string]
 * the suffix = [d,cd,bcd,abcd,dabcd,cdabcd,bcdabcd, final string]
 * we will not consider final string because that'll be used in last step for final comparison
 *
 * in the above example index abcd is the longest prefix which is same as suffix.
 *
 * This LPS tells which index should we move back to in case of pattern match failure
 */
public class KMP {

    public static void main(String[] args) {
        String given = "abxabcabcabyasd";
        String pattern = "abcaby";
        System.out.println(strStr(given, pattern));
    }


    public static int strStr( String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int[] lps = computeKMPTable(needle);
        int i = 0, j = 0, n = haystack.length(), m = needle.length();
        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                ++i; ++j;
                if (j == m) return i - m; // found solution
            } else {
                if (j != 0) j = lps[j - 1]; // try match with longest prefix suffix
                else i++; // don't match -> go to next character of `haystack` string
            }
        }
        return -1;
    }
    private static int[] computeKMPTable(String pattern) {
        int i = 1, j = 0, n = pattern.length();
        int[] lps = new int[n];
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i++] = ++j;
            } else {
                if (j != 0) {
                    j = lps[j - 1]; // try match with longest prefix suffix
                }
                else{
                    i++; // don't match -> go to next character
                }
            }
        }
        return lps;
    }

}
