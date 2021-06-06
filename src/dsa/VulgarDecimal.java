package dsa;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal
 */
public class VulgarDecimal {

    public static String fractionToDecimal(long num, long denom) {
        if (denom == 0) {
            return null;
        }

        boolean isNegative = (num < 0 && denom > 0) || (num > 0 && denom < 0);

        long denominator = Math.abs(denom);
        long numerator = Math.abs(num);

        Map<Long, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(numerator / denominator);

        if (numerator % denominator != 0) {
            sb.append(".");
        }
        if (isNegative) {
            sb.insert(0, "-");
        }
        numerator %= denominator;
        if (numerator == 0) {
            return sb.toString();
        }

        map.put(numerator, sb.length());

        while (numerator > 0) {

            numerator *= 10;
            sb.append(numerator / denominator);
            numerator = numerator % denominator;

            if (map.containsKey(numerator)) {
                int index = map.get(numerator);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(numerator, sb.length());
            }
        }

        return sb.toString();
    }

    /**
     * boolean doTestsPass()
     * Returns true if all tests pass. Otherwise false
     * <p>
     * Consider adding more tests.
     */
    public static boolean doTestsPass() {
        boolean testsPassed = true;

        testsPassed &= fractionToDecimal(4l, 333l).equals("0.5");
        //        testsPassed &= fractionToDecimal(1l, 3l).equals("0.(3)");
        //testsPassed &= fractionToDecimal(1l, 30l).equals("0.0(3)");
        //testsPassed &= fractionToDecimal(1l, 75l).equals("0.01(3)");
        testsPassed &= fractionToDecimal(4l, 7l).equals("0.(571428)");
        // testsPassed &= fractionToDecimal(1l, 56l).equals("0.017(857142)");

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
