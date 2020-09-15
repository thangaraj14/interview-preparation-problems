package microsoftassesment;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

//Lexicographically smallest string formed by removing at most one character.
//
//        Example 1:
//
//        Input: "abczd"
//        Output: "abcd"

public class LexicographicallySmallest {

    public String lexSmaller(String s){
        if(s==null || s.length()==0) return "";

        Deque<Character> queue= new ArrayDeque<>();
        int k=1;
        for(char c: s.toCharArray()){
           // k=1;
            while (!queue.isEmpty() && queue.getLast()>=c && k>0){
                queue.removeLast();
                k--;
            }
            queue.addLast(c);
        }

        StringBuilder sb= new StringBuilder();
        while (!queue.isEmpty()){
            sb.append(queue.removeFirst());
        }

        return sb.toString();
    }

    public String lexBuilder(String s ){
        StringBuilder str = new StringBuilder(s);
        int i=0;
        for(i=0; i<str.length()-1; i++){
            if(str.charAt(i)>str.charAt(i+1)){
                break;
            }
        }

        return str.deleteCharAt(i).toString();
    }
    public static void main(String[] args) {
        System.out.println(new LexicographicallySmallest().lexSmaller("abcde"));
    }
}
