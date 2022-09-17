package practiceproblems;

import java.util.Arrays;

/**
 * The idea is that we need to map a char to another one, for example, "egg" and "add",
 * we need to constract the mapping 'e' -> 'a' and 'g' -> 'd'.
 * Instead of directly mapping 'e' to 'a', another way is to mark them with same value,
 * for example, 'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2, this works same.
 * So we use two arrays here m1 and m2, initialized space is 256 (Since the whole ASCII size is 256, 128 also works here).
 * Traverse the character of both s and t on the same position,
 * if their mapping values in m1 and m2 are different, means they are not mapping correctly,
 * return false; else we construct the mapping, since m1 and m2 are both initialized as 0,
 * we want to use a new value when i == 0, so i + 1 works here.
 */
class IsomorphicString {
    static boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        Arrays.fill(map1, -1);
        Arrays.fill(map2, -1);

        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            // first time to see the two characters
            if (map1[c1] == -1 && map2[c2] == -1) {
                map1[s.charAt(i)] = i;
                map2[t.charAt(i)] = i;
                // one of them is seen before or two are mapping to different
            } else if (map1[c1] != map2[c2]) {
                return false;
            }

        }
        return true;
    }

    public static boolean isIsomorphicEff(String s, String t) {
        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }

            // Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both
            else if (!(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphicEff("abacb", "xyxzy"));
        System.out.println(isIsomorphicEff("paper", "title"));
    }
}