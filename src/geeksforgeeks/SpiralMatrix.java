package geeksforgeeks;

class SpiralMatrix {

    static void spiralPrint(int rowEnd, int colEnd, int a[][]) {
        int rowStart = 0;
        int colStart = 0;

        while (rowStart < colEnd && colStart < rowEnd) {
            // Print the first row from the remaining rowEnd
            for (int i = colStart; i < rowEnd; ++i) {
                System.out.print(a[rowStart][i] + " ");
            }
            rowStart++;

            // Print the last column from the remaining colEnd
            for (int i = rowStart; i < colEnd; ++i) {
                System.out.print(a[i][rowEnd - 1] + " ");
            }
            rowEnd--;

            // Print the last row from the remaining rowEnd */
            if (rowStart < colEnd) {
                for (int i = rowEnd - 1; i >= colStart; --i) {
                    System.out.print(a[colEnd - 1][i] + " ");
                }
                colEnd--;
            }

            // Print the first column from the remaining colEnd */
            if (colStart < rowEnd) {
                for (int i = colEnd - 1; i >= rowStart; --i) {
                    System.out.print(a[i][colStart] + " ");
                }
                colStart++;
            }
        }
    }

    public static void main(String[] args) {
        int R = 4;
        int C = 4;
        int a[][] = {{1, 2, 3, 4},
                     {5, 6, 7, 8},
                     {9, 10, 11, 12},
                     {13, 14, 15, 16}};
        spiralPrint(R, C, a);
    }
}