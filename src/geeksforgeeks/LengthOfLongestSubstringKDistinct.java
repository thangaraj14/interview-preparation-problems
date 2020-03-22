package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringKDistinct {
    /**
     * @param s: A string
     * @param k: An integer
     *
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();

        int result = 0;
        int left = 0;
        int right = 0;

        while (left < s.length()) {
            char ch = s.charAt(left);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                char chright = s.charAt(right);
                map.put(chright, map.get(chright) - 1);
                if (map.get(chright) <= 0) {
                    map.remove(chright);
                }
                right++;
            }
            left++;
            result = Math.max(result, left - right);
        }

        return result;
    }
}