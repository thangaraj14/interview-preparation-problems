package geeksforgeeks;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
 */
public class SortStack {

    public static Stack<Integer> sortStack(Stack<Integer> input) {
        if (input == null) {
            return null;
        }
        Stack<Integer> tempStack = new Stack<>();
        while (!input.isEmpty()) {
            int tempValue = input.pop();
            while (!tempStack.isEmpty() && tempStack.peek() < tempValue) {
                input.push(tempStack.pop());
            }
            tempStack.push(tempValue);
        }
        return tempStack;
    }

    public static void main(String[] args) {
        Stack<Integer> input = new Stack<>();
        input.add(23);
        input.add(92);
        input.add(98);
        input.add(31);
        input.add(3);
        input.add(34);

        // This is the temporary stack
        Stack<Integer> tmpStack = sortStack(input);
        System.out.println("Sorted numbers are:");

        while (!tmpStack.empty()) {
            System.out.print(tmpStack.pop() + " ");
        }
    }
}
