package practiceproblems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/multiply-strings/
 *
 * `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
 */
public class MultiplyTwoNumbers {

    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        /**

         121 X
         23
         ------------
         363
         242
         ------

         */
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int a = (num1.charAt(i) - '0');
                int b = (num2.charAt(j) - '0');
                int multipliedVal = a * b;
                int valuePlace = i + j;
                int carryOverPlace = i + j + 1;

                // add carryover to the multiplied value
                int sum = multipliedVal + pos[carryOverPlace]; // pos[carryOverPlace] will act as reminder also

                pos[valuePlace] += sum / 10;
                pos[carryOverPlace] = (sum) % 10;
                System.out.println("a : " + a + " b: " + b + " valuePlace :" + valuePlace + " carryOverPlace " + carryOverPlace);
                System.out.println("At end of j " + j + " arr vals are  " + Arrays.toString(pos));
                System.out.println();
            }
            System.out.println();
            System.out.println("At end of i " + i + " arr vals are  " + Arrays.toString(pos));
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) sb.append(p);

        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("123", "62"));
    }

}
