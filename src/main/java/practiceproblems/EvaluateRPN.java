package practiceproblems;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation
 */
public class EvaluateRPN {
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> s = new ArrayDeque<>();

        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int second = s.pop();
                int first = s.pop();
                switch (token) {
                    case "+":
                        s.push(second + first);
                        break;
                    case "-":
                        s.push(first - second);
                        break;
                    case "*":
                        s.push(first * second);
                        break;
                    default:
                        s.push(first / second);
                        break;
                }

            } else {
                s.push(Integer.parseInt(token));
            }

        }

        return s.pop();


    }
}