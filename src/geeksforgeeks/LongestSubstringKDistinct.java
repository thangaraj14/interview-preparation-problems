package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringKDistinct {
    /**
     * @param s: A string
     * @param k: An integer
     *
     * @return: An integer
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();

        int result = 0;
        int right = 0;
        int left = 0;

        while (right < s.length()) {
            char charAtRight = s.charAt(right);
            map.put(charAtRight, map.getOrDefault(charAtRight, 0) + 1);
            while (map.size() > k) {
                char charAtLeft = s.charAt(left);
                map.put(charAtLeft, map.get(charAtLeft) - 1);
                if (map.get(charAtLeft) <= 0) {
                    map.remove(charAtLeft);
                }
                left++;
            }
            right++;
            result = Math.max(result, right - left);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("ecebi", 2));
    }
}