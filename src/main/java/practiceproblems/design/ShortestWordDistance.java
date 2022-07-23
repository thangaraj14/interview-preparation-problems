package practiceproblems.design;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/shortest-word-distance-ii
 */
public class ShortestWordDistance {

    HashMap<String, ArrayList<Integer>> locations;

    public ShortestWordDistance(String[] words) {
        this.locations = new HashMap<>();

        // Prepare a mapping from a word to all it's locations (indices).
        for (int i = 0; i < words.length; i++) {
            ArrayList<Integer> loc = this.locations.getOrDefault(words[i], new ArrayList<>());
            loc.add(i);
            this.locations.put(words[i], loc);
        }
    }

    public int shortest(String word1, String word2) {
        ArrayList<Integer> loc1, loc2;

        // Location lists for both the words
        // the indices will be in SORTED order by default
        loc1 = this.locations.get(word1);
        loc2 = this.locations.get(word2);

        int l1 = 0, l2 = 0, minDiff = Integer.MAX_VALUE;
        while (l1 < loc1.size() && l2 < loc2.size()) {
            minDiff = Math.min(minDiff, Math.abs(loc1.get(l1) - loc2.get(l2)));
            if (loc1.get(l1) < loc2.get(l2)) {
                l1++;
            } else {
                l2++;
            }
        }

        return minDiff;
    }

    /**
     * https://leetcode.com/problems/shortest-word-distance-iii
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int min = Integer.MAX_VALUE;
        int p1 = -1;
        int p2 = -1;
        boolean same = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (same) {
                    p2 = p1;  // Deal with another pointer too
                }
                p1 = i;
            } else if (words[i].equals(word2)) {
                p2 = i;
            }

            if (p1 != -1 && p2 != -1) {
                min = Math.min(min, Math.abs(p1 - p2));
            }
        }
        return min;
    }

    public int shortestDistance(String[] words, String word1, String word2) {
        int ret = Integer.MAX_VALUE, index1 = -1, index2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
                if (index2 >= 0) ret = Math.min(ret, i - index2);
            } else if (words[i].equals(word2)) {
                index2 = i;
                if (index1 >= 0) ret = Math.min(ret, i - index1);
            }
        }
        return ret;
    }
}
