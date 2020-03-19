package geeksforgeeks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// unresolved
public class IsomorphicArray {
    public Collection<List<String>> groupIsomorphicStrings(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        Map<String, List<String>> hashToList = new HashMap<>();

        for (String string : strings) {
            String hash = hash(string);

            if (!hashToList.containsKey(hash)) {
                hashToList.put(hash, new ArrayList<>());
            }

            hashToList.get(hash).add(string);
        }
        return hashToList.values();
    }

    private String hash(String s) {
        if (s.isEmpty()) {
            return "";
        }

        int count = 1;
        StringBuilder hash = new StringBuilder();

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {

            if (map.containsKey(c)) {
                hash.append(map.get(c));
            } else {
                map.put(c, count++);
                hash.append(map.get(c));
            }
        }
        return hash.toString();
    }
}
