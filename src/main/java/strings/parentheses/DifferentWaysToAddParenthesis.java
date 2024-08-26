package strings.parentheses;

// Given a string of numbers and operators, 
// return all possible results from computing all the different possible ways to group numbers 
// and operators. The valid operators are +, - and *.

import java.util.*;

// Input: "2*3-4*5"
// Output: [-34, -14, -10, -10, 10]
// Explanation: 
// (2*(3-(4*5))) = -34 
// ((2*3)-(4*5)) = -14 
// ((2*(3-4))*5) = -10 
// (2*((3-4)*5)) = -10 
// (((2*3)-4)*5) = 10
public class DifferentWaysToAddParenthesis {
    public List<Integer> diffWaysToCompute(String input) {
        if (input == null) return Collections.emptyList();

        Map<String, List<Integer>> map = new HashMap<>();
        return bfsHelper(input, map);

    }

    public List<Integer> bfsHelper(String input, Map<String, List<Integer>> map) {

        if (map.containsKey(input)) {
            return map.get(input);
        }

        List<Integer> result = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.parseInt(input));
        } else {
            // Split input string into two parts and solve them recursively
            // for ex input = 2*3-4 output=2,-2
            // 2* | 3-4 => this right and left expression returns a list
            // 2*3| -4 => this right and left expression returns another list
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    List<Integer> firstList = bfsHelper(input.substring(0, i), map);
                    List<Integer> secondList = bfsHelper(input.substring(i + 1), map);
                    for (int first : firstList) {
                        for (int second : secondList) {
                            if (input.charAt(i) == '+') {
                                result.add(first + second);
                            } else if (input.charAt(i) == '-') {
                                result.add(first - second);
                            } else {
                                result.add(first * second);
                            }
                        }
                    }
                }
            }
        }
        map.put(input, result);
        return result;
    }
}