package cess;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=QAcH8qD9Pe0
 * https://cses.fi/problemset/task/2431
 *
 * too complex, debug to understand
 */
public class DigitQueries {

    public static String solve(long[] arr) {
        long[] powerOfTen = new long[19];
        Arrays.fill(powerOfTen, 1);

        for (int i = 1; i < powerOfTen.length; i++) {
            powerOfTen[i] = powerOfTen[i - 1] * 10;

        }

        for (long index : arr) {

            long digitsSoFar = 0;
            long digitsBeforeActualBlock = 0;
            int numberOfDigits = 0;

            for (int j = 1; j <= 18; j++) {
                long distance = (powerOfTen[j] * powerOfTen[j - 1]) * j;

                digitsSoFar += distance;

                if (digitsSoFar >= index) {
                    numberOfDigits = j;
                    break;
                }
                digitsBeforeActualBlock += distance;
            }

            long smallestValue = powerOfTen[numberOfDigits - 1];
            long largestValue = powerOfTen[numberOfDigits] - 1;
            long bestValue = 0;
            long bestValueStartPos = 0;

            while (smallestValue <= largestValue) {
                long mid = (smallestValue + largestValue) / 2;
                long startPostOfActual = digitsBeforeActualBlock + 1 + (mid - powerOfTen[numberOfDigits - 1]) * numberOfDigits;
                if (startPostOfActual <= index) {
                    if (mid > bestValue) {
                        bestValue = mid;
                        bestValueStartPos = startPostOfActual;
                    }
                    smallestValue = mid + 1;
                } else {
                    largestValue = mid - 1;
                }
            }
            int res = (int) ((int) index - bestValueStartPos);
            return String.valueOf(String.valueOf(bestValue).charAt(res));
        }
        return "";
    }

    public static void main(String[] args) {
        solve(new long[]{107});
    }
}
