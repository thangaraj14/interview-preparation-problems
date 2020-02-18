package geeksforgeeks;

import java.util.Arrays;

class PlusOne {

    public static int[] plusOne(int[] digits) {

        StringBuilder sb = new StringBuilder();
        int remainder = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = 0;
            if (i == digits.length - 1) {
                num = digits[i] + 1;
            } else {
                num = digits[i] + remainder;
            }
            int count = String.valueOf(num).length();
            if (count > 1) {
                sb.append(num % 10);
                remainder = num / 10;
            } else {
                remainder = 0;
                sb.append(num);
            }
        }

       return sb.reverse().toString().chars().map(i -> i-'0').toArray();
    }

    public static void main(String[] args) {
        int[] digits = { 1, 2, 9 };
        int[] endpointUrl = plusOne(digits);
        Arrays.stream(endpointUrl).forEach(System.out::println);
    }
}