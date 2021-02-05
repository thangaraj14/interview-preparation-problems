package geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 */
class PalindromePartitioning {

    public List<List<String>> partition(String s) {

        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }

        List<List<String>> result = new ArrayList<>();
        backtrackingUtil(s, result, new ArrayList<>());

        return result;
    }

    public void backtrackingUtil(String s, List<List<String>> result, List<String> tempList) {
        if (s == null || s.length() == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String temp = s.substring(0, i);
            if (isPalindrome(temp)) {
                tempList.add(temp);
                backtrackingUtil(s.substring(i), result, tempList);
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
        System.out.println(partitioning.partition("aabcb"));
    }
}