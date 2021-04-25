package dsa;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/basic-calculator/
 */
// Input: "(1+(4+5+2)-3)+(6+8)"
// Output: 23
public class BasicCalculator {

    public static int calculate(String s) {

        if (s == null || s.length() == 0) {
            return -1;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int sign = 1;
        int number = 0;
        int result = 0;

        // let's take an edge case 2-(5-6)=3;
        // at i=0 number=2
        // i=1 char ='-' update with prev seen sign res=sign*number reset number we are looking for next operand
        //i=2 char='(' and sign is '-', push prev result and sign and reset result for calculating
        // sub-problem inside braces
        // i=3 update number to 5
        // i=4 char ='-' update result as sign*number = 5 reset number and sign =-1
        //i=5  update number to 6
        // i=6  char=')' update result with existing number(res=5=> 5+(-1*6)) and sign inside the braces
        // then pop, which is last seen sign outside brace=> -1 and pop again to get result outside brace
        // add all to result;

        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (Character.isDigit(temp)) {
                number = number * 10 + (temp - '0');
            } else if (temp == '+') {
                result += sign * number; // to do operations like (-4-5)
                number = 0;
                sign = 1;
            } else if (temp == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (temp == '(') {
                deque.addLast(result);
                deque.addLast(sign);

                result = 0;
                sign = 1;
            } else if (temp == ')') {
                result += sign * number;
                number = 0;
                result *= deque.removeLast();
                result += deque.removeLast();
            }
        }

        if (number != 0) {
            result += sign * number;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}