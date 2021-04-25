package practiceproblems;

import java.util.ArrayDeque;
import java.util.Deque;

// Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
// Output: 22
// Explanation: 
//   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
// = ((10 * (6 / (12 * -11))) + 17) + 5
// = ((10 * (6 / -132)) + 17) + 5
// = ((10 * 0) + 17) + 5
// = (0 + 17) + 5
// = 17 + 5
// = 22
public class EvaluvateRPN {
    public int reversePolishNotation(String[] tokens) {
        if(tokens==null && tokens.length==0) return 0;
        Deque<Integer> deque= new ArrayDeque<>();
        for(String i: tokens){
            if(i.length()==1 && "+*-/".contains(i)){
                Integer second= deque.removeFirst();
                Integer first= deque.removeFirst();
                //System.out.println(first +" - "+ second +" "+i);
                switch(i){
                    case "+":
                        deque.addFirst(first+second);
                        break;
                    case "-":
                        deque.addFirst(first-second);
                        break;
                    case "*":
                        deque.addFirst(first*second);
                        break;
                    case "/":
                        deque.addFirst(first/second);
                        break;
                        
                }
            }else{
                deque.addFirst(Integer.parseInt(i));
            }
        }
        
        return deque.removeFirst();
    }
}