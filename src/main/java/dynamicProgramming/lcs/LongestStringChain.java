package dynamicProgramming.lcs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-string-chain/
 */
public class LongestStringChain {

    /**
     * Algorithm
     * Initialize a set (wordsPresent) and add all the words in the list to the set. This set will be used to check if a word is present in the list.
     * Initialize a map (memo) having key type as String and value type as Integer. This map will store the length of the longest possible word sequence where the key is the last word in the sequence.
     * Iterate over the list. For each word in the list perform a depth-first search.
     * In the DFS, consider the current word (currentWord) as the last word in the word sequence.
     * If currentWord was encountered previously we just return its corresponding value in the map memo.
     * Initialize maxLength to 1.
     * Iterate over the entire length of the currentWord.
         * Create all possible words (newWord) by taking out one character at a time.
         * If newWord is present in the set perform a DFS with this word and store the intermediate result in a variable currentLength.
         * Update the maxLength so that it contains the length of the longest sequence possible where the currentWord is the end word.
     * Set the maxLength as the value for currentWord (key) in the map.
     * Return maxLength.
     */
    private int dfs(Set<String> words, Map<String, Integer> memo, String currentWord) {
        // If the word is encountered previously we just return its value present in the map (memoization).
        if (memo.containsKey(currentWord)) {
            return memo.get(currentWord);
        }
        // This stores the maximum length of word sequence possible with the 'currentWord' as the
        int maxLength = 1;
        StringBuilder sb = new StringBuilder(currentWord);

        // creating all possible strings taking out one character at a time from the `currentWord`
        for (int i = 0; i < currentWord.length(); i++) {
            sb.deleteCharAt(i);
            String newWord = sb.toString();
            // If the new word formed is present in the list, we do a dfs search with this newWord.
            if (words.contains(newWord)) {
                int currentLength = 1 + dfs(words, memo, newWord);
                maxLength = Math.max(maxLength, currentLength);
            }
            sb.insert(i, currentWord.charAt(i));
        }
        memo.put(currentWord, maxLength);

        return maxLength;
    }

    public int longestStrChain(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> wordsPresent = new HashSet<>();
        Collections.addAll(wordsPresent, words);
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, dfs(wordsPresent, memo, word));
        }
        return ans;
    }

    public int longestStrChainBottomUp(String[] words) {
        Map<String, Integer> cache = new HashMap<>();


        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        int result = 0;

        for (String word : words) {
            int count = 0;

            for (int i = 0; i < word.length(); i++) {
                String temp = new StringBuilder(word).deleteCharAt(i).toString();
                count = Math.max(count, cache.getOrDefault(temp, 0) + 1);

            }

            cache.put(word, count);
            result = Math.max(result, count);
        }

        return result;
    }
}
