package geeksforgeeks;

/*https://leetcode.com/problems/fraction-to-recurring-decimal/*/

import java.util.*;

public class VulgarDecimal {

    public static String fractionToDecimal(long num, long den) {
        if (num == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();

        result.append(((num > 0) ^ (den > 0)) ? "-" : "");
        result.append(num / den);
        num %= den;
        if (num == 0) {
            return result.toString();
        }

        result.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, result.length());
        while (num != 0) {
            num *= 10;
            result.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                result.insert(index, "(");
                result.append(")");
                break;
            } else {
                map.put(num, result.length());
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
