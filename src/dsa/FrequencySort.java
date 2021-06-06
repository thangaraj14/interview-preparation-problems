package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/solution/
 * <p>
 * Given a string s, sort it in decreasing order based on the frequency of characters, and return the sorted string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 105
 * s consists of English letters and digits.
 */
// sort the string by character frequency from high to low
public class FrequencySort {

    // this could be easily done with priority queue but this is ref for bucket sort
    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        List<Character>[] bucket = new ArrayList[s.length() + 1];

        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        StringBuilder sb = new StringBuilder();
        // since this is max frequency are iterating from last else we'd go from start
        for (int pos = bucket.length - 1; pos >= 0; pos--)
            if (bucket[pos] != null) {
                for (char c : bucket[pos])
                    for (int i = 0; i < map.get(c); i++)
                        sb.append(c);
            }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("treee"));
    }
}