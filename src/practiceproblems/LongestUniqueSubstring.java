package practiceproblems;

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
            if (map.get(c) > 1) {
                counter++;
            }
            end++;

            while (counter > 0) {
                char charTemp = s.charAt(begin);
                if (map.get(charTemp) > 1) {
                    counter--;
                }
                map.put(charTemp, map.get(charTemp) - 1);
                begin++;
            }
            result = Math.max(result, end - begin);
        }
        return result;
    }
    public static int lengthOfLongestSubstringOpt(String s) {
        int res = 0, n = s.length();
        int[] arr = new int[256];
        int startIndex=0;
        for(int curr=0;curr<n;curr++){
            // if already seen, pick the next element as start Index
            startIndex = Math.max(startIndex, arr[s.charAt(curr)]);

            res = Math.max(res, curr-startIndex+1);
            // store curr+1=> next index, so that we can start from here
            arr[s.charAt(curr)] = curr+1;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringOpt("pwwkew"));
    }
}