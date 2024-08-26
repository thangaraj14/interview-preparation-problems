package practiceproblems;

/**
 * https://leetcode.com/problems/rotate-image/description/
 */
class RotateMatrixInPlace {
    /**
     * For Swapping 90  -> transpose matrix (rows to col, col to rows)            -> swap cols using 2 pointers
     * For Swapping 180 -> swap columns using 2 pointer                           -> swap rows using 2 pointers
     * For Swapping 270 -> transpose matrix (rows to col, col to rows)            -> swap rows using 2 pointers
     */
    public static void rotateAlter(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            //If we start j from 0, we would end up swapping each element twice.
            // For example, we'd swap matrix[1][2] with matrix[2][1],
            // and then later swap matrix[2][1] with matrix[1][2]
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int N = 4;

        // Test Case 1
        int[][] mat = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotateAlter(mat);
    }
}