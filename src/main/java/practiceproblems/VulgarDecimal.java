package practiceproblems;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal
 * tricky
 */
public class VulgarDecimal {

    public String fractionToDecimal(int numerator, int denominator) {

        boolean isNegative = numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0;

        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        long numeratorL = Math.abs(numerator);
        long denominatorL = Math.abs(denominator);

        long rem = numeratorL / denominatorL;
        sb.append(rem);

        if (isNegative) sb.insert(0, '-');

        if (numeratorL % denominatorL > 0) {
            sb.append(".");
        } else {
            return sb.toString();
        }
        numeratorL %= denominatorL;
        map.put(numeratorL, sb.length());

        while (numeratorL > 0) {
            numeratorL *= 10;

            rem = numeratorL / denominatorL;
            sb.append(rem);
            numeratorL %= denominatorL;

            if (map.containsKey(numeratorL)) {
                int pos = map.get(numeratorL);
                sb.insert(pos, "(");
                sb.append(")");
                break;
            } else {
                map.put(numeratorL, sb.length());
            }

        }

        return sb.toString();
    }


}
