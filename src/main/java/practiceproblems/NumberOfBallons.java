package practiceproblems;

/**
 * https://leetcode.com/problems/maximum-number-of-balloons/discuss/382401/WithComments-StraightForward-Java-Simple-count-of-chars
 */
public class NumberOfBallons {

    public static int maxNumberOfBalloons(String text) {
        int[] chars = new int[26]; //count all letters
        for (char c : text.toCharArray()) {
            chars[c - 'a']++;
        }
        int min = chars[1];//for b
        min = Math.min(min, chars[0]);//for a
        min = Math.min(min, chars[11] / 2);// for l /2
        min = Math.min(min, chars[14] / 2);//similarly for o/2
        min = Math.min(min, chars[13]);//for n
        return min;
    }

    private int findMaxNumberofPattern(String text, String pattern) {
        int n = text.length(), m = pattern.length(), answer = Integer.MAX_VALUE;
        int freqInText[] = new int[26];
        int freqInPattern[] = new int[26];

        // Frequency of characters in text.
        for (int i = 0; i < n; i++) {
            freqInText[text.charAt(i) - 'a']++;
        }
        // Frequency of characters in pattern.
        for (int i = 0; i < m; i++) {
            freqInPattern[pattern.charAt(i) - 'a']++;
        }

        // Compare the maximum string that can be produced
        // considering one character at a time.
        for (int i = 0; i < 26; i++) {
            // Do not divide by zero.
            if (freqInPattern[i] > 0) {
                answer = Math.min(answer, freqInText[i] / freqInPattern[i]);
            }
        }

        return answer;
    }

    public int maxNumberOfBalloonsAnother(String text) {
        // Any string made up of lowercase English letters.
        String pattern = "balloon";
        return findMaxNumberofPattern(text, pattern);
    }

    public static void main(String[] args) {
        maxNumberOfBalloons("llonbioan");
    }
}
