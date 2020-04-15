package geeksforgeeks;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
 */
public class SortStack {

    public static Stack<Integer> sortStack(Stack<Integer> input) {
        /* If input is null, no processing needed */
        if (input == null) {
            return null;
        }
        /* Create a temp stack */
        Stack<Integer> tempStack = new Stack<>();
        /* Keep going until input is not empty */
        while (!input.isEmpty()) {
            /* Pop value from input */
            int tempValue = input.pop();
            /*
             * We want smallest one at the bottom. So keep comparing and if temp stack has
             * bigger item, pop it and push it to input stack
             */
            while (!tempStack.isEmpty() && tempStack.peek() > tempValue) {
                input.push(tempStack.pop());
            }
            /* Push temp value to the temp stack */
            tempStack.push(tempValue);
        }
        return tempStack;
    }

    public static void main(String[] args) {

    }
}
