package dsa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet(wordList);
        if (!set.contains(endWord)) {
            return 0; // end word itself not in set
        }
        Queue<String> queue = new LinkedList();
        queue.add(beginWord);
        int count = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) { // going level by level
                String current_word = queue.poll();
                char[] charArray = current_word.toCharArray();

                for (int j = 0; j < charArray.length; j++) {
                    char temp = charArray[j]; // storing the value to reset back
                    for (char ch = 'a'; ch <= 'z'; ch++) { // try all letters in alphabets
                        if (temp == ch) {
                            continue;
                        }
                        charArray[j] = ch;
                        String new_word = String.valueOf(charArray);
                        if (set.contains(new_word)) {
                            if (new_word.equals(endWord)) { // if found return count
                                return count + 1;
                            }
                            queue.add(new_word);// else add to queue and continue
                            set.remove(new_word);// because you already reached this word, no need to see again
                        }
                    }
                    charArray[j] = temp;
                }
            }
            count++;
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = { "hot", "dot", "dog", "lot", "log", "cog" };
        System.out.println(ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }
}
