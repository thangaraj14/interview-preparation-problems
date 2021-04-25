package practiceproblems;

import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> res = new ArrayList<Integer>();
        
        if (matrix.length == 0) {
            return res;
        }
        
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                System.out.println(" Right "+matrix[rowBegin][j]);
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                System.out.println(" Down "+matrix[j][colEnd]);
                res.add(matrix[j][colEnd]);
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) { // without this condition, this corner test case [[2,3]] would print [2,3,2]
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    System.out.println(" Left "+matrix[rowEnd][j]);
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            // this block's work is to move 1 row up from bottom
            // the rest of the work will be done by first 'Right' loop again
            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    System.out.println(" uppp "+matrix[j][colBegin]);
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }
        
        return res;
    }

    public static void main(String[] args) {
      
        int a[][] = {{1, 2, 3, 4},
                     {5, 6, 7, 8},
                     {9, 10, 11, 12},
                     {13, 14, 15, 16}};
                     spiralOrder(a);
    }
}