package practiceproblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstringKDistinct {

    public static final int CHAR_RANGE = 128;
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
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

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("aaaaaa", 2));
    }

    public static String findLongestSubstring(String str, int k)
    {
        // stores the longest substring boundaries
        int end = 0, begin = 0;

        // set to store distinct characters in a window
        Set<Character> window = new HashSet<>();

        // Count array `freq` stores the frequency of characters present in the
        // current window. We can also use a map instead of a count array.
        int[] freq = new int[CHAR_RANGE];

        // `[low…high]` maintains the sliding window boundaries
        for (int low = 0, high = 0; high < str.length(); high++)
        {
            window.add(str.charAt(high));
            freq[str.charAt(high)]++;

            // if the window size is more than `k`, remove characters from the left
            while (window.size() > k)
            {
                // If the leftmost character's frequency becomes 0 after
                // removing it in the window, remove it from the set as well
                if (--freq[str.charAt(low)] == 0) {
                    window.remove(str.charAt(low));
                }

                low++;        // reduce window size
            }

            // update the maximum window size if necessary
            if (end - begin < high - low)
            {
                end = high;
                begin = low;
            }
        }

        // return the longest substring found at `str[begin…end]`
        return str.substring(begin, end + 1);
    }

}