package dsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/
 * https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/642-design-search-autocomplete-system.html
 */
class AutoCompleteSystem {

    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> counts;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            counts = new HashMap<>();
            isWord = false;
        }
    }

    TrieNode root;
    String prefix;

    public AutoCompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";

        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    private void add(String s, int count) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
            curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
        }
        curr.isWord = true;
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix = prefix + c;

        TrieNode curr = root;

        for (char ch : prefix.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(ch);
        }

        Comparator<Map.Entry<String, Integer>> cmp = (a, b) -> a.getValue() == b.getValue() ?
                b.getKey().compareTo(a.getKey()) :
                a.getValue() - b.getValue();

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(cmp);
        int k = 3;
        for (Map.Entry<String, Integer> entry : curr.counts.entrySet()) {
            pq.offer(entry);
            while (!pq.isEmpty() && pq.size() > k) {
                pq.poll();
            }
        }

        ArrayList<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        String[] sentences = { "i love you", "island", "ironman", "i love leetcode" };
        int[] times = { 5, 3, 2, 9 };
        AutoCompleteSystem autoCompleteSystem = new AutoCompleteSystem(sentences, times);
        System.out.println(autoCompleteSystem.input('i'));
        System.out.println(autoCompleteSystem.input(' '));
    }
}