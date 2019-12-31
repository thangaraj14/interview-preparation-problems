package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 * <p>
 * https://leetcode.com/problems/rotate-image/discuss/298719/A-Java-one-pass-solution-with-detailed-explanation
 * <p>
 * //x,y1=x2,y2
 * // x1=y2
 */
class RotateMatrixInPlaceAntiClockwise {

    static void rotateMatrix(int N, int mat[][]) {

        for (int x = 0; x < N / 2; x++) {

            for (int y = x; y < N - x; y++) {

                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[y][N - x];

                // move values from bottom to right
                mat[y][N - x] = mat[N - x][N - y];

                // move values from left to bottom
                mat[N - x][N - y] = mat[N - y][x];

                // assign temp to left
                mat[N - y][x] = temp;
            }
        }
    }

    static void displayMatrix(int N, int mat[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
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