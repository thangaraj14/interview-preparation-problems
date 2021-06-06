package dsa;

/**
 * https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 * <p>
 * https://leetcode.com/problems/rotate-image/discuss/298719/A-Java-one-pass-solution-with-detailed-explanation
 * <p>
 * //x,y1=x2,y2
 * // x1=y2
 */
class RotateMatrixInPlaceAntiClockwise {

    static void rotateMatrix(int n, int[][] mat) {

        for (int x = 0; x < n / 2; x++) {

            for (int y = x; y < n - x; y++) {

                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[y][n - x];

                // move values from bottom to right
                mat[y][n - x] = mat[n - x][n - y];

                // move values from left to bottom
                mat[n - x][n - y] = mat[n - y][x];

                // assign temp to left
                mat[n - y][x] = temp;
            }
        }
    }

    static void displayMatrix(int n, int[][] mat) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(" " + mat[i][j]);

            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int N = 4;

        // Test Case 1
        int mat[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        // int mat[][] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        // int mat[][] = { {1, 2}, {4, 5} };

        // displayMatrix(mat);

        rotateMatrix(N - 1, mat);

        // Print rotated matrix
        displayMatrix(N, mat);
    }
}