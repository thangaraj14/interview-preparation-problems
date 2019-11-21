package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/*https://leetcode.com/problems/longest-substring-without-repeating-characters/*/
public class LongestUniqueSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int end = 0;
        int counter = 0;
        int result = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) counter++;
            end++;

            while (counter > 0) {
                char charTemp = s.charAt(begin);
                if (map.get(charTemp) > 1) counter--;
                map.put(charTemp, map.get(charTemp) - 1);
                begin++;
            }
            result = Math.max(result, end - begin);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abccbcbb"));
    }
}