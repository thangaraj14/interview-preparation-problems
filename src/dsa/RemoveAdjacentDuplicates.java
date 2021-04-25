package dsa;

/**
 * https://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
 */
public class RemoveAdjacentDuplicates {

    public static void main(String[] args) {
        System.out.println(removeDuplicatesLee("azxxzy"));
        System.out.println(removeDuplicatesLee("aaaa"));
        System.out.println(removeDuplicatesLee("abbaca"));
    }

    public static String removeDuplicatesLee(String s) {
        int i = 0;
        int n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; j++, i++) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) {
                i -= 2;
            }
        }
        return new String(res, 0, i);
    }
}
