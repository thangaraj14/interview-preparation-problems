package dynamicProgramming;

import practiceproblems.stack.MaxHistogram;

import java.util.Arrays;

/**
 * At every row the height and area of rectangle varies, if the last row(base) is having a zero value
 * then that portion have to be avoided, this is the reason we take maxHistogram after every row
 * <p>
 * matrix
 * 0 0 0 1 0 0 0
 * 0 0 1 1 1 0 0
 * 0 1 1 1 1 1 0
 * <p>
 * height
 * 0 0 0 1 0 0 0
 * 0 0 1 2 1 0 0
 * 0 1 2 3 2 1 0
 */
public class MaximumRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int[] row = new int[matrix[0].length];
        int result = 0;
        for (char[] arr : matrix) {
            int i = 0;
            // for each cell with value=1, we look upward (north), the number of continuous '1' is the height of cell
            //First initiate the height array as 1 1 0 1 0 1, which is just a copy of the first row. Then we can easily calculate the max area is 2.
            //Then update the array. We scan the second row, when the matrix[1][i] is 0, set the height[i] to 0
            //else height[i] += 1, which means the height has increased by 1. So the height array again becomes 0 2 0 0 1 2.
            // The max area now is also 2.
            for (char c : arr) {
                if (c - '0' > 0) {
                    row[i] += c - '0';
                } else {
                    row[i] = 0;
                }

                i++;
            }
            /** The result row for every iteration
             * [1, 0, 1, 0, 0]
             * [2, 0, 2, 1, 1]
             * [3, 1, 3, 2, 2]
             * [4, 0, 0, 3, 0]
             */
            System.out.println(Arrays.toString(row));
            result = Math.max(result, MaxHistogram.largestRectangleArea(row));
        }

        return result;
    }

    public static void main(String[] args) {
        maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}});
    }

    /**
     * https://www.youtube.com/watch?v=-FgseNO-6Gk
     */
    public int maximalRectangleBruteForce(char[][] matrix) {
        int rowLength = matrix.length;
        if (rowLength == 0) return 0;
        int colLength = matrix[0].length;
        if (colLength == 0) return 0;

        int maxA = Integer.MIN_VALUE;

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                if (matrix[row][col] == '0') continue;
                // consider the rectangle whose upper left corner is at [r, c]:

                int rightMostCol = colLength - 1;// the right most column that we should check, initially it's w-1

                // iterate from i,j till row and col end
                for (int r1 = row; r1 < rowLength; r1++) {
                    for (int c1 = col; c1 <= rightMostCol; c1++) {

                        if (matrix[r1][c1] == '0') {
                            rightMostCol = c1 - 1; // update the rightMostCol when we encounter '0', no use in proceeding after
                            break; // go to next row
                        }
                        // r1 - row + 1, c1 - col + 1 are rectangle size relative to i,j where the inner loop started
                        maxA = Math.max(maxA, (r1 - row + 1) * (c1 - col + 1)); //
                    }
                }
            }
        }

        return Math.max(maxA, 0);
    }

}
