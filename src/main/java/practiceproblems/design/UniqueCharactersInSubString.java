package practiceproblems.design;

import java.util.*;


/**
 * https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 * <p>
 * https://www.youtube.com/watch?v=JT1NDR-M_8A&t=1212s
 */
public class UniqueCharactersInSubString {

    public int uniqueLetterString(String s) {

        Map<Integer, List<Integer>> cache = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            cache.putIfAbsent(i, new ArrayList<>());
            cache.get(i).add(-1);
        }

        for (int i = 0; i < s.length(); i++) {
            cache.get(s.charAt(i) - 'A').add(i);
        }

        for (int i = 0; i < 26; i++) {
            cache.get(i).add(s.length());
        }

        int result = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < cache.get(i).size() - 1; j++) {
                result += (cache.get(i).get(j) - cache.get(i).get(j - 1)) * (cache.get(i).get(j + 1) - cache.get(i).get(j));
            }
        }

        return result;

    }

    /**
     * In the first for loop, we initialize the index array to -1 to indicate that none of the characters have occurred yet.

     * In the second for loop, we iterate over the input string "ABC". For each character,
     * we calculate the contribution of all substrings that end with that character by using the last two occurrences of that character in the index array.
     * For example, when we process "A" (at index 0), the last two occurrences of "A" are both -1,
     * so the contribution of all substrings that end with "A" is (0 - (-1)) * ((-1) - (-1)) = 1. When we process "B" (at index 1),
     * the last two occurrences of "B" are -1 and -1, so the contribution of all substrings that end with "B" is (1 - (-1)) * ((-1) - (-1)) = 0.
     * When we process "C" (at index 2), the last two occurrences of "C" are both -1, so the contribution of all substrings that end with "C" is (2 - (-1)) * ((-1) - (-1)) = 0.
     * The total contribution of all substrings is 1 + 0 + 0 = 1, so res is updated to 1.

     * In the third for loop, we calculate the contribution of all substrings that end with a character that has not occurred twice yet (i.e., all remaining characters in the index array).
     * For example, for the character "D" (with index 3), the last two occurrences of "D" are both -1, so the contribution of all substrings that end with "D" is (3 - (-1)) * ((-1) - (-1)) = 0.
     * We do this for all characters that have not occurred twice yet.

     * Finally, we return the result res, which is the sum of the contributions of all substrings that end with each character in the input string. For the input string "ABC", the result should be 10.
     */
    public int uniqueLetterStringOptimised(String s) {
        int n = s.length();     // Get the length of the input string, n = 3 for "ABC"
        int res = 0;            // Initialize the result variable, res = 0
        int[][] index = new int[26][2];  // Create a 2D array of size 26x2 to store the last two occurrences of each character
        for (int i = 0; i < 26; i++) {
            Arrays.fill(index[i], -1);  // Initialize the array to -1 to indicate that the character has not occurred yet
        }
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';  // Get the index of the current character in the index array, c = 0 for "A", c = 1 for "B", c = 2 for "C"
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % 1000000007) % 1000000007;  // Calculate the contribution of all substrings that end with the current character
            index[c][0] = index[c][1];  // Update the last occurrence of the current character to be the second last occurrence
            index[c][1] = i;            // Update the last occurrence of the current character to be the current index
        }
        for (int i = 0; i < 26; i++) {
            res = (res + (n - index[i][1]) * (index[i][1] - index[i][0]) % 1000000007) % 1000000007;  // Calculate the contribution of all substrings that end with the current character
        }
        return res;  // Return the result
    }


}
