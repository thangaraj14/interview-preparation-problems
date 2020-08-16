package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
 */
public class FirstNonReapeatingCharacterStream {
    final static int MAX_CHAR = 256;

    
    // we need to maintain 2 array of size 26 and a doubly linkedlist where the hed element points to the first Non repeat
    // 1) 1st array to store the corresponding node to the character position boolean[] visited= new boolean[26];
    // 2nd array is to check if the character occurred 2>= times DLLNode[] node= new DLLNode[26];
    // first condition whenever we see a value, we need to check it in visited array
    // if(visited[c-'a']== true) do nothing because already added and deleted from the list(means this is 3rd occurrence of the array)
    // else if(node[c-'a']!=null) removeNode(node[c-'a']); visited[c-'a']=true; 2nd occurrence we need to remove from list and set visited value to true 
    // else create a node and add head if head null or add to tail means we are seeing first time and need to create an entry in list
    
    // this uses arraylist so remove and contains are O(N)
    static void findFirstNonRepeating() {
        // inDLL[x] contains pointer to a DLL node if x is present
        // in DLL. If x is not present, then inDLL[x] is NULL
        List<Character> inDLL = new ArrayList<>();

        // repeated[x] is true if x is repeated two or more times.
        // If x is not seen so far or x is seen only once. then
        // repeated[x] is false
        boolean[] repeated = new boolean[MAX_CHAR];

        // Let us consider following stream and see the process
        String stream = "geeksforgeeksandgeeksquizfor";
        for (int i = 0; i < stream.length(); i++) {
            char x = stream.charAt(i);
            System.out.println("Reading " + x + " from stream n");

            // We process this character only if it has not occurred
            // or occurred only once. repeated[x] is true if x is
            // repeated twice or more.s
            if (!repeated[x]) {
                // If the character is not in DLL, then add this at
                // the end of DLL.
                if (!(inDLL.contains(x))) {
                    inDLL.add(x);
                } else // Otherwise remove this character from DLL
                {
                    inDLL.remove((Character) x);
                    repeated[x] = true; // Also mark it as repeated
                }
            }

            // Print the current first non-repeating character from
            // stream
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
