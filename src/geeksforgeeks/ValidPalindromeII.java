package geeksforgeeks;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 */
class ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }

        if (i >= j) {
            return true;
        }

        if (isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1)) {
            return true;
        }
        return false;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindrome("ddeeeef"));
    }
}