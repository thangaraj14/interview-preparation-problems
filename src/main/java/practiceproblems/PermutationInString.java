package practiceproblems;

import java.util.Arrays;

/**
 * tricky sliding window
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * <p>
 * https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {

    // How do we know string p is a permutation of string s? Easy, each character in p is in s too.
    // So we can abstract all permutation strings of s to a map (Character -> Count). i.e. abba -> {a:2, b:2}.
    // Since there are only 26 lower case letters in this problem,
    // we can just use an array to represent the map.
    // How do we know string s2 contains a permutation of s1?
    // We just need to create a sliding window with length of s1,
    // move from beginning to the end of s2.
    // When a character moves in from right of the window,
    // we subtract 1 to that character count from the map.
    // When a character moves out from left of the window, we add 1 to that character count.
    // So once we see all zeros in the map,
    // meaning equal numbers of every characters between s1 and
    // the substring in the sliding window, we know the answer is true.
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return false;
        }
        int[] cache = new int[26];
        for (char s : s1.toCharArray()) {
            cache[s - 'a']++;
        }

        int right = 0;

        while (right < s2.length()) {
            cache[s2.charAt(right) - 'a']--;
            if (right >= s1.length()) {
                cache[s2.charAt(right - s1.length()) - 'a']++; // sliding the window
            }
            if (Arrays.stream(cache).sum()==0) {
                return true;
            }
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaoo"));
    }
}