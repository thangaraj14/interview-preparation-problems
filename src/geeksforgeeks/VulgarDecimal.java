package geeksforgeeks;

/*https://leetcode.com/problems/fraction-to-recurring-decimal/*/

import java.util.*;

public class VulgarDecimal {

    public static String fractionToDecimal(long numerator, long denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();

        result.append(((numerator < 0) || (denominator < 0)) ? "-" : "");
        result.append(numerator / denominator);
        numerator %= denominator;
        if (numerator == 0) {
            return result.toString();
        }

        result.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(numerator, result.length());
        while (numerator != 0) {
            numerator *= 10;
            result.append(numerator / denominator);
            numerator %= denominator;
            if (map.containsKey(numerator)) {
                int index = map.get(numerator);
                result.insert(index, "(");
                result.append(")");
                break;
            } else {
                map.put(numerator, result.length());
            }
        }
        return result.toString();
    }


    /**
     * boolean doTestsPass()
     * Returns true if all tests pass. Otherwise false
     * <p>
     * Consider adding more tests.
     */
    public static boolean doTestsPass() {
        boolean testsPassed = true;

        // testsPassed &= fractionToDecimal(1l, 2l).equals("0.5");
        //testsPassed &= fractionToDecimal(1l, 3l).equals("0.(3)");
        //testsPassed &= fractionToDecimal(1l, 30l).equals("0.0(3)");
        //testsPassed &= fractionToDecimal(1l, 75l).equals("0.01(3)");
        //testsPassed &= fractionToDecimal(4l, 7l).equals("0.(571428)");
        testsPassed &= fractionToDecimal(1l, 56l).equals("0.017(857142)");

        if (testsPassed) {
            System.out.println("Tests passes");
        } else {
            System.out.println("Tests failed");
        }
        return testsPassed;
    }

    public static void main(String[] args) {
        doTestsPass();
    }
}
