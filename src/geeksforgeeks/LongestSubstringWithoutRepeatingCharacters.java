package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int counter = 0;
        int result = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) {
                counter++;
            }
            right++;

            while (counter > 0) {
                char charTemp = s.charAt(left);
                if (map.get(charTemp) > 1) {
                    counter--;
                }
                map.put(charTemp, map.get(charTemp) - 1);
                left++;
            }
            result = Math.max(result, right - left);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}