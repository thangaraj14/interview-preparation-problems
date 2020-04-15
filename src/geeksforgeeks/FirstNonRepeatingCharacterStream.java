package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
 */
public class FirstNonRepeatingCharacterStream {

    final static int MAX_CHAR = 256;

    static void findFirstNonRepeating() {

        List<Character> inDLL = new ArrayList<>();
        boolean[] repeated = new boolean[MAX_CHAR];

        String stream = "geeksforgeeksandgeeksquizfor";
        for (int i = 0; i < stream.length(); i++) {
            char x = stream.charAt(i);
            System.out.println("Reading " + x + " from stream n");

            if (!repeated[x]) {
                if (!inDLL.contains(x)) {
                    inDLL.add(x);
                } else {
                    inDLL.remove((Character) x);
                    repeated[x] = true;
                }
            }
            if (inDLL.size() != 0) {
                System.out.print("First non-repeating character so far is ");
                System.out.println(inDLL.get(0));
            }
        }
    }

    public static void main(String[] args) {
        findFirstNonRepeating();
    }
}
