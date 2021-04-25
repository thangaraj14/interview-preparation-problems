package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/solution/
 */
// sort the string by character frequency from high to low
public class FrequencySort {

    // this could be easily done with priority queue but this is ref for bucket sort
    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        List<Character>[] bucket = new List[s.length() + 1];

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
        frequencySort("");
    }
}