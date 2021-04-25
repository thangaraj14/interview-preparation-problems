package practiceproblems;

import java.util.stream.IntStream;

/**
 * Write a method to replace all the spaces in a string with ‘%20’.
 * You may assume that the string has sufficient space at the end to hold the additional characters,
 * and that you are given the “true” length of the string.
 */
public class UrlEncode {
    public void replaceSpaces(char[] str, int trueLength) {
        int i = str.length - 1;
        int extra = str.length - trueLength;
        int j = i - extra;
        while (i != j) {
            if (!Character.isSpaceChar(str[j])) {
                str[i--] = str[j--];
            } else {
                str[i--] = '0';
                str[i--] = '2';
                str[i--] = '%';
                j--;
            }
        }
    }

    public static void main(String[] args) {
        test(new char[17], "Mr John Smith");
        test(new char[12], "Mr John ");
        test(new char[5], "Mr ");
        test(new char[5], " Mr");
        test(new char[8], " Mr ");
        test(new char[3], " ");
        test(new char[20], "Mr  John Smith");
        test(new char[0], "");
    }

    private static void test(char[] str, String s) {
        IntStream.range(0, s.length()).forEach(i -> str[i] = s.charAt(i));
        System.out.print(new String(str) +" - ");
        UrlEncode ob = new UrlEncode();
        ob.replaceSpaces(str, s.length());
        System.out.println(new String(str));
    }
}