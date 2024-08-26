package practiceproblems.stack;

import java.util.*;

// https://leetcode.com/problems/number-of-atoms/
public class AtomCounts {
    public String countOfAtoms(String formula) {

        var stack = new Stack<HashMap<String, Integer>>();
        stack.push(new HashMap<>());
        int len = formula.length();
        for (int i = 0; i < formula.length();) {

            if (formula.charAt(i) == '(') {
                stack.push(new HashMap<>());
                i++;
                continue;
            }
            if (formula.charAt(i) == ')') {
                Map<String, Integer> top = stack.pop();
                i++;
                int number = 0;
                int start = i;
                while (i < len && Character.isDigit(formula.charAt(i))){
                    i++;
                }
                int multiplier = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;
                for (String key : top.keySet()) {
                    stack.peek().put(key, stack.peek().getOrDefault(key, 0) + top.get(key) * multiplier);
                }
                continue;
            }
            int start = i;
            i++;
            while (i < len && Character.isLowerCase(formula.charAt(i)))
                i++;
            String element = formula.substring(start, i);
            start = i;
            while (i < len && Character.isDigit(formula.charAt(i))) {
                i++;
            }
            int count = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;
            stack.peek().put(element, stack.peek().getOrDefault(element, 0) + count);
        }

        Map<String, Integer> result = stack.pop();
        List<String> elements = new ArrayList<>(result.keySet());
        Collections.sort(elements);
        StringBuilder sb = new StringBuilder();
        for (String element : elements) {
            sb.append(element);
            int count = result.get(element);
            if (count > 1)
                sb.append(count);
        }
        return sb.toString();
    }

}
