package practiceproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagram {

    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : p.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int counter = map.size();

        int start = 0;
        int end = 0;
        List<Integer> result = new ArrayList<>();
        while (end < s.length()) {
            char temp = s.charAt(end);
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) - 1);
                if (map.get(temp) == 0) counter--;
            }
            end++;

            while (counter == 0) {
                char begin = s.charAt(start);
                if (map.containsKey(begin)) {
                    map.put(begin, map.get(begin) + 1);
                    if (map.get(begin) > 0) counter++;
                }
                if (end - start == p.length()) {
                    result.add(start);
                }
                start++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("abbabab", "ab"));
    }
}
