package geeksforgeeks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAnagrams {

    public static List<Integer> findAnagrams(String s, String t) {

        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length()) {
            return result;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int slow = 0;
        int fast = 0;

        while (fast < s.length()) {
            char c = s.charAt(fast);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    counter--;
                }
            }
            fast++;

            while (counter == 0) {
                char tempChar = s.charAt(slow);
                if (map.containsKey(tempChar)) {
                    map.put(tempChar, map.get(tempChar) + 1);
                    if (map.get(tempChar) > 0) {
                        counter++;
                    }
                }
                if (fast - slow == t.length()) {
                    result.add(slow);
                }
                slow++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> anagrams = findAnagrams("cbaebabacd", "abc");
        anagrams.forEach(System.out::println);
    }
}