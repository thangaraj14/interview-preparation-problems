package dsa;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] sum = new int[26];

            for (int i = 0; i < str.length(); i++) {
                sum[str.charAt(i) - 'a']++;
            }

            String key = Arrays.toString(sum);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                map.put(key, new ArrayList<>(Arrays.asList(str)));
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        List<List<String>> lists = groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });
        lists.forEach(System.out::println);
    }
}