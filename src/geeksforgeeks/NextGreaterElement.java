package geeksforgeeks;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/next-greater-element/
 * <p>
 * https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
 */
class NextGreaterElement {

    static int arr[] = {4, 5, 2, 25};

    // 9,1,2,3,4,5,6,7
    public static void printNGE() {
        Stack<Integer> s = new Stack<>();
        int[] nge = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {

            while (!s.empty() && s.peek() <= arr[i]) {
                s.pop();
            }
            nge[i] = s.empty() ? -1 : s.peek();
            s.push(arr[i]);

        }
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i] + " --> " + nge[i]);

    }

    public static void main(String[] args) {
        printNGE();
    }
}
