package dsa;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 */
class ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }

        if (left >= right) {
            return true;
        }

        if (isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)) {
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
        System.out.println(validPalindrome("deedf"));
    }
}