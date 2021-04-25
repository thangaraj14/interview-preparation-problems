package dsa;

/**
 * https://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
 */
public class MatrixRowWithMax1 {

    public static void main(String[] args) {
        int[][] mat = { { 0, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };
        System.out.println(rowWithMax1s(mat));
    }

    static int rowWithMax1s(int[][] mat) {

        int R = mat.length;
        int C = mat[0].length;
        int max_row_index = 0;

        int j = findFirstIndex(mat[0], 0, C - 1);
        if (j == -1) {
            j = C - 1;
        }

        for (int i = 1; i < R; i++) {
            while (j >= 0 && mat[i][j] == 1) {
                j = j - 1;
                max_row_index = i;
            }
        }
        return max_row_index;
    }

    static int findFirstIndex(int[] arr, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if ((mid == 0 || (arr[mid - 1] == 0)) && arr[mid] == 1) {
                return mid;
            } else if (arr[mid] == 0) {
                return findFirstIndex(arr, (mid + 1), high);
            } else {
                return findFirstIndex(arr, low, (mid - 1));
            }
        }
        return -1;
    }

}
