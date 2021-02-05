package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

public class TrieImpl {

    private static TrieNode root;

    private void insert(String word) {

        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (null == curr.children.get(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }
        curr.endOfWord = true;
    }

    public static void main(String[] args) {
        TrieImpl trie = new TrieImpl();
        root = new TrieNode();

        trie.insert("hero");
        trie.insert("help");
        trie.insert("helping");
        trie.insert("helps");
        trie.insert("abdir");
        trie.insert("lman");
        trie.insert("he");
        trie.insert("hello");
        trie.print();
        System.out.println(trie.fullTextSearch("abdc"));
        trie.prefixSearch("he");

    }

    private void print() {

        TrieNode temp = root;
        Map<Character, TrieNode> children = temp.children;
        for (Map.Entry<Character, TrieNode> map : children.entrySet()) {
            System.out.println(map.getKey());
            TrieNode node = map.getValue();
            new TrieImpl().parseNode(node);
            System.out.println();
        }

    }

    private void parseNode(TrieNode node) {
        if (null == node) {
            return;
        }
        for (Map.Entry<Character, TrieNode> map : node.children.entrySet()) {
            System.out.print(map.getKey());
            parseNode(map.getValue());
        }
    }

    private boolean fullTextSearch(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (!temp.children.containsKey(word.charAt(i))) {
                return false;
            } else {
                temp = temp.children.get(word.charAt(i));
            }
        }
        return true;
    }

    private void prefixSearch(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (curr.children.containsKey(prefix.charAt(i))) {
                curr = curr.children.get(prefix.charAt(i));
            }
        }
        printWords(curr, prefix, prefix);
    }

    private void printWords(TrieNode curr, String prefix, String word) {
        if (null == curr) {
            return;
        }
        for (Map.Entry<Character, TrieNode> map : curr.children.entrySet()) {
            word = word + map.getKey();
            if (map.getValue().endOfWord) {
                System.out.print(word);
                System.out.println();
            }
            printWords(map.getValue(), prefix, word);
            word = word.substring(0, word.length() - 1);
        }
    }
}

class TrieNode {
    Map<Character, TrieNode> children;
    boolean endOfWord;

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
    }
}