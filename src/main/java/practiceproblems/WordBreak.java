package practiceproblems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordBreak {

/**
 *
|T| | | | | | | | |
 0 1 2 3 4 5 6 7 8

i = 1
j = 0 sub = l

i = 2
j = 0 sub = le
j = 1 sub = e

i = 3
j = 0 sub = lee
j = 1 sub = ee
j = 2 sub = e

i = 4
j = 0 sub = leet && T[0] and then break, no need to check for rest
|T | | | |T| | | | |
0 1 2 3 4 5 6 7 8

i = 5
j = 0 sub = leetc
j = 1 sub = eetc
j = 2 sub = etc
j = 3 sub = tc
j = 4 sub = c

i = 6
j = 0 sub = leetco
j = 1 sub = eetco
j = 2 sub = etco
j = 3 sub = tco
j = 4 sub = co
j = 5 sub = o

i = 7
j = 0 sub = leetcod
j = 1 sub = eetcod
j = 2 sub = etcod
j = 3 sub = tcod
j = 4 sub = cod
j = 5 sub = od
j = 6 sub = d

i = 8
j = 0 sub = leetcode
j = 1 sub = eetcode
j = 2 sub = etcode
j = 3 sub = tcode
j = 4 sub = code && T[4] and then break

|T| | | |T| | | |T|
 0 1 2 3 4 5 6 7 8
*/
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null) {
            return false;
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && set.contains(s.substring(j, i));
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean wordBreakRec(String s, List<String> wordDict) {
        // create the memoization array to save results and avoid repeat computations
        Boolean[] canBreak = new Boolean[s.length()];

        // convert the list into set for faster lookup
        Set<String> wordSet = new HashSet<>(wordDict);

        // recursion with memoization
        return helper(s, 0, wordSet, canBreak);
    }

    private boolean helper(String s, int startIdx, Set<String> wordSet, Boolean[] canBreak) {
        // in case we've reached the end of string, return true
        if (startIdx == s.length()) return true;
        // else if we've already computed on current substring before
        if (canBreak[startIdx] != null) return canBreak[startIdx]; // auto-unboxing

        boolean res = false;
        // iterate through all indices after startIdx, explore every possible word
        for (int i = startIdx + 1; i <= s.length(); i++) {
            String currWord = s.substring(startIdx, i);
            // skip if this is not a word in the input dictionary
            // recursively call upon the rest of string
            if (wordSet.contains(currWord) && helper(s, i, wordSet, canBreak)) {
                res = true;
                break;
            }
        }
        // add result to memo and return the result
        canBreak[startIdx] = res;
        return res;
    }
}