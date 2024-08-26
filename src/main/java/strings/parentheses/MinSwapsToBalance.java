package strings.parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Idea :
 *
 *     Idea is similar to other Balance Parentheses related problems. i.e we check the balance of ( and ) brackets.
 *
 *     And whenever number of ) exceeds number of ( , we can say that it is unbalanced from start.
 *
 *     And whenever number of ( exceeds number of ) , we can say that it is unbalanced from end ( this one is trivial).
 *
 *     This can be done by incrementing when we see opening ( bracket and decrementing vice versa.
 */
public class MinSwapsToBalance {
    public static long swapCount(String s) {

        // Keep track of '['
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) == '[')
                pos.add(i);

        // To OpenBraceCount number of encountered '['
        int OpenBraceCount = 0;

        // To track position of next '[' in pos
        int nextPosOfOpen = 0;

        // To store result
        long sum = 0;

        char[] S = s.toCharArray();

        for (int i = 0; i < s.length(); ++i) {

            // Increment OpenBraceCount and move nextPosOfOpen
            // to next position
            if (S[i] == '[') {
                ++OpenBraceCount;
                ++nextPosOfOpen;
            } else if (S[i] == ']')
                --OpenBraceCount;

            // We have encountered an
            // unbalanced part of string
            if (OpenBraceCount < 0) {

                // Increment sum by number of
                // swaps required i.e. position
                // of next '[' - current position
                sum += pos.get(nextPosOfOpen) - i;
                char temp = S[i];
                S[i] = S[pos.get(nextPosOfOpen)];
                S[pos.get(nextPosOfOpen)] = temp;
                ++nextPosOfOpen;

                // Reset OpenBraceCount to 1
                OpenBraceCount = 1;
            }
        }
        return sum;
    }

    // Driver code
    public static void main(String[] args) {
        String s = "[]][][";
        System.out.println(swapCount(s));

        s = "[[][]]";
        System.out.println(swapCount(s));
    }
}
