package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * tricky matrix
 */
class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();

        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                System.out.println(" Right " + matrix[rowBegin][j]);
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                System.out.println(" Down " + matrix[j][colEnd]);
                res.add(matrix[j][colEnd]);
            }
            colEnd--;

            // Make sure we are now on a different row.
            if (rowBegin <= rowEnd) { // without this condition, this corner test case [[2,3]] would print [2,3,2]
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--) {
                    System.out.println(" Left " + matrix[rowEnd][j]);
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            //  It's necessary because after we've traversed right, down, and left,
            //  we might have exhausted all columns.

            if (colBegin <= colEnd) {
                // Travel Up
                // this block's work is to lift 1 row up from bottom
                //  the rest of the work will be done by first 'Right' loop again
                for (int j = rowEnd; j >= rowBegin; j--) {
                    System.out.println(" uppp " + matrix[j][colBegin]);
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin++;
        }

        return res;
    }

    public static void main(String[] args) {

//        int a[][] = {{1, 2, 3, 4},
//                     {5, 6, 7, 8},
//                     {9, 10, 11, 12},
//                     {13, 14, 15, 16}};
//                     spiralOrder(a);

        SpiralMatrix sm = new SpiralMatrix();
        sm.spiralMatrixIII(5, 6, 1, 4);
    }

    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];

        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        int num = 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                matrix[rowBegin][j] = num++;
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                matrix[j][colEnd] = num++;
            }
            colEnd--;

            // Make sure we are now on a different row.
            if (rowBegin <= rowEnd) { // without this condition, this corner test case [[2,3]] would print [2,3,2]
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--) {
                    matrix[rowEnd][j] = num++;
                }
            }
            rowEnd--;

            //  It's necessary because after we've traversed right, down, and left,
            //  we might have exhausted all columns.

            if (colBegin <= colEnd) {
                // Travel Up
                // this block's work is to lift 1 row up from bottom
                //  the rest of the work will be done by first 'Right' loop again
                for (int j = rowEnd; j >= rowBegin; j--) {
                    matrix[j][colBegin] = num++;
                }
            }
            colBegin++;
        }

        return matrix;
    }

    //The idea here is that once we start at (r=r0, c=c0),
    // we walk along the east, then south, then west, and then north.
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] dirt = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // east, south, west, north is 0, 1, 2,
        // 3
        int[][] res = new int[R * C][2];
        int len = 0, d = 0, j = 0; // move <len> steps in the <d> direction
        res[j++] = new int[]{r0, c0};
        //After starting at (r0,c0), we need to walk in spirals,
        // where the length of the spiral increases after every two directions.
        // For example 2, we start at (r0=1, c0=4), then we go east by one length, we go south by one length.
        // Following that, we go west by 2 length and then, go north by 2 length.
        // After that, we go in next directions by 3 lengths, and so on.
        while (j < R * C) { // fill all the blanks
            if (d == 0 || d == 2) {
                len++; // when move east or west, the length of path need plus 1
            }
            for (int i = 0; i < len; i++) {
                r0 += dirt[d][0];
                c0 += dirt[d][1];
                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) // check valid
                    res[j++] = new int[]{r0, c0};
            }
            d = (d + 1) % 4; // turn to next direction
        }
        return res;
    }

    public int[][] spiralMatrixWithLinkedList(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(matrix[i], -1);
        }
        int rowBegin = 0;
        int rowEnd = m - 1;
        int colBegin = 0;
        int colEnd = n - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            for (int j = colBegin; j <= colEnd; j++) {
                if (head != null) {
                    matrix[rowBegin][j] = head.val;
                    head = head.next;
                }
            }
            rowBegin++;

            for (int j = rowBegin; j <= rowEnd; j++) {
                if (head != null) {
                    matrix[j][colEnd] = head.val;
                    head = head.next;
                }
            }
            colEnd--;

            // Make sure we are now on a different row.
            if (rowBegin <= rowEnd) { // without this condition, this corner test case [[2,3]] would print [2,3,2]
                for (int j = colEnd; j >= colBegin; j--) {
                    if (head != null) {
                        matrix[rowEnd][j] = head.val;
                        head = head.next;
                    }
                }
            }
            rowEnd--;

            // It's necessary because after we've traversed right, down, and left,
            // we might have exhausted all columns.

            if (colBegin <= colEnd) {
                // Travel Up
                // this block's work is to lift 1 row up from bottom
                // the rest of the work will be done by first 'Right' loop again
                for (int j = rowEnd; j >= rowBegin; j--) {
                    if (head != null) {
                        matrix[j][colBegin] = head.val;
                        head = head.next;
                    }
                }
            }
            colBegin++;
        }

        return matrix;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}

