package practiceproblems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0; // end word itself not in set
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) { // going level by level
                String currentWord = queue.poll();
                char[] charArray = currentWord.toCharArray();

                for (int j = 0; j < charArray.length; j++) {
                    char temp = charArray[j]; // storing the value to reset back
                    for (char ch = 'a'; ch <= 'z'; ch++) { // try all letters in alphabets
                        if (temp == ch) {
                            continue;
                        }
                        charArray[j] = ch;
                        String newWord = String.valueOf(charArray);
                        if (set.contains(newWord)) {
                            if (newWord.equals(endWord)) { // if found return count
                                return count + 1;
                            }
                            queue.add(newWord);// else add to queue and continue
                            set.remove(newWord);// because you already reached this word, no need to see again
                        }
                    }
                    charArray[j] = temp;
                }

            }
            count++;
        }

        return 0;
    }
}