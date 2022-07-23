package practiceproblems;

import java.util.ArrayList;
import java.util.List;


/**
 * tricky permutation
 *
 * https://leetcode.com/problems/palindrome-partitioning/
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(s, 0, list, res);
        return res;
    }

    public void dfs(String s, int pos, List<String> list, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            if (isPal(s, pos, i)) { // what we are checking over here is,
                // if we partition the string from index to i Example-(0, 0) is palindrome or not

                list.add(s.substring(pos, i + 1)); // take the substring and store it in our list & call the next substring from index + 1
                dfs(s, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }

    }

    public boolean isPal(String s, int low, int high) {
        while (low < high) if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    public static void main(String[] args) {
        new PalindromePartition().partition("aab");
    }


}