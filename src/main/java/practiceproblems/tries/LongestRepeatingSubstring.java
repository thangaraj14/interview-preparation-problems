package practiceproblems.tries;

/**
 * https://leetcode.com/problems/longest-repeating-substring
 * // revise tricky
 */
public class LongestRepeatingSubstring {


    public int longestRepeatingSubstring(String S) {
        char[] A = S.toCharArray();
        int res = 0;
        TrieNode root = new TrieNode();
        for (int i = 0; i < S.length(); i++) {
            TrieNode cur = root;
            for (int j = i; j < S.length(); j++) {
                if (cur.next[A[j] - 'a'] == null) {
                    TrieNode newNode = new TrieNode();
                    cur.next[A[j] - 'a'] = newNode;
                    cur = newNode;
                } else {
                    res = Math.max(res, j - i + 1);
                    cur = cur.next[A[j] - 'a'];
                }
            }
        }
        return res;
    }

    class TrieNode {
        TrieNode[] next;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }
}
