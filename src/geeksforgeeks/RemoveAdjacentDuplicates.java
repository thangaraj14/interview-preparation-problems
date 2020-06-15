package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
 */
public class RemoveAdjacentDuplicates {

    public static void main(String[] args) {
//        System.out.println(removeDuplicates("azxxzy"));
        System.out.println(removeDuplicates("aaaa"));
    }

    public static String removeDuplicates(String str) {
        int stptr = -1;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (stptr == -1 || arr[i] != arr[stptr]) {
                stptr++;
                arr[stptr] = arr[i];
            } else {
                stptr--;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= stptr; i++) {
            ans.append(arr[i]);
        }
        return new String(ans);
    }
}
