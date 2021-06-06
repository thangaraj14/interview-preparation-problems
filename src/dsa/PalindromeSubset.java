package dsa;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/find-number-distinct-palindromic-sub-strings-given-string/
 * https://www.techiedelight.com/find-possible-palindromic-substrings-string/
 */
class PalindromeSubset {

    public static void expand(String str, int low, int high, Set<String> set) {
        // run till `str[low.high]` is not a palindrome
        while (low >= 0 && high < str.length() && str.charAt(low) == str.charAt(high)) {

            set.add(str.substring(low, high + 1));

            low--;
            high++;
        }
    }

    public static void allPalindromicSubstrings(String str) {

        Set<String> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            // find all odd length palindrome with `str[i]` as a midpoint
            expand(str, i, i, set);

            // find all even length palindrome with `str[i]` and `str[i+1]`
            // as its midpoints
            expand(str, i, i + 1, set);
        }

        set.forEach(System.out::println);
    }

    public static void main(String[] args) {
        String str = "google";
        allPalindromicSubstrings(str);
    }
}