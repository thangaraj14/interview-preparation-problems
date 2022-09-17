package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/remove-k-digits/
 *
 * Given a non-negative integer num represented as a string,
 * remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */

public class RemoveKDigits {
    //1432219
    public static String removeKdigits(String num, int k) { 
        if(num==null || num.length()==0) return null;
        
        Deque<Integer> queue= new ArrayDeque<>();
        for(char c: num.toCharArray()){
            
            while(!queue.isEmpty() && queue.getLast()>c-'0' && k>0){
                queue.removeLast();
                k--;
            }
            queue.addLast(c-'0');       
        }
         
        while(k>0){
            queue.removeLast();
            k--;
        }
        
        StringBuilder result= new StringBuilder();
        while(!queue.isEmpty()){
            result.append(queue.removeFirst());
        }
       
        while(result.length()>0 && result.charAt(0)=='0'){
            result.deleteCharAt(0);
        }
        
        return result.length()==0?"0":result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("14232191", 3));
    }
}