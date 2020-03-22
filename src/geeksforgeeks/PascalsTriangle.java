package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1); // append 1 at the end of each iteration
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1)); // modify after adding 1
            allrows.add(new ArrayList<Integer>(row));// take a copy of row and save it in result
        }
        return allrows;

    }
}