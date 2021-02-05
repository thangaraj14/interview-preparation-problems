package geeksforgeeks;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
 */
public class RemoveAdjacentDuplicates {

    public static void main(String[] args) {
        System.out.println(removeDuplicates("azxxzy"));
        System.out.println(removeDuplicates("aaaa"));
    }

    public static String removeDuplicates(String str) {
        int ptr = -1;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (ptr == -1 || arr[i] != arr[ptr]) {
                ptr++;
                arr[ptr] = arr[i];
            } else {
                ptr--;
            }
        }
        return Arrays.toString(Arrays.copyOfRange(arr, 0, ptr + 1));
    }
}
