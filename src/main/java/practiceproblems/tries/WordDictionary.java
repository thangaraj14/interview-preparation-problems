package practiceproblems.tries;

/**
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or
 * a regular expression string containing only letters a-z or .. A .
 * means it can represent any one letter.
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 */
public class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode head = root;
        for (int i = 0; i < word.length(); i++) {
            if (head.children[word.charAt(i) - 'a'] == null) head.children[word.charAt(i) - 'a'] = new TrieNode();
            head = head.children[word.charAt(i) - 'a'];
        }

        head.isWord = true;

    }

    public boolean search(String word) {

        return searchUtil(word.toCharArray(), 0, root);
    }

    public boolean searchUtil(char[] words, int start, TrieNode root) {
        if (start == words.length) return root.isWord;

        if (words[start] == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null && searchUtil(words, start + 1, root.children[i])) {
                    return true;
                }
            }
        } else {
            return root.children[words[start] - 'a'] != null && searchUtil(words, start + 1, root.children[words[start] - 'a']);
        }

        return false;
    }

    static class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }
}

