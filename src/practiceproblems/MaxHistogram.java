package practiceproblems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */

public class MaxHistogram {

    //For any bar i the maximum rectangle is of width r - l - 1
    // where r - is the last coordinate of the bar to the right with height h[r] >= h[i] and
    // l - is the last coordinate of the bar to the left which height h[l] >= h[i]
    //So if for any i coordinate we know his utmost higher (or of the same height) neighbors to the right and to the left,
    // we can easily find the largest rectangle:  maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));

    //The main trick is how to effectively calculate lessFromRight and lessFromLeft arrays.
    // The trivial solution is to use O(n^2) solution and for each i element
    // first find his left/right neighbour in the second inner loop just iterating back or forward:
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

       // for example in order to lessFromLeft[i]; if height[i - 1] < height[i] then left[i] = i - 1;
        // other wise we do not need to start scan from i - 1; we can start the scan from lessFromLeft[i - 1],
        // because since lessFromLeft[i - 1] is the first position to the left of i - 1 that have height less than height[i - 1],
        // and we know height[i - 1] >= height[i]; so lessFromLeft[i] must be at the left or at lessFromLeft[i - 1]; similar for the right array;
        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        // after both the loop ends, this is the output of left and right
        // input [2, 1, 5,  6, 2, 3]
        //        0, 1, ,2  3, 4, 5  
        // left  [-1, -1, 1, 2, 1, 4] => indexes of elements
        // right [1, 6, 4, 4, 6, 6]
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        System.out.println(Arrays.toString(lessFromLeft));
        System.out.println(Arrays.toString(lessFromRight));
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(arr));
    }
}