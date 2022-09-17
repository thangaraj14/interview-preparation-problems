package practiceproblems;

/**
 * https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 */
class RotateMatrixInPlace {

    /**
     * N/2 for time complexity o(n)
     * get all the corners first and swap ( rotate 90 degree)
     */

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int n = matrix.length - 1;

        for (int i = 0; i < matrix.length / 2; i++) {
            // to check number of cycles
            for (int j = i; j < n - i; j++) {
                // loop to find number of elements 

                //x1,y1= x2,y2
                //x1=y2

                int temp = matrix[i][j];

                //0,0= 3,0
                matrix[i][j] = matrix[n - j][i];

                // 3,0=3,3
                matrix[n - j][i] = matrix[n - i][n - j];

                //3,3=0,3
                matrix[n - i][n - j] = matrix[j][n - i];

                matrix[j][n - i] = temp;
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

    /**
     * For Swapping 90  -> transpose matrix (rows to col, col to rows)            -> swap cols using 2 pointers
     * For Swapping 180 -> swap columns using 2 pointer                           -> swap rows using 2 pointers
     * For Swapping 270 -> transpose matrix (rows to col, col to rows)            -> swap rows using 2 pointers
     */
    public static void rotateAlter(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int N = 4;

        // Test Case 1
        int[][] mat = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        displayMatrix(N, mat);

        rotateAlter(mat);

        // Print rotated matrix
        displayMatrix(N, mat);
    }
}