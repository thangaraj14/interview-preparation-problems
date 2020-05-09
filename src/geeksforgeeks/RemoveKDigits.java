package geeksforgeeks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-k-digits/
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
        System.out.println(removeKDigits("14232191", 3));
    }
}