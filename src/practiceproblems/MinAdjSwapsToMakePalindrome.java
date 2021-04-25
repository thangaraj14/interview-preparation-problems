package practiceproblems;

/**
 * https://leetcode.com/discuss/interview-question/351783/
 * https://www.youtube.com/watch?v=zXpYs8j5oI8&ab_channel=Insidecode
 */
public class MinAdjSwapsToMakePalindrome {

    private int getNoOfSwaps(String s) {
        if (s == null || s.length() == 0) return -1;
        int totalSwaps = 0;

        if (isShuffledPalindrome(s)) {
            char[] chars = s.toCharArray();
            int p1 = 0, p2 = chars.length - 1;

            while (p2 > p1) {
                if (chars[p1] != chars[p2]) {
                    int k = p2;
                    while (k > p1 && chars[k] != chars[p1]) k--;

                    if (k == p1) { //When no matching character found
                        swap(chars, p1, p1 + 1);
                        totalSwaps++;

                    } else { //When Matching character found swap until K reaches p2 position
                        while (k < p2) {
                            swap(chars, k, k + 1);
                            totalSwaps++;
                            k++;
                        }
                        p1++;
                        p2--;
                    }
                } else {
                    p1++;
                    p2--; //When the characters are equal move on
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
