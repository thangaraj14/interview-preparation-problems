package practiceproblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://www.interviewbit.com/problems/first-non-repeating-character-in-a-stream-of-characters/
 */
public class FirstNonRepeatingCharacterStream {
    Set<String> isSeen = new HashSet<>();
    DLList linkedList = new DLList();
    Map<String, Node> map = new HashMap<>();

    public String solve(String A) {
        StringBuilder sb = new StringBuilder();
        for (String s : A.split("")) {
            sb.append(uniqueStream(s));
        }
        return sb.toString();
    }

    public String uniqueStream(String A) {
        Node node = new Node(A);

        if (isSeen.contains(A) && map.get(A) != null) {
            Node temp = map.get(A);
            linkedList.remove(temp);
            map.put(A, null);
        } else if (!isSeen.contains(A)) {
            isSeen.add(A);
            linkedList.add(node);
            map.put(A, node);
        }

        return linkedList.head.next.val;
    }

    /**
     * tricky inner loop
     */
    public String solveEfficient(String A) {
        // we can use two pointer approach to solve this problem.
        // First count the occurences of the characters in the stream in the charCounts array
        int[] charCounts = new int[26];
        // Keep a start pointer at the start index which will denote the first non-repeating character, if any
        int j = 0;
        char[] charArr = A.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArr.length; i++) {
            // increment the count of the current character
            charCounts[charArr[i] - 'a']++;
            // move the start pointer ahead either till the current index
            // or until we see another non-repeating character
            while (j < i && charCounts[charArr[j] - 'a'] > 1) j++;
            // If the character at start index is a non repeating character, add it the string otherwise add '#''
            if (charCounts[charArr[j] - 'a'] == 1) sb.append(charArr[j]);
            else sb.append('#');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String vals = "jpxvxivxkkthvpqhhhjuzhkegnzqriokhsgea";
        FirstNonRepeatingCharacterStream solution = new FirstNonRepeatingCharacterStream();
        System.out.println("ans:: " + solution.solveEfficient(vals));
    }

    class DLList {
        Node head;
        Node tail;

        public DLList() {
            head = new Node("#");
            tail = new Node("#");
            head.next = tail;
            tail.prev = head;
        }

        public void add(Node node) {
            Node prev = tail.prev;
            prev.next = node;
            node.prev = prev;

            node.next = tail;
            tail.prev = node;
        }

        public void remove(Node temp) {
            Node next = temp.next;
            temp.prev.next = next;
            next.prev = temp.prev;
        }
    }

    class Node {
        Node prev;
        Node next;
        String val;

        public Node(String val) {
            this.val = val;
        }
    }
}



