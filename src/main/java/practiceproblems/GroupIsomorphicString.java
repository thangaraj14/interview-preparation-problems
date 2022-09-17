package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tricky- string hash
 */
public class GroupIsomorphicString {
    public Collection<List<String>> groupIsomorphicStrings(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, List<String>> hashToList = new HashMap<>();

        for (String string : strings) {
            String hash = hash(string);
            hashToList.putIfAbsent(hash, new ArrayList<>());
            hashToList.get(hash).add(string);
        }
        return hashToList.values();
    }

    // this method returns a hash value for every string passed in
    // apple = 12234
    // apply = 12234
    // dog = 123
    // cog = 123
    // romi = 1234
    private String hash(String s) {
        if (s.isEmpty()) {
            return "";
        }
        int count = 1;
        StringBuilder hash = new StringBuilder();

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.putIfAbsent(c, count++);
            hash.append(map.get(c));
        }
        return hash.toString();
    }

    public static void main(String[] args) {
        Collection<List<String>> result = new GroupIsomorphicString()
                .groupIsomorphicStrings(Arrays.asList("apple", "apply", "dog", "cog", "romi"));
        result.stream().forEach(System.out::println);
    }
}
