package practiceproblems.prefixsum;


import java.util.HashMap;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=9SS83X6Na6k
 * <p>
 * Microsoft interview
 * <p>
 * There are two strings, A and B, each of length N.
 * A fragment of string A Corresponds with a fragment of string B if: both fragments start at the same position;
 * letters from one fragment can be rearranged into the order of letters in the other fragment
 * (note that the case and number of occurrences of the letter matters).
 * <p>
 * Given A "dBacaAA" and B = "caBdaaA", the function should return 5. The corresponding fragments are:
 * "dBaca" and "caBda" (starting at position 0)
 * "dBac" and "caBd"(starting at position 0)
 * ."Ba" and "aB" (starting at position 1)
 * ."a" and "a" (starting at position 4)
 * . "A" and "A" (at position 6).
 * <p>
 * Given A = "zzzX" and B "zzzX",the function should return 10. total substrings = (n*(n+1))/2
 * All fragments starting at the same positions in both strings correspond.
 */
public class SubstringEqualFragments {

    public static int countFragmentsOptimised(String A, String B) {
        int count = 0;
        int strLength = A.length();
        Map<Character, Integer> letters = new HashMap<>();


        int noOfCharacterToBeChecked = 1;
        int startIndex = 0;
        while (noOfCharacterToBeChecked <= strLength) {

            int lastIndex = startIndex + noOfCharacterToBeChecked;
            //First get the characters in HashMap for the noOfLtters to be checked
            for (int i = startIndex; i < lastIndex; i++) {
                letters.put(A.charAt(i), letters.getOrDefault(A.charAt(i), 0) + 1);
            }
            //compare the second string and increase count if matched
            for (int i = startIndex; i < lastIndex; i++) {
                if (letters.containsKey(B.charAt(i))) {
                    int letterCount = letters.getOrDefault(B.charAt(i), 0);
                    if (letterCount > 1) {
                        letters.put(B.charAt(i), letters.getOrDefault(B.charAt(i), 0) - 1);
                    } else {
                        letters.remove(B.charAt(i));
                    }
                } else {
                    break;
                }
            }

            if (letters.isEmpty()) {
                count++;
            }
            letters.clear();
            //Check till last index
            if (lastIndex < strLength) {
                startIndex++;
            } else {
                //Once last index is reached increase noOfLetter to be checked and reset startIndex
                noOfCharacterToBeChecked++;
                startIndex = 0;
            }

        }
        return count;
    }


    public static int countFragmentsBruteForce(String a, String b) {
        int[] cache1;
        int[] cache2;
        int ans = 0;
        for (int i = 0; i < a.length(); i++) {
            cache1 = new int[52];
            cache2 = new int[52];
            int totalCountOfParity = 52; // at first all the values in cache has same value i.e 0
            for (int j = i; j < a.length(); j++) {
                int c1 = mapToInt(a.charAt(j));
                int c2 = mapToInt(b.charAt(j));

                if (cache1[c1] == cache2[c1]) totalCountOfParity--;

                cache1[c1]++;

                if (cache1[c1] == cache2[c1]) totalCountOfParity++;

                if (cache1[c2] == cache2[c2]) totalCountOfParity--;

                cache2[c2]++;

                if (cache1[c2] == cache2[c2]) totalCountOfParity++;

                if (totalCountOfParity == 52) ans++;
            }
        }
        return ans;
    }

    public static int mapToInt(char ch) {
        if (ch >= 'a' && ch <= 'z') return (ch - 'a');
        if (ch >= 'A' && ch <= 'Z') return (ch - 'A' + 26);
        return -1;
    }

    public static void main(String[] args) {
        String s1 = "dBacaAA";
        String s2 = "caBdaaA";
        System.out.println(countFragmentsOptimised(s1, s2));
    }

}
