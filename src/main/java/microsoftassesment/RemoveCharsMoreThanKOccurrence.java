package microsoftassesment;


// given eedaaad  and k=3 return eedaad
public class RemoveCharsMoreThanKOccurrence {

    public static String removeKConsecutiveChars(String s, int k) {
        if (s == null || s.length() == 0) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int cnt = 1;
        for (int r = 1; r < s.length(); r++) {
            char c = s.charAt(r);
            if (c == s.charAt(r - 1))
                cnt++;
            else {
                cnt = 1;
            }
            if (cnt < k)
                sb.append(c);
        }
        return sb.toString();


    }

    public static void main(String[] args) {
        String[] inputs = {"eedaaad", "xxxtxxxz", "uuuuxaaaaxuuu"};
        for (String i : inputs) {
            System.out.println(removeKConsecutiveChars(i, 3));
        }

        System.out.println(removeDuplicates("aaabcd"));
    }

    public static String removeDuplicates(String A) {
        int stptr = -1;
        char[] arr = A.toCharArray();
        for (int i = 0; i < A.length(); i++) {
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
