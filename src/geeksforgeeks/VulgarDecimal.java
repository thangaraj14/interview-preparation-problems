package geeksforgeeks;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 *
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 */



import java.util.*;

public class VulgarDecimal {

    public static String fractionToDecimal(long numerator, long denominator) {
        if(denominator==0) return null;
        boolean isNegative= (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0);
        
        long denomiL= Math.abs(denominator);
        long numerL= Math.abs(numerator);
        
         Map<Long,Integer> map= new HashMap<>();
        
        StringBuilder sb= new StringBuilder();
        
        sb.append((numerL/denomiL));
        
        if(numerL%denomiL!=0){
            sb.append(".");
        }
         if(isNegative) sb.insert(0,"-");
        numerL%=denomiL;
        if(numerL==0) return sb.toString();
        
        map.put(numerL,sb.length());
        
        while(numerL>0){
            
           numerL*=10;
            sb.append((numerL/denomiL));
            numerL= (numerL%denomiL);
            
            if(map.containsKey(numerL)){
                int index= map.get(numerL);
                sb.insert(index,"(");
                sb.append(")");
                break;
            }else{
            map.put(numerL,sb.length());
            }
        }
       
        return sb.toString();
    }


}
