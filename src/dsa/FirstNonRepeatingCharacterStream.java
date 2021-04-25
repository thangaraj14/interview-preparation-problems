package dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
 */
public class FirstNonRepeatingCharacterStream {

    static void findFirstNonRepeating() {

        List<Character> inDLL = new ArrayList<>();
        boolean[] repeated = new boolean[26];

        String stream = "geeksforgeeksandgeeksquizfor";
        for (int i = 0; i < stream.length(); i++) {
            char x = stream.charAt(i);

            if (!repeated[x - 'a']) {
                if (!inDLL.contains(x)) {
                    inDLL.add(x);
                } else {
                    inDLL.remove((Character) x);
                    repeated[x - 'a'] = true;
                }
            }
            if (!inDLL.isEmpty()) {
                System.out.println(inDLL.get(0));
            }
        }
    }

    public static void main(String[] args) {
        findFirstNonRepeating();
    }
}
