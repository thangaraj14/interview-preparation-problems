package dsa;

/**
 * https://leetcode.com/problems/maximum-number-of-balloons
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

    public static void main(String[] args) {
        maxNumberOfBalloons("llonbioan");
    }
}
