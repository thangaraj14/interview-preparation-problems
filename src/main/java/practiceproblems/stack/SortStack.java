package practiceproblems.stack;

import java.util.Stack;

public class SortStack {
    // Input : [34, 3, 31, 98, 92, 23]
    // Output : [3, 23, 31, 34, 92, 98]
    public static void stackSorting(Stack<Integer> stack) {
        Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            int item = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() > item) {
                stack.push(tempStack.pop());
            }
            tempStack.push(item);
        }
        while (!tempStack.isEmpty()) stack.push(tempStack.pop());
    }
}