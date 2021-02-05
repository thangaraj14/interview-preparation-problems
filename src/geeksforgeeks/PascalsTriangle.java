package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 */
public class PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // suppose if the row is [1,3,3,1]
            // add 1 at start [1,1,3,3,1]
            // then add together j and j+1 elements like below
            // [1,4,3,3,1] => [1,4,6,3,1]=>[1,4,6,4,1]
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            result.add(new ArrayList<>(row));// every time the copy is only appended
        }
        return result;
    }

    public static void main(String[] args) {
        generate(5);
    }
}