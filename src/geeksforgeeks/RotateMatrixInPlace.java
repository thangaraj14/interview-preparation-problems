package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 */
// looking for easy solution
class RotateMatrixInPlace {

    /**
     * N/2 for time complexity o(n)
     * <p>
     * get all the corners first and swap ( rotate 90 degree)
     *
     * @param N
     * @param mat
     */
    static void rotateMatrix(int N, int mat[][]) {
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++) {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y < N - x - 1; y++) {
                // store current cell in temp variable
                System.out.println("mat[" + x + "][" + y + "] = " + mat[x][y]);
                int temp = mat[x][y];

                // move values from right to top
                System.out.println("mat[" + y + "][" + (N - 1 - x) + "] = " + mat[y][N - 1 - x]);
                mat[x][y] = mat[y][N - 1 - x];

                // move values from bottom to right
                System.out.println("mat[" + (N - 1 - x) + "][" + (N - 1 - y) + "] = " + mat[N - 1 - x][N - 1 - y]);
                mat[y][N - 1 - x] = mat[N - 1 - x][N - 1 - y];

                // move values from left to bottom
                System.out.println("mat[" + (N - 1 - y) + "][" + (x) + "] = " + mat[N - 1 - y][x]);
                mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];

                // assign temp to left
                System.out.println("mat[" + (N - 1 - y) + "][" + (x) + "] = " + temp);
                mat[N - 1 - y][x] = temp;
            }
        }
    }

    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)return;
        int n= matrix.length-1;
        
        for(int i=0; i<matrix.length/2;i++){
            // to check number of cycles
            for(int j=i; j<n-i;j++){
                // loop to find number of elements 
                
                //x1,y1= x2,y2
                //x1=y2
                
                int temp=matrix[i][j];
                
                //0,0= 3,0
                matrix[i][j]=matrix[n-j][i];
                
               // 3,0=3,3
                matrix[n-j][i]= matrix[n-i][n-j];
                
                //3,3=0,3
                matrix[n-i][n-j]= matrix[j][n-i];
                
                matrix[j][n-i]=temp;
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
        int mat[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        // int mat[][] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        // int mat[][] = { {1, 2}, {4, 5} };

        // displayMatrix(mat);

        rotateMatrix(N, mat);

        // Print rotated matrix
        displayMatrix(N, mat);
    }
}