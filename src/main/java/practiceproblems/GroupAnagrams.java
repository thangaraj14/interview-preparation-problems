package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String s1 = new String(temp);
            map.putIfAbsent(s1, new ArrayList<>());
            map.get(s1).add(s);
        }
        return new ArrayList<>(map.values());
    }
}