package graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * tricky topological sort
 *
 * https://leetcode.com/problems/alien-dictionary
 *
 * Really nice solution! Let me try to explain the code with example in the problem description:
 *
 * First, build a degree map for each character in all the words:
 *
 * w:0
 * r:0
 * t:0
 * f:0
 * e:0
 *
 * Then build the hashmap by comparing the adjacent words, the first character that is different between two adjacent words reflect the lexicographical order. For example:
 *
 *  "wrt",
 *  "wrf",
 *     first different character is 3rd letter, so t comes before f
 *
 *  "wrf",
 *  "er",
 *     first different character is 1rd letter, so w comes before e
 *
 * The characters in set come after the key. x->y means letter x comes before letter y. x -> set: y,z,t,w means x comes before all the letters in the set. The final HashMap "map" looks like.
 *
 * t -> set: f
 * w -> set: e
 * r -> set: t
 * e -> set: r
 *
 * and final HashMap "degree" looks like, the number means "how many letters come before the key":
 *
 * w:0
 * r:1
 * t:1
 * f:1
 * e:1
 *
 * Then use Kahn's algorithm to do topological sort. This is essentially BFS.
 */

public class AlienDictionary {
    public String alienOrder(String[] words) {
        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            // One edge case  is when the second word is the prefix of the first word, for example: ["abc", "ab"]
            // Because the prefix should always be at the front.
            // check for cases like, ["wrtkj","wrt"]; it's invalid, because this input is not in sorted lexicographical order
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        // Step 2: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) {
            if (counts.get(c)==0) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next)==0) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }
}
