package geeksforgeeks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-k-digits/
 */

public class RemoveKDigits {
<<<<<<< HEAD
    //1432219
    public static String removeKdigits(String num, int k) { 
        if(num==null || num.length()==0) return null;
        
        Deque<Integer> queue= new ArrayDeque<>();
        for(char c: num.toCharArray()){
            
            while(!queue.isEmpty() && queue.getLast()>c-'0' && k>0){
                queue.removeLast();
=======
    //143221999
    //121999
    public static String removeKDigits(String num, int k) {
        int len = num.length();

        if (k == len) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
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
<<<<<<< HEAD
        System.out.println(removeKdigits("143221999", 3));
=======
        System.out.println(removeKDigits("14232191", 3));
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
    }
}