package strings.stringProblems;

import java.util.ArrayList;
import java.util.List;

public class ZigZag {

    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        List<StringBuilder> grid = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            grid.add(new StringBuilder());
        }

        int row = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            grid.get(row).append(c);
            if (row == 0 || row == numRows - 1) {
                goingDown = !goingDown;
            }
            row += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : grid) {
            result.append(sb);
        }
        return result.toString();
    }
}
