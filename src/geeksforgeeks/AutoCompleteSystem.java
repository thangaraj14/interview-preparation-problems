package geeksforgeeks;

import java.util.*;

/**
 * https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/642-design-search-autocomplete-system.html
 */
class AutoCompleteSystem {

   static class TrieNode {
       Map<Character, TrieNode> children;
       Map<String, Integer> counts;
       boolean isWord;

       public TrieNode() {
           children = new HashMap<>();
           counts = new HashMap<>();
           isWord = false;
       }
   }

   TrieNode root; // points to the root of the trie to be initialised
   String prefix; // concat and stores the input char sequence from main function

   public AutoCompleteSystem(String[] sentences, int[] times) {
       root = new TrieNode();
       prefix = "";
        // for the given word and freq value we proceed to add it to trie
       for (int i = 0; i < sentences.length; i++) {
           add(sentences[i], times[i]);
       }
   }

   private void add(String s, int count) {
       TrieNode curr = root;
       for (char c : s.toCharArray()) {
           curr.children.putIfAbsent(c, new TrieNode());
           curr = curr.children.get(c);
           // for every node in the trie(which has one char val)
           //the counts map will store the original word along with it's freq value
           // for the given input 'i love leetcode' and 'i love you'
           // the trie    Node(i) => Map(i love leetcode -> 2, i love you->5, island->3, ironman->2)
           //              /
           //           Node(' ') => Map(i love leetcode -> 2, i love you->5)

           // the trie    Node(i) => Map(i love leetcode -> 2, i love you->5, island->3, ironman->2)
           //               \
           //           Node('s') => Map(island->3)
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

       Comparator<Map.Entry<String, Integer>> cmp = (a, b) -> a.getValue().equals(b.getValue()) ?
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
       int[] times = { 5, 3, 2, 2 };
       AutoCompleteSystem autoCompleteSystem = new AutoCompleteSystem(sentences, times);
       System.out.println(autoCompleteSystem.input('i'));
       System.out.println(autoCompleteSystem.input(' '));
   }
}