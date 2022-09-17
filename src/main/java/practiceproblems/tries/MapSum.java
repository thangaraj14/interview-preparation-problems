package practiceproblems.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/map-sum-pairs/
 * https://www.youtube.com/watch?v=AaMIYNdOz8w&ab_channel=NareshGupta
 *
 * brute force approach is add all the key value to a map
 *
 * for every sum(prefix) call:
 *    iterate -> map
 *       -> map.key.startsWith(prefix) add it to sum
 *     -> return sum
 */
public class MapSum {

    TrieNode root;
    /**
     * Initialize your data structure here.
     */
    Map<String, Integer> sumStorage;

    public MapSum() {
        root = new TrieNode();
        sumStorage = new HashMap<>();
    }

    /**
     * sumStorage is used to track the changes of the existing keys
     *
     *  this is going to update all the chars in the path with value apple,5
     *      diff is initially 5
     *      apple -> 5
     *         a -> 5
     *         |
     *         ap -> 5
     *         |
     *         app -> 5
     *         |
     *         appl -> 5
     *         |
     *         apple -> 5
     *
     *      if we get a new key app,2
     *      diff will be 2 initially
     *         a -> 5+2
     *         |
     *         p -> 5+2
     *         |
     *         ap -> 5 +2
     *         |
     *         app -> 5 + 2
     *         |
     *         appl -> 5
     *         |
     *         apple -> 5
     *
     *     after some time if we get apple , 3
     *     we get difference as 3-(map.get('apple')) = 3-5 = -2
     *     we add the diff to all the elements
     */



    public void insert(String key, int val) {
        TrieNode head = root;
        int diff = val - sumStorage.getOrDefault(key, 0);
        sumStorage.put(key, val);

        for (char c : key.toCharArray()) {
            head.children.putIfAbsent(c, new TrieNode());
            head = head.children.get(c);
            head.value += diff;
        }
    }

    public int sum(String prefix) {
        TrieNode head = root;
        for (char c : prefix.toCharArray()) {
            if (head.children.get(c) == null) return 0;
            head = head.children.get(c);

        }
        return head.value;
    }

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        int value;

        public TrieNode() {
            isWord = false;
            children = new HashMap<>();
            value = 0;
        }
    }

}

