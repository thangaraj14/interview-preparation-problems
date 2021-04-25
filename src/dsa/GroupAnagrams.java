package dsa;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // int[] cache= new int[26];
            // for(char ch: s.toCharArray()){
            //     cache[ch-'a']++;
            // }
            // String hash=Arrays.toString(cache);
            char[] te = s.toCharArray();
            Arrays.sort(te);
            String hash = new String(te);

            List<String> list = map.getOrDefault(hash, new LinkedList<>());
            list.add(s);
            map.put(hash, list);
        }

        return new ArrayList(map.values());
    }
}