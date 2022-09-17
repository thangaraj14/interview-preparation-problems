package dynamicProgramming.palindrome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * The aim to partition the string into all possible palindrome combinations.
 * To achieve this, we must generate all possible substrings of a string by partitioning at every index
 * until we reach the end of the string.
 * Example, abba can be partitioned as ["a","ab","abb","abba"].
 * Each generated substring is considered as a potential candidate if it's a Palindrome
 */
class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }

        List<List<String>> result = new ArrayList<>();
        backtrackingUtil(s, result, new ArrayList<>(), 0);

        return result;
    }

    public void backtrackingUtil(String s, List<List<String>> result, List<String> tempList, int start) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String temp = s.substring(start, i + 1);
            if (isPalindrome(temp)) {
                tempList.add(temp);
                backtrackingUtil(s, result, tempList, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning partitioning = new PalindromePartitioning();
        partitioning.partition("aab");
    }
}




