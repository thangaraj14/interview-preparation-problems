package practiceproblems.design;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subrectangle-queries/
 */
public class SubRectangleQueries {


    private int[][] rec = null;
    private List<int[]> list = null;

    public SubRectangleQueries(int[][] rectangle) {
        rec = rectangle;
        list = new ArrayList<>();

    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        int[] arr = new int[]{row1, col1, row2, col2, newValue};
        list.add(arr);
    }

    public int getValue(int row, int col) {
        int result = rec[row][col];

        for (int[] arr : list) {
            if (row >= arr[0] && row <= arr[2] && col <= arr[3] && col >= arr[1]) {
                result = arr[4];
            }
        }

        return result;
    }
}
