package geeksforgeeks;

/*https://leetcode.com/problems/fraction-to-recurring-decimal/*/

import java.util.HashMap;
import java.util.Map;

public class VulgarDecimal {

    public static String fractionToDecimal(long numerator, long denominator) {
        if (denominator == 0) {
            return null;
        }
        boolean isNegative = (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0) ? true : false;

        long denomiL = Math.abs(denominator);
        long numerL = Math.abs(numerator);

        Map<Long, Integer> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append(numerL / denomiL);

        if (numerL % denomiL != 0) {
            sb.append(".");
        }
        if (isNegative) {
            sb.insert(0, "-");
        }
        numerL %= denomiL;
        if (numerL == 0) {
            return sb.toString();
        }

        map.put(numerL, sb.length());

        while (numerL > 0) {

            numerL *= 10;
            sb.append((numerL / denomiL));
            numerL = (numerL % denomiL);

            if (map.containsKey(numerL)) {
                int index = map.get(numerL);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(numerL, sb.length());
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

        // testsPassed &= fractionToDecimal(1l, 2l).equals("0.5");
        //testsPassed &= fractionToDecimal(1l, 3l).equals("0.(3)");
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
