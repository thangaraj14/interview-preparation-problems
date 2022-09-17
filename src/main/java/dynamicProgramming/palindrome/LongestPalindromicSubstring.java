package dynamicProgramming.palindrome;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        int[] maxStart = new int[1], maxEnd = new int[1];
        // odd length ababc => here we start j and k at same position say index 2 and go left and right
        // even length cbbd=> here let's say we're at index 1, we need to take 1 and 2 index to check for palindrome
        // the above cases are the reason for sending i and i+1
        for (int i = 0; i < s.length() - 1; i++) {
            extend(s, i, i, maxStart, maxEnd);
            extend(s, i, i + 1, maxStart, maxEnd);
        }

        return s.substring(maxStart[0], maxEnd[0] + 1);
    }

    private static void extend(String s, int i, int j, int[] maxStart, int[] maxEnd) {
        // loop until meet invalid match
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        i++;
        j--; // back to the last valid match

        if (j - i + 1 > maxEnd[0] - maxStart[0] + 1) {
            maxStart[0] = i;
            maxEnd[0] = j;
        }
    }


    public static void main(String args[]) {

        System.out.println(longestPalindrome("bananas"));
    }
}