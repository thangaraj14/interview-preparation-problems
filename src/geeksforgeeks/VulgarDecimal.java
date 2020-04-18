package geeksforgeeks;

/*https://leetcode.com/problems/fraction-to-recurring-decimal/*/

import java.util.*;

public class VulgarDecimal {

    public static String fractionToDecimal(long numerator, long denominator) {
        if(denominator==0) return null;
        boolean isNegative= (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0) ? true : false;
        
        long denomiL= Math.abs((long)denominator);
        long numerL= Math.abs((long)numerator);
        
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
