package stringProblems;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DistinctSubstring {

    public static List<String> kSubstring(String s, int k) {
        Set<Character> window = new LinkedHashSet<>();
        Set<String> result = new LinkedHashSet<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            while (window.contains(s.charAt(end))) {
                window.remove(s.charAt(start));
                start++;
            }

            window.add(s.charAt(end));

            if (window.size() == k) {
                result.add(s.substring(start, end + 1));
                window.remove(s.charAt(start));
                start++;
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        System.out.println(kSubstring("awaglknagawunagwkwagl", 4));
    }
}