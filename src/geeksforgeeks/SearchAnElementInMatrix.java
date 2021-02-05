package geeksforgeeks;

/**
 * https://www.youtube.com/watch?v=FOa55B9Ikfg
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * https://leetcode.com/problems/search-a-2d-matrix/
 */

public class SearchAnElementInMatrix {

    private static boolean searchII(int[][] mat, int n, int x) {

        int i = 0;
        int j = n - 1;

        while (i < n && j >= 0) {
            if (mat[i][j] == x) {
                System.out.print("n Found at " + i + " " + j);
                return true;
            }
            if (mat[i][j] > x) {
                j--;
            } else {
                i++;
            }
        }

        System.out.print("n Element not found");
        return false;

    }

    // why column : no.of.columns define the row correctly and rows wont help us to find the exact row
    public static boolean searchI(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int end = rows * cols - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            //Another way to took at it is: lets say you have a matrix M with 4 rows and 3 columns. When we want to access M[2][1], the way the memory address is calculated is 2*3+1 = 7. so you are just reversing the calculation , row number is given by 7/3 = 2, and column is the offset in that row so for 7th element it is 7%3 = 1. This link helped me understand it.
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            }
            if (matrix[mid / cols][mid % cols] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };

        int[][] matI = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 } };

        System.out.println(searchI(mat, 11));
        System.out.println(searchII(matI, 5, 8));

    }

}
