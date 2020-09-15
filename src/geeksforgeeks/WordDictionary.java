package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or 
 * a regular expression string containing only letters a-z or .. A . 
 * means it can represent any one letter.
 *  addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true
 */
public class WordDictionary {
    private TrieNode root;
    
    /** Initialize your data structure here. */
    private class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public boolean isWord;
    }
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
       
        TrieNode temp = root;

        for (char c : word.toCharArray()) {
            if (temp.children.get(c) == null)
                temp.children.put(c, new TrieNode());

            temp = temp.children.get(c);
        }

        temp.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] chs, int k, TrieNode node) {

        if (k == chs.length)
            return node.isWord;

        if (chs[k] == '.') {
            for (Character curr: node.children.keySet()) {
                if (node.children.get(curr) != null && match(chs, k+1, node.children.get(curr)))
                    return true;
            }
        } else 
            return node.children.get(chs[k]) != null && match(chs, k + 1, node.children.get(chs[k]));
        
        return false;
    }
}