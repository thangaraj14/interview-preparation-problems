package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
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

        if (numRows == 0)
            return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> first = Arrays.asList(1);
        result.add(first);
        if (numRows == 1)
            return result;
        List<Integer> second = Arrays.asList(1, 1);
        result.add(second);
        if (numRows == 2)
            return result;

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
}