package geeksforgeeks;

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