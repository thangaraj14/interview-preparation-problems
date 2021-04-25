package dsa;

import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
//unsolved
public class MaxHistogram {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int result = 0;
        Stack<Integer> stack = new Stack();
        //i<=heights because it needs to cross the ith pos to calculate the right boundary
        for (int i = 0; i <= heights.length; ) {
            int h = (i == heights.length ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                int rightboundary = i - 1;
                int leftboundary = stack.isEmpty() ? 0 : stack.peek() + 1;
                int width = rightboundary - leftboundary + 1;
                result = Math.max(result, height * width);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(arr));
    }
}