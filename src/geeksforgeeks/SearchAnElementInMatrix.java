package geeksforgeeks;

/*https://www.youtube.com/watch?v=FOa55B9Ikfg
* https://leetcode.com/problems/search-a-2d-matrix-ii/
https://leetcode.com/problems/search-a-2d-matrix/*/

public class SearchAnElementInMatrix {

    private static boolean searchII(int[][] mat, int n, int x) {

        int i = 0;
        int j = n - 1; // set indexes for top right element
       // we can start from  top right corner or bottom left corner, because from
       // these points only we have 2 paths one increasing one decreasing 
        while (i < n && j >= 0) {
            if (mat[i][j] == x) {
                System.out.print("n Found at " + i + " " + j);
                return true;
            }
            if (mat[i][j] > x) {
                j--;
            } else // if mat[i][j] < x
            {
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

        int[][] matI = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };

        System.out.println(searchI(matI, 11));
        System.out.println(searchII(mat, 4, 33));

    }

}
