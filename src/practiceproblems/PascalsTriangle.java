package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 * <p>
 * Input: 5
 * Output:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {

        if (numRows == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> first = Arrays.asList(1);
        result.add(first);
        if (numRows == 1) {
            return result;
        }
        List<Integer> second = Arrays.asList(1, 1);
        result.add(second);
        if (numRows == 2) {
            return result;
        }

        for (int i = 2; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for (int k = 0; k < i - 1; k++) {
                int j = k + 1;
                temp.add(result.get(i - 1).get(k) + result.get(i - 1).get(j));
            }
            temp.add(1);
            result.add(temp);
        }
        return result;
    }

    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            // suppose if the row is [1,3,3,1]
            // add 1 at start [1,1,3,3,1]
            // then add together j and j+1 elements like below
            // [1,4,3,3,1] => [1,4,6,3,1]=>[1,4,6,4,1]
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            allrows.add(new ArrayList<Integer>(row));// every time the copy is only appended
        }
        return allrows;

    }
}