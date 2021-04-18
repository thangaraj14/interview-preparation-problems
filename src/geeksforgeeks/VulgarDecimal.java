package geeksforgeeks;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * <p>
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * If multiple answers are possible, return any of them.
 * <p>
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 */


import java.util.*;

public class VulgarDecimal {

    public static String fractionToDecimal(long numerator, long denominator) {
        if (denominator == 0) return null;
        // if both are negative then the ans would be positive
        boolean isNegative = (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0);

        long denomiL = Math.abs(denominator);
        long numerL = Math.abs(numerator);

        Map<Long, Integer> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append((numerL / denomiL));

        if (numerL % denomiL != 0) {
            sb.append(".");
        }
        if (isNegative) sb.insert(0, "-");

        numerL %= denomiL;

        if (numerL == 0) return sb.toString();

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

    public String fractionToDecimal(int numerator, int denominator) {

        boolean isNegative= numerator<0 && denominator>0 || numerator>0 && denominator<0;

        StringBuilder sb= new StringBuilder();
        Map<Long,Integer> map= new HashMap<>();
        long numeratorL= Math.abs(Long.valueOf(numerator));
        long denominatorL= Math.abs(Long.valueOf(denominator));

        long rem=numeratorL/denominatorL;
        sb.append(rem);



        if(isNegative) sb.insert(0,'-');

        if(numeratorL%denominatorL>0){
            sb.append(".");
        }else{
            return sb.toString();
        }
        numeratorL%=denominatorL;
        map.put(numeratorL,sb.length());

        while(numeratorL>0){
            numeratorL*=10;

            rem=numeratorL/denominatorL;
            sb.append(rem);
            numeratorL%=denominatorL;
            if(map.containsKey(numeratorL)){
                int pos= map.get(numeratorL);
                sb.insert(pos,"(");
                sb.append(")");
                break;
            }else{
                map.put(numeratorL,sb.length());
            }

        }

        return sb.toString();
    }


}
