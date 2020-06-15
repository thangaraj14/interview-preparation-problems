package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class WordDictionary {

    private TrieNode root;

    public class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public boolean eow;
    }

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {

        TrieNode temp = root;

        for (char c : word.toCharArray()) {
            if (temp.children.get(c) == null) {
                temp.children.put(c, new TrieNode());
            }

            temp = temp.children.get(c);
        }

        temp.eow = true;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chs, int k, TrieNode node) {

        if (k == chs.length) {
            return node.eow;
        }
        if (chs[k] == '.') {
            for (Character curr : node.children.keySet()) {
                if (node.children.get(curr) != null && match(chs, k + 1, node.children.get(curr))) {
                    return true;
                }
            }
        } else {
            return node.children.get(chs[k]) != null && match(chs, k + 1, node.children.get(chs[k]));
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.addWord("ant");
        wordDictionary.addWord("cat");
        wordDictionary.addWord("mad");
     /*   wordDictionary.search("pad");
        wordDictionary.search("bad");*/
        wordDictionary.search(".ad");
        //        wordDictionary.search("b..");
    }
}
