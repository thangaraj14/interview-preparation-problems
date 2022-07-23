package practiceproblems;

/**
 * https://leetcode.com/discuss/interview-question/351783/
 * https://www.youtube.com/watch?v=zXpYs8j5oI8&ab_channel=Insidecode
 *
 * TODO
 */
public class MinAdjSwapsToMakePalindrome {

    private int getNoOfSwaps(String s) {
        if (s == null || s.length() == 0) return -1;
        int totalSwaps = 0;

        if (isShuffledPalindrome(s)) {
            char[] chars = s.toCharArray();
            int left = 0, right = chars.length - 1;

            while (right > left) {
                if (chars[left] != chars[right]) {
                    int searcher = right;
                    while (searcher > left && chars[searcher] != chars[left]) searcher--;

                    if (searcher == left) { //When no matching character found
                        swap(chars, left, left + 1);
                        totalSwaps++;

                    } else { //When Matching character found swap until K reaches p2 position
                        while (searcher < right) {
                            swap(chars, searcher, searcher + 1);
                            totalSwaps++;
                            searcher++;
                        }
                        left++;
                        right--;
                    }
                } else {
                    left++;
                    right--; //When the characters are equal move on
                }
            }
            return totalSwaps;
        } else return -1;
    }

    private static void swap(char[] chars, int k, int i) {
        char temp = chars[k];
        chars[k] = chars[i];
        chars[i] = temp;
    }

    private boolean isShuffledPalindrome(String s) {
        int[] occurrence = new int[26];
        int oddCount = 0;

        for (int i = 0; i < s.length(); i++) occurrence[s.charAt(i) - 'a']++;
        for (int value : occurrence) if (value % 2 != 0) oddCount++;
        return oddCount <= 1;
    }
}
