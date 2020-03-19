package geeksforgeeks;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/check-if-two-given-strings-are-isomorphic-to-each-other/
 */
class IsomorphicString {
    static boolean isIsomorphic(String s, String t) {
        int m1[] = new int[256];
        int m2[] = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            // it checks the count of the character in the array ;
            // for 'g' -> a[103] is 2 and 'd' -> a[100] is 2
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                return false;
            }
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        System.out.println(Arrays.toString(m1));
        System.out.println(Arrays.toString(m2));
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
    }
};